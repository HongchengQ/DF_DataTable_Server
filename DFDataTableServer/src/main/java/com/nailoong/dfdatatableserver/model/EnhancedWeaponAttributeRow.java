package com.nailoong.dfdatatableserver.model;

import com.nailoong.dfdatatableserver.data.json.WeaponAttributeTableConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 增强的武器属性类（包含计算字段）
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EnhancedWeaponAttributeRow extends WeaponAttributeTableConfig.WeaponAttributeRow {

    // 计算字段 - 武器类型推断
    private WeaponType weaponType;

    // 计算字段 - 射速（发/分钟）
    private Double roundsPerMinute;

    // 计算字段 - 弹匣容量
    private Integer magazineSize;

    // 计算字段 - 总备弹量
    private Integer totalAmmo;

    // 计算字段 - 换弹总时间
    private Double totalReloadTime;

    // 计算字段 - 是否为连发武器
    private Boolean isAutoWeapon;

    // 计算字段 - 是否为栓动狙击
    private Boolean isBoltActionSniper;

    /**
     * 从基础属性构建增强对象
     */
    public static EnhancedWeaponAttributeRow fromBase(WeaponAttributeTableConfig.WeaponAttributeRow base) {
        EnhancedWeaponAttributeRow enhanced = new EnhancedWeaponAttributeRow();

        // 复制所有属性
        BeanUtils.copyProperties(base, enhanced);

        // 计算武器类型
        enhanced.setWeaponType(inferWeaponType(base));

        // 计算射速 (60秒 / 射击间隔)
        if (base.getFireInterval() != null && base.getFireInterval() > 0) {
            enhanced.setRoundsPerMinute(60.0 / base.getFireInterval());
        }

        // 弹匣容量
        enhanced.setMagazineSize(base.getWeaponClipAmmoCount());

        // 总备弹量（弹匣容量 + 携带弹药）
        if (base.getWeaponClipAmmoCount() != null && base.getMaxCarriedAmmoCount() != null) {
            enhanced.setTotalAmmo(base.getWeaponClipAmmoCount() + base.getMaxCarriedAmmoCount());
        }

        // 换弹总时间
        if (base.getChangeClipTime() != null && base.getChangeClipTimeWhenEmpty() != null) {
            enhanced.setTotalReloadTime(base.getChangeClipTime() + base.getChangeClipTimeWhenEmpty());
        }

        // 是否为全自动武器
        enhanced.setIsAutoWeapon(
                base.getSupportFireModes() != null &&
                        base.getSupportFireModes().contains("EGPWeaponFiringMode::Auto")
        );

        // 是否为栓动狙击（射速慢 + 上膛时间 > 0）
        enhanced.setIsBoltActionSniper(
                base.getFireInterval() != null &&
                        base.getFireInterval() > 0.5 &&
                        base.getChamberTime() != null &&
                        base.getChamberTime() > 0
        );

        return enhanced;
    }

    private static WeaponType inferWeaponType(WeaponAttributeTableConfig.WeaponAttributeRow weapon) {
        if (weapon == null) return WeaponType.UNKNOWN;

        // 根据属性推断武器类型
        Double fireInterval = weapon.getFireInterval();
        Integer clipSize = weapon.getWeaponClipAmmoCount();
        Double chamberTime = weapon.getChamberTime();
        List<String> fireModes = weapon.getSupportFireModes();

        // 栓动狙击判断
        if (fireInterval != null && fireInterval > 0.8 &&
                chamberTime != null && chamberTime > 0.5 &&
                clipSize != null && clipSize <= 10) {
            return WeaponType.SNIPER_RIFLE;
        }

        // 霰弹枪判断（这里需要根据实际数据特征）
        if (clipSize != null && clipSize <= 8 && fireInterval != null && fireInterval > 0.3) {
            // 可能还需要判断ProjectileNumPerShot
            return WeaponType.SHOTGUN;
        }

        // 轻机枪判断
        if (clipSize != null && clipSize >= 50) {
            return WeaponType.LIGHT_MACHINE_GUN;
        }

        // 冲锋枪判断
        if (fireInterval != null && fireInterval < 0.08 && clipSize != null && clipSize <= 40) {
            return WeaponType.SUBMACHINE_GUN;
        }

        // 手枪判断
        if (clipSize != null && clipSize <= 20 && fireInterval != null && fireInterval > 0.1) {
            return WeaponType.PISTOL;
        }

        // 默认归为突击步枪
        return WeaponType.ASSAULT_RIFLE;
    }
}