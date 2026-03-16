package com.nailoong.dfdatatableserver.data.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

/**
 * 武器属性表配置实体类
 * 对应JSON中的根对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WeaponAttributeTableConfig extends BaseJsonTable {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Flags")
    private String flags;

    @JsonProperty("Class")
    private String clazz;

    @JsonProperty("Properties")
    private Properties properties;

    @JsonProperty("Rows")
    private Map<String, WeaponAttributeRow> rows;

    @Data
    @NoArgsConstructor
    public static class Properties {

        @JsonProperty("RowStruct")
        private RowStruct rowStruct;

        @Data
        @NoArgsConstructor
        public static class RowStruct {

            @JsonProperty("ObjectName")
            private String objectName;

            @JsonProperty("ObjectPath")
            private String objectPath;
        }
    }

    /**
     * 武器属性行数据
     * 对应JSON中每个武器的完整配置
     */
    @Data
    @NoArgsConstructor
    public static class WeaponAttributeRow {

        // ==================== 基础标识 ====================
        @JsonProperty("RecId")
        private Long recId;

        @JsonProperty("RowDescription")
        private String rowDescription;

        // ==================== 开火模式 ====================
        @JsonProperty("SuppportFireModes")
        private List<String> supportFireModes;

        @JsonProperty("DefaultFireMode")
        private String defaultFireMode;

        @JsonProperty("FireRateMode")
        private String fireRateMode;

        @JsonProperty("FireSocketMode")
        private String fireSocketMode;

        @JsonProperty("FireInterval")
        private Double fireInterval;

        @JsonProperty("FireCD")
        private Double fireCD;

        @JsonProperty("FireDelayTime")
        private Double fireDelayTime;

        @JsonProperty("BurstFireCD")
        private Double burstFireCD;

        @JsonProperty("BurstInterval")
        private Double burstInterval;

        @JsonProperty("BurstFireInterval")
        private Double burstFireInterval;

        @JsonProperty("BurstFireBulletCount")
        private Integer burstFireBulletCount;

        @JsonProperty("PostFireTime")
        private Double postFireTime;

        @JsonProperty("ChamberTime")
        private Double chamberTime;

        @JsonProperty("AutoFireSingleInterval")
        private Double autoFireSingleInterval;

        @JsonProperty("AutoFireBurstInterval")
        private Double autoFireBurstInterval;

        @JsonProperty("bEnableSprintAutoDelayFireOnce")
        private Boolean enableSprintAutoDelayFireOnce;

        @JsonProperty("SingleFireTolerant")
        private Double singleFireTolerant;

        @JsonProperty("bSupportFireStartRotFromView")
        private Boolean supportFireStartRotFromView;

        @JsonProperty("FireFromViewConfig")
        private FireFromViewConfig fireFromViewConfig;

        @JsonProperty("bCanFireUnderWater")
        private Boolean canFireUnderWater;

        @JsonProperty("bEnableIgnoreTeammateBlock_SOLMode")
        private Boolean enableIgnoreTeammateBlockSOLMode;

        @JsonProperty("bEnableIgnoreTeammateBlock_MPMode")
        private Boolean enableIgnoreTeammateBlockMPMode;

        // ==================== 弹药系统 ====================
        @JsonProperty("CanAddAmmo")
        private Boolean canAddAmmo;

        @JsonProperty("WeaponAmmoCost")
        private Integer weaponAmmoCost;

        @JsonProperty("ProjectileNumPerShot")
        private Integer projectileNumPerShot;

        @JsonProperty("MaxValidBulletNumPerShot")
        private Integer maxValidBulletNumPerShot;

        @JsonProperty("WeaponClipAmmoCount")
        private Integer weaponClipAmmoCount;

        @JsonProperty("MaxCarriedAmmoCount")
        private Integer maxCarriedAmmoCount;

        @JsonProperty("AmmoType")
        private Integer ammoType;

        @JsonProperty("UseItemAmmoLogic")
        private Boolean useItemAmmoLogic;

        @JsonProperty("AmmoDamageRule_MP")
        private AmmoDamageRule ammoDamageRuleMP;

        @JsonProperty("EnableAmmoTip")
        private Boolean enableAmmoTip;

        @JsonProperty("LowAmmoPresent")
        private Double lowAmmoPresent;

        @JsonProperty("bEnableAutoModifyAmmo")
        private Boolean enableAutoModifyAmmo;

        @JsonProperty("AutoModifyAmmoInterval")
        private Double autoModifyAmmoInterval;

        @JsonProperty("DeltaAmmoOnce")
        private Integer deltaAmmoOnce;

        // ==================== 换弹逻辑 ====================
        @JsonProperty("ChangeClipMode")
        private String changeClipMode;

        @JsonProperty("ChangeClipMaxReloadedOnce")
        private Integer changeClipMaxReloadedOnce;

        @JsonProperty("ChangeClipTime")
        private Double changeClipTime;

        @JsonProperty("ChangeClipTimeWhenEmpty")
        private Double changeClipTimeWhenEmpty;

        @JsonProperty("ChangeClipTimeBackground")
        private Double changeClipTimeBackground;

        @JsonProperty("bEnableAimOnReloading")
        private Boolean enableAimOnReloading;

        @JsonProperty("bAutoReloadWhenEmpty")
        private Boolean autoReloadWhenEmpty;

        @JsonProperty("AutoReloadOption")
        private String autoReloadOption;

        // ==================== 过热系统 ====================
        @JsonProperty("HeatIncreasePerFire")
        private Double heatIncreasePerFire;

        @JsonProperty("HeatDecreasePerSecond")
        private Double heatDecreasePerSecond;

        @JsonProperty("OverHeatCDSeconds")
        private Double overHeatCDSeconds;

        @JsonProperty("EffectOverHeatMin")
        private Double effectOverHeatMin;

        // ==================== 装备时间 ====================
        @JsonProperty("EquipTime")
        private Double equipTime;

        @JsonProperty("UnEquipTime")
        private Double unEquipTime;

        @JsonProperty("FastEquipTime")
        private Double fastEquipTime;

        @JsonProperty("FastUnEquipTime")
        private Double fastUnEquipTime;

        @JsonProperty("FirstEquipTime")
        private Double firstEquipTime;

        @JsonProperty("HadFirstEquipAnim")
        private Boolean hadFirstEquipAnim;

        // ==================== 状态ID引用 ====================
        @JsonProperty("CharacterStateId")
        private Integer characterStateId;

        @JsonProperty("IK3PLeftShoulerPitch")
        private Double ik3PLeftShoulerPitch;

        @JsonProperty("IK3PLeftArmYaw")
        private Double ik3PLeftArmYaw;

        @JsonProperty("WaistShootCrossHairId")
        private Integer waistShootCrossHairId;

        @JsonProperty("WaistShootSpreadId")
        private Integer waistShootSpreadId;

        @JsonProperty("WaistShootRecoilId")
        private Integer waistShootRecoilId;

        @JsonProperty("AimAssistorId")
        private Integer aimAssistorId;

        @JsonProperty("AimAssistorIdForMPMode")
        private Integer aimAssistorIdForMPMode;

        @JsonProperty("AimingId")
        private Integer aimingId;

        @JsonProperty("SideAimingId")
        private Integer sideAimingId;

        @JsonProperty("AntiVehicleAssistAimConfigID")
        private Integer antiVehicleAssistAimConfigID;

        @JsonProperty("AntiVehiclePosPredictConfigID")
        private Integer antiVehiclePosPredictConfigID;

        @JsonProperty("WeaponHitPosPredictConfigID")
        private Integer weaponHitPosPredictConfigID;

        @JsonProperty("bEnableFastSwitchWeaponFireMode")
        private Boolean enableFastSwitchWeaponFireMode;

        // ==================== 镜头恢复 ====================
        @JsonProperty("CameraRecover")
        private CameraRecover cameraRecover;

        // ==================== 子弹效果 ====================
        @JsonProperty("BulletFlyingId")
        private Integer bulletFlyingId;

        @JsonProperty("BulletFlyingId_Breakthrough")
        private Integer bulletFlyingIdBreakthrough;

        @JsonProperty("AttackerValueId")
        private Integer attackerValueId;

        @JsonProperty("bHitRequestCheckOnClient")
        private Boolean hitRequestCheckOnClient;

        @JsonProperty("HitRequestCheckRadius")
        private Double hitRequestCheckRadius;

        @JsonProperty("HitRequestCheckRadiusForVehicle")
        private Double hitRequestCheckRadiusForVehicle;

        @JsonProperty("ExtraAttackerValueId")
        private List<Object> extraAttackerValueId;

        // ==================== 移动与晃动 ====================
        @JsonProperty("SprintToFireSpeed")
        private Double sprintToFireSpeed;

        @JsonProperty("SuperSprintToFireSpeed")
        private Double superSprintToFireSpeed;

        @JsonProperty("ReturnSprintInterval")
        private Double returnSprintInterval;

        @JsonProperty("MovementSpeedID")
        private Integer movementSpeedID;

        @JsonProperty("BattlefieldMovementSpeedID")
        private Integer battlefieldMovementSpeedID;

        @JsonProperty("ChangeSpeedScaleWhenHit")
        private Double changeSpeedScaleWhenHit;

        @JsonProperty("CameraSwayHitScale")
        private Double cameraSwayHitScale;

        @JsonProperty("GunSwayHitScale")
        private Double gunSwayHitScale;

        @JsonProperty("CameraSwayBeHitScale")
        private Double cameraSwayBeHitScale;

        @JsonProperty("GunSwayBeHitScale")
        private Double gunSwayBeHitScale;

        @JsonProperty("GunSwayBeHitScale_Mobile")
        private Double gunSwayBeHitScaleMobile;

        @JsonProperty("AimingIdleScale")
        private Double aimingIdleScale;

        @JsonProperty("AimingIdleScale_Mobile")
        private Double aimingIdleScaleMobile;

        @JsonProperty("MiniShakeScale")
        private Double miniShakeScale;

        @JsonProperty("BeHitCameraMoveId")
        private Integer beHitCameraMoveId;

        @JsonProperty("BeHitCameraMoveScale")
        private Double beHitCameraMoveScale;

        @JsonProperty("BeHitWeaponShakeScale")
        private Double beHitWeaponShakeScale;

        // ==================== 压制系统 ====================
        @JsonProperty("SingleSuppressValue")
        private Double singleSuppressValue;

        @JsonProperty("ToggleSuppressValueMax")
        private Double toggleSuppressValueMax;

        // ==================== 特长与显示 ====================
        @JsonProperty("CanEquipPerk")
        private Boolean canEquipPerk;

        @JsonProperty("PerkCost")
        private Double perkCost;

        @JsonProperty("ViewId")
        private Integer viewId;

        @JsonProperty("DisplayAttrValues")
        private List<AttributeValue> displayAttrValues;

        @JsonProperty("bIndependentMainAttributeCurve")
        private Boolean independentMainAttributeCurve;

        @JsonProperty("BaseMainAttributeValues")
        private List<AttributeValue> baseMainAttributeValues;

        // ==================== 其他属性 ====================
        @JsonProperty("ShotDistance")
        private Integer shotDistance;

        @JsonProperty("CheckVehicleLeanState")
        private Boolean checkVehicleLeanState;

        @JsonProperty("FireInVehicleShouldLeanout")
        private Boolean fireInVehicleShouldLeanout;

        @JsonProperty("FireInVehicleLeanoutCheckType")
        private Boolean fireInVehicleLeanoutCheckType;

        @JsonProperty("DeltaTime2TriggerChanceScale")
        private Double deltaTime2TriggerChanceScale;

        @JsonProperty("CdTime")
        private Double cdTime;

        @JsonProperty("RandomFailAddChance")
        private Double randomFailAddChance;

        @JsonProperty("RescueTimeIncrement")
        private Double rescueTimeIncrement;

        @JsonProperty("RescueDeathTimeIncrement")
        private Double rescueDeathTimeIncrement;

        @JsonProperty("CBowId")
        private Integer cBowId;


        /**
         * 从视角开火配置
         */
        @Data
        @NoArgsConstructor
        public static class FireFromViewConfig {

            @JsonProperty("bOverrideConfig")
            private Boolean overrideConfig;

            @JsonProperty("TolerantAngle")
            private Double tolerantAngle;

            @JsonProperty("UseLogicAsBasic")
            private Boolean useLogicAsBasic;
        }

        /**
         * 弹药伤害规则
         */
        @Data
        @NoArgsConstructor
        public static class AmmoDamageRule {

            @JsonProperty("AmmoDamageType")
            private String ammoDamageType;

            @JsonProperty("AmmoArmorLevelDiff")
            private Integer ammoArmorLevelDiff;

            @JsonProperty("PenetrateAttenuationRatio")
            private Double penetrateAttenuationRatio;

            @JsonProperty("PenetrateAttenuationType")
            private String penetrateAttenuationType;

            @JsonProperty("PenetrateAmmoLevelDecrease")
            private Integer penetrateAmmoLevelDecrease;
        }

        /**
         * 镜头恢复配置
         */
        @Data
        @NoArgsConstructor
        public static class CameraRecover {

            @JsonProperty("PitchUndampedFrequency")
            private Double pitchUndampedFrequency;

            @JsonProperty("YawUndampedFrequency")
            private Double yawUndampedFrequency;

            @JsonProperty("PitchDampingRatio")
            private Double pitchDampingRatio;

            @JsonProperty("YawDampingRatio")
            private Double yawDampingRatio;

            @JsonProperty("PitchFixN")
            private Double pitchFixN;

            @JsonProperty("YawFixN")
            private Double yawFixN;

            @JsonProperty("PitchMaxVelocity")
            private Double pitchMaxVelocity;

            @JsonProperty("YawMaxVelocity")
            private Double yawMaxVelocity;

            @JsonProperty("PitchRecoverRate")
            private Double pitchRecoverRate;

            @JsonProperty("YawRecoverRate")
            private Double yawRecoverRate;

            @JsonProperty("RecoverPauseWhiteList")
            private List<String> recoverPauseWhiteList;
        }

        /**
         * 属性键值对（用于DisplayAttrValues和BaseMainAttributeValues）
         */
        @Data
        @NoArgsConstructor
        public static class AttributeValue {

            @JsonProperty("Key")
            private String key;

            @JsonProperty("Value")
            private Double value;
        }
    }
}