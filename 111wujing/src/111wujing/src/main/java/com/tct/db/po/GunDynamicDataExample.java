package com.tct.db.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GunDynamicDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GunDynamicDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGunIdIsNull() {
            addCriterion("gun_id is null");
            return (Criteria) this;
        }

        public Criteria andGunIdIsNotNull() {
            addCriterion("gun_id is not null");
            return (Criteria) this;
        }

        public Criteria andGunIdEqualTo(String value) {
            addCriterion("gun_id =", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotEqualTo(String value) {
            addCriterion("gun_id <>", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdGreaterThan(String value) {
            addCriterion("gun_id >", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdGreaterThanOrEqualTo(String value) {
            addCriterion("gun_id >=", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdLessThan(String value) {
            addCriterion("gun_id <", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdLessThanOrEqualTo(String value) {
            addCriterion("gun_id <=", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdLike(String value) {
            addCriterion("gun_id like", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotLike(String value) {
            addCriterion("gun_id not like", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdIn(List<String> values) {
            addCriterion("gun_id in", values, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotIn(List<String> values) {
            addCriterion("gun_id not in", values, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdBetween(String value1, String value2) {
            addCriterion("gun_id between", value1, value2, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotBetween(String value1, String value2) {
            addCriterion("gun_id not between", value1, value2, "gunId");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIsNull() {
            addCriterion("total_bullet_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIsNotNull() {
            addCriterion("total_bullet_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberEqualTo(Integer value) {
            addCriterion("total_bullet_number =", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotEqualTo(Integer value) {
            addCriterion("total_bullet_number <>", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberGreaterThan(Integer value) {
            addCriterion("total_bullet_number >", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_bullet_number >=", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberLessThan(Integer value) {
            addCriterion("total_bullet_number <", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_bullet_number <=", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIn(List<Integer> values) {
            addCriterion("total_bullet_number in", values, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotIn(List<Integer> values) {
            addCriterion("total_bullet_number not in", values, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_bullet_number between", value1, value2, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_bullet_number not between", value1, value2, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIsNull() {
            addCriterion("bullet_number is null");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIsNotNull() {
            addCriterion("bullet_number is not null");
            return (Criteria) this;
        }

        public Criteria andBulletNumberEqualTo(Integer value) {
            addCriterion("bullet_number =", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotEqualTo(Integer value) {
            addCriterion("bullet_number <>", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberGreaterThan(Integer value) {
            addCriterion("bullet_number >", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("bullet_number >=", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberLessThan(Integer value) {
            addCriterion("bullet_number <", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberLessThanOrEqualTo(Integer value) {
            addCriterion("bullet_number <=", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIn(List<Integer> values) {
            addCriterion("bullet_number in", values, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotIn(List<Integer> values) {
            addCriterion("bullet_number not in", values, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberBetween(Integer value1, Integer value2) {
            addCriterion("bullet_number between", value1, value2, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("bullet_number not between", value1, value2, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIsNull() {
            addCriterion("real_time_state is null");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIsNotNull() {
            addCriterion("real_time_state is not null");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateEqualTo(Integer value) {
            addCriterion("real_time_state =", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotEqualTo(Integer value) {
            addCriterion("real_time_state <>", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateGreaterThan(Integer value) {
            addCriterion("real_time_state >", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_time_state >=", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateLessThan(Integer value) {
            addCriterion("real_time_state <", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateLessThanOrEqualTo(Integer value) {
            addCriterion("real_time_state <=", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIn(List<Integer> values) {
            addCriterion("real_time_state in", values, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotIn(List<Integer> values) {
            addCriterion("real_time_state not in", values, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateBetween(Integer value1, Integer value2) {
            addCriterion("real_time_state between", value1, value2, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("real_time_state not between", value1, value2, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateIsNull() {
            addCriterion("gun_device_state is null");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateIsNotNull() {
            addCriterion("gun_device_state is not null");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateEqualTo(Integer value) {
            addCriterion("gun_device_state =", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateNotEqualTo(Integer value) {
            addCriterion("gun_device_state <>", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateGreaterThan(Integer value) {
            addCriterion("gun_device_state >", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("gun_device_state >=", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateLessThan(Integer value) {
            addCriterion("gun_device_state <", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateLessThanOrEqualTo(Integer value) {
            addCriterion("gun_device_state <=", value, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateIn(List<Integer> values) {
            addCriterion("gun_device_state in", values, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateNotIn(List<Integer> values) {
            addCriterion("gun_device_state not in", values, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateBetween(Integer value1, Integer value2) {
            addCriterion("gun_device_state between", value1, value2, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceStateNotBetween(Integer value1, Integer value2) {
            addCriterion("gun_device_state not between", value1, value2, "gunDeviceState");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerIsNull() {
            addCriterion("gun_device_battery_power is null");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerIsNotNull() {
            addCriterion("gun_device_battery_power is not null");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerEqualTo(String value) {
            addCriterion("gun_device_battery_power =", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerNotEqualTo(String value) {
            addCriterion("gun_device_battery_power <>", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerGreaterThan(String value) {
            addCriterion("gun_device_battery_power >", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerGreaterThanOrEqualTo(String value) {
            addCriterion("gun_device_battery_power >=", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerLessThan(String value) {
            addCriterion("gun_device_battery_power <", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerLessThanOrEqualTo(String value) {
            addCriterion("gun_device_battery_power <=", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerLike(String value) {
            addCriterion("gun_device_battery_power like", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerNotLike(String value) {
            addCriterion("gun_device_battery_power not like", value, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerIn(List<String> values) {
            addCriterion("gun_device_battery_power in", values, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerNotIn(List<String> values) {
            addCriterion("gun_device_battery_power not in", values, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerBetween(String value1, String value2) {
            addCriterion("gun_device_battery_power between", value1, value2, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andGunDeviceBatteryPowerNotBetween(String value1, String value2) {
            addCriterion("gun_device_battery_power not between", value1, value2, "gunDeviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}