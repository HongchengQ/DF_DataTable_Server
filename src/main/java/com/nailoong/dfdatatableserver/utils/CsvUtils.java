package com.nailoong.dfdatatableserver.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CsvUtils {

    /**
     * 从 CSV 文件加载字段映射关系
     *
     * @param file CSV 文件
     * @return 字段名 -> 含义的映射
     */
    public static Map<String, String> loadFieldMapping(File file) {
        Map<String, String> mapping = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                // 跳过空行和章节标题行（如"一、基础标识"）
                if (line.trim().isEmpty() || line.matches("^一、.*")) {
                    continue;
                }

                // 跳过表头
                if (skipHeader && line.contains("字段名") && line.contains("含义")) {
                    skipHeader = false;
                    continue;
                }

                // 解析 CSV 行（简单处理，假设没有逗号在字段内）
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String fieldName = parts[0].trim();
                    String description = parts[1].trim();

                    if (!fieldName.isEmpty() && !description.isEmpty()) {
                        mapping.put(fieldName, description);
                    }
                }
            }

            log.info("已加载 {} 个字段映射关系", mapping.size());

        } catch (IOException e) {
            log.error("读取 CSV 映射文件失败：{}", file.getName(), e);
        }

        return mapping;
    }
}
