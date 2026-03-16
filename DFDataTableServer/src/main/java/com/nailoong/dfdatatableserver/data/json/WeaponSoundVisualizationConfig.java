package com.nailoong.dfdatatableserver.data.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 对应JSON中的根对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WeaponSoundVisualizationConfig extends BaseJsonTable {

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
    private Map<String, SoundVisualizationRow> rows;

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
    public static class SoundVisualizationRow {

        // ==================== 基础信息 ====================
        @JsonProperty("Description")
        private String description;

        @JsonProperty("bEnable")
        private Boolean enable;

        @JsonProperty("WeaponId")
        private String weaponId;

        @JsonProperty("WeaponIdInt")
        private Long weaponIdInt;

        // ==================== 消音器配置 ====================
        @JsonProperty("SilencerScalarMap")
        private List<SilencerScalar> silencerScalarMap;

        // ==================== 距离信息 ====================
        @JsonProperty("DistanceInfoArray")
        private List<DistanceInfo> distanceInfoArray;

        // ==================== 声音类型 ====================
        @JsonProperty("Type")
        private String type;

        @JsonProperty("IconType")
        private String iconType;

        @JsonProperty("bIsRed")
        private Boolean isRed;

        @JsonProperty("Priority")
        private Integer priority;

        // ==================== 时间配置 ====================
        @JsonProperty("Duration")
        private Double duration;

        @JsonProperty("TriggerCD")
        private Double triggerCD;

        @JsonProperty("bUseTimeLevelMap")
        private Boolean useTimeLevelMap;

        @JsonProperty("DurationMap")
        private List<Object> durationMap;  // 可能包含复杂对象，使用Object或JsonNode

        @JsonProperty("TriggerCDMap")
        private List<Object> triggerCDMap;  // 可能包含复杂对象，使用Object或JsonNode

        // ==================== 行为配置 ====================
        @JsonProperty("bAffectedByListenRange")
        private Boolean affectedByListenRange;

        @JsonProperty("AudioBlueprintType")
        private String audioBlueprintType;

        @JsonProperty("UnderWaterAffectScale")
        private Double underWaterAffectScale;

        @JsonProperty("bNotifyMyself")
        private Boolean notifyMyself;

        @JsonProperty("bCheckState")
        private Boolean checkState;

        @JsonProperty("Duration_Myself")
        private Double durationMyself;

        @JsonProperty("SoundLevel_Myself")
        private Integer soundLevelMyself;

        /**
         * 消音器缩放系数
         * 对应SilencerScalarMap中的每个元素
         */
        @Data
        @NoArgsConstructor
        public static class SilencerScalar {

            @JsonProperty("Key")
            private String key;

            @JsonProperty("Value")
            private Double value;
        }

        /**
         * 距离信息
         * 对应DistanceInfoArray中的每个元素
         */
        @Data
        @NoArgsConstructor
        public static class DistanceInfo {

            @JsonProperty("MinDistance")
            private Double minDistance;

            @JsonProperty("MaxDistance")
            private Double maxDistance;

            @JsonProperty("DistanceLevel")
            private Integer distanceLevel;
        }
    }
}