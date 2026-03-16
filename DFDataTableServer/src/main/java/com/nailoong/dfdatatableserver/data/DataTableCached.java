package com.nailoong.dfdatatableserver.data;

import java.io.File;
import java.util.HashMap;

public class DataTableCached {

    // TODO: 文件内容可以压缩一遍 节省内存
    public static HashMap<String, File> tableFileMap = new HashMap<>();

    public static HashMap<String, Object> tableObjectMap = new HashMap<>();

    // 字段映射
    public static HashMap<String, String> fieldMapping = new HashMap<>();
}
