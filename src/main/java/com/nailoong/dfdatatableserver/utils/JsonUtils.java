package com.nailoong.dfdatatableserver.utils;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;

public class JsonUtils {

    /**
     * 递归替换 JSON 节点中的字段名
     * @param node JSON 节点
     * @param mapping 字段映射关系
     * @return 替换后的节点
     */
    public static JsonNode replaceFieldNames(JsonNode node, Map<String, String> mapping, ObjectMapper objectMapper) {
        if (node.isObject()) {
            ObjectNode newObject = objectMapper.createObjectNode();

            // 使用 Iterator 遍历对象的所有字段
            for (Map.Entry<String, JsonNode> entry : node.properties()) {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                // 如果字段名在映射中存在，则替换为中文含义
                String newFieldName = mapping.getOrDefault(fieldName, fieldName);

                // 递归处理子节点
                JsonNode newNode = replaceFieldNames(fieldValue, mapping, objectMapper);

                newObject.set(newFieldName, newNode);
            }

            return newObject;
        } else if (node.isArray()) {
            // 处理数组
            ArrayNode newArray = objectMapper.createArrayNode();
            for (JsonNode element : node) {
                newArray.add(replaceFieldNames(element, mapping, objectMapper));
            }
            return newArray;
        } else {
            // 基本类型直接返回
            return node;
        }
    }
}
