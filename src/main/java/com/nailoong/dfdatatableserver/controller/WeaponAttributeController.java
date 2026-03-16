package com.nailoong.dfdatatableserver.controller;

import com.nailoong.dfdatatableserver.data.DataTableCached;
import com.nailoong.dfdatatableserver.data.json.WeaponAttributeTableConfig;
import com.nailoong.dfdatatableserver.model.EnhancedWeaponAttributeRow;
import com.nailoong.dfdatatableserver.model.WeaponType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nailoong.dfdatatableserver.data.DataTableCached.tableObjectMap;

@RestController
@RequestMapping("/api/weapon/attributes")
public class WeaponAttributeController {

    /**
     * 获取所有武器列表
     *
     * @return Map<武器id, 武器名>
     */
    @GetMapping("/index")
    public Map<Long, String> getAllWeaponTableIndex() {

        return DataTableCached.allWeaponIdNameTable;
    }

    /**
     * 获取完整武器属性表
     */
    @GetMapping("/table")
    public WeaponAttributeTableConfig getAttributeTable() {
        return (WeaponAttributeTableConfig) tableObjectMap.get("WeaponAttributeTable");
    }

    /**
     * 获取所有武器（原始数据）
     */
    @GetMapping("/all")
    public Map<String, WeaponAttributeTableConfig.WeaponAttributeRow> getAllWeapons() {
        return getAttributeTable().getRows();
    }

//    /**
//     * 获取所有武器（增强数据）
//     */
//    @GetMapping("/all/enhanced")
//    public List<EnhancedWeaponAttributeRow> getAllWeaponsEnhanced() throws IOException {
//        return getAttributeTable().getRows().values().stream().map(EnhancedWeaponAttributeRow::fromBase).collect(Collectors.toList());
//    }

//    /**
//     * 根据武器名称获取配置
//     */
//    @GetMapping("/weapon/{weaponName}")
//    public WeaponAttributeTableConfig.WeaponAttributeRow getWeaponByName(@PathVariable String weaponName) {
//        // TODO: 配置表与游戏显示名称其实并不一致
//
//        WeaponAttributeTableConfig.WeaponAttributeRow weaponAttributeRow = getAttributeTable().getRows().get(weaponName);
//
//        if (weaponAttributeRow == null) throw new RuntimeException("未查询到相关内容:" + weaponName);
//        return weaponAttributeRow;
//    }

    /**
     * 根据RecId获取武器配置
     */
    @GetMapping("/id/{recId}")
    public WeaponAttributeTableConfig.WeaponAttributeRow getWeaponByRecId(@PathVariable Long recId) throws IOException {
        return getAttributeTable().getRows().values().stream().filter(row ->
                row.getRecId().equals(recId)).findFirst().orElse(null);
    }

    /**
     * 获取指定类型的武器
     */
    @GetMapping("/type/{weaponType}")
    public List<EnhancedWeaponAttributeRow> getWeaponsByType(@PathVariable String weaponType) throws IOException {
        WeaponType type = WeaponType.valueOf(weaponType.toUpperCase());
        return getAttributeTable().getRows().values().stream().map(EnhancedWeaponAttributeRow::fromBase).filter(weapon -> weapon.getWeaponType() == type).collect(Collectors.toList());
    }

    /**
     * 获取高射速武器（> 600 RPM）
     */
    @GetMapping("/high-rate")
    public List<EnhancedWeaponAttributeRow> getHighRateWeapons() throws IOException {
        return getAttributeTable().getRows().values().stream().map(EnhancedWeaponAttributeRow::fromBase).filter(weapon -> weapon.getRoundsPerMinute() != null && weapon.getRoundsPerMinute() > 600).collect(Collectors.toList());
    }
}