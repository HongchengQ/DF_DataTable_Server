package com.nailoong.dfdatatableserver.init;

import com.nailoong.dfdatatableserver.data.DataTableCached;
import com.nailoong.dfdatatableserver.data.json.BaseJsonTable;
import com.nailoong.dfdatatableserver.data.json.WeaponAttributeTableConfig;
import com.nailoong.dfdatatableserver.data.json.WeaponSoundVisualizationConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nailoong.dfdatatableserver.data.DataTableCached.tableObjectMap;
import static com.nailoong.dfdatatableserver.utils.CsvUtils.loadFieldMapping;
import static com.nailoong.dfdatatableserver.utils.FileUtils.findFilesByExtension;
import static com.nailoong.dfdatatableserver.utils.JsonUtils.replaceFieldNames;
import static com.nailoong.dfdatatableserver.utils.Utils.isNumeric;

@Slf4j
@Component
class Run {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 启动时自动执行
     */
    @PostConstruct
    public void init() throws IOException {
        loadAllDataTableFile();
        loadCsvFieldMapping();
        loadWeaponIdNameTable();
//        replaceAllDataTableFile();
    }

    /**
     * 加载所有文件
     */
    public void loadAllDataTableFile() {
        for (File file : findFilesByExtension("./DataTables", new String[]{".json"})) {
            List<BaseJsonTable> baseJsonTable = objectMapper.readValue(file, new TypeReference<>() {});
            DataTableCached.tableFileMap.put(baseJsonTable.getFirst().getName(), file);

            if (file.getName().equals("WeaponAttributeTable.json")) {
                List<WeaponAttributeTableConfig> list = objectMapper.readValue(file, new TypeReference<>() {});

                WeaponAttributeTableConfig weaponAttributeTableConfig = list.getFirst();

                DataTableCached.tableObjectMap.put("WeaponAttributeTable", weaponAttributeTableConfig);
            } else if (file.getName().equals("WeaponSoundVisualizationConfig.json")) {
                List<WeaponSoundVisualizationConfig> list = objectMapper.readValue(file, new TypeReference<>() {});

                WeaponSoundVisualizationConfig weaponSoundVisualizationConfig = list.getFirst();

                DataTableCached.tableObjectMap.put("WeaponSoundVisualizationConfig", weaponSoundVisualizationConfig);
            }
        }

        log.debug(String.valueOf(DataTableCached.tableFileMap.keySet()));
    }

    /**
     * 加载 CSV 映射关系
     */
    public void loadCsvFieldMapping() throws IOException {
        File resourceFile = new ClassPathResource("static/mapping.csv").getFile();

        DataTableCached.fieldMapping = (HashMap<String, String>) loadFieldMapping(resourceFile);
    }

    /**
     * 加载武器id与名称对照表(需要自己合成，给的文件没有专门的对照表)
     */
    static void loadWeaponIdNameTable() {
        HashMap<Long, String> weaponAttributeRowMap = new HashMap<>();
        {
            WeaponAttributeTableConfig weaponAttributeTableConfig =
                    (WeaponAttributeTableConfig) tableObjectMap.get("WeaponAttributeTable");

            for (String ketName : weaponAttributeTableConfig.getRows().keySet()) {
                Long recId = weaponAttributeTableConfig.getRows().get(ketName).getRecId();
                weaponAttributeRowMap.put(recId, ketName);
            }
        }

        HashMap<Long, String> idNameMap = new HashMap<>();
        {
            WeaponSoundVisualizationConfig weaponSoundVisualizationConfig =
                    (WeaponSoundVisualizationConfig) tableObjectMap.get("WeaponSoundVisualizationConfig");

            for (WeaponSoundVisualizationConfig.SoundVisualizationRow row : weaponSoundVisualizationConfig.getRows().values()) {
                idNameMap.put(row.getWeaponIdInt(), row.getDescription());
            }
        }

        HashMap<Long, String> allWeaponTableIndex = new HashMap<>();
        for (Long id : weaponAttributeRowMap.keySet()) {
            if (idNameMap.containsKey(id)) {
                allWeaponTableIndex.put(id, idNameMap.get(id));
            } else {
                String name = weaponAttributeRowMap.get(id);
                // key 为纯数字11位的的不要加入
                if (name.length() != 11 && !isNumeric(name)) {
                    allWeaponTableIndex.put(id, name);
                }
            }
        }

        DataTableCached.allWeaponIdNameTable.putAll(allWeaponTableIndex);
    }

    /**
     * 根据 csv 映射规则替换 DataTableConst.tableMaps 文件中的关键字 并导出新目录
     */
    public void replaceAllDataTableFile() {
        // 创建输出目录
        File outputDir = new File("./DataTables_Replaced");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // 遍历所有已加载的 JSON 文件
        for (Map.Entry<String, File> entry : DataTableCached.tableFileMap.entrySet()) {
            File inputFile = entry.getValue();

            try {
                // 读取 JSON 文件
                JsonNode rootNode = objectMapper.readTree(inputFile);

                // 递归替换字段名
                JsonNode replacedNode = replaceFieldNames(rootNode, DataTableCached.fieldMapping, objectMapper);

                // 生成新的文件路径
                String relativePath = inputFile.getPath().replace("./DataTables", "");
                File outputFile = new File(outputDir, relativePath);

                // 确保父目录存在
                File parentDir = outputFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }

                // 写入新文件
                String jsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(replacedNode);
                try (FileWriter writer = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
                    writer.write(jsonContent);
                }

                log.info("已处理文件：{} -> {}", inputFile.getPath(), outputFile.getPath());

            } catch (IOException e) {
                log.error("处理文件失败：{}", inputFile.getPath(), e);
            }
        }

        log.info("所有文件替换完成，输出目录：{}", outputDir.getAbsolutePath());
    }
}
