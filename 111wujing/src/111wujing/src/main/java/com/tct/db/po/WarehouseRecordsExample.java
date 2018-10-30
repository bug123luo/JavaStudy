package com.tct.db.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarehouseRecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WarehouseRecordsExample() {
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

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(Integer value) {
            addCriterion("warehouse_id =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(Integer value) {
            addCriterion("warehouse_id <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(Integer value) {
            addCriterion("warehouse_id >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(Integer value) {
            addCriterion("warehouse_id <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<Integer> values) {
            addCriterion("warehouse_id in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<Integer> values) {
            addCriterion("warehouse_id not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeIsNull() {
            addCriterion("warehouse_in_time is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeIsNotNull() {
            addCriterion("warehouse_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeEqualTo(Date value) {
            addCriterion("warehouse_in_time =", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeNotEqualTo(Date value) {
            addCriterion("warehouse_in_time <>", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeGreaterThan(Date value) {
            addCriterion("warehouse_in_time >", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("warehouse_in_time >=", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeLessThan(Date value) {
            addCriterion("warehouse_in_time <", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeLessThanOrEqualTo(Date value) {
            addCriterion("warehouse_in_time <=", value, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeIn(List<Date> values) {
            addCriterion("warehouse_in_time in", values, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeNotIn(List<Date> values) {
            addCriterion("warehouse_in_time not in", values, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeBetween(Date value1, Date value2) {
            addCriterion("warehouse_in_time between", value1, value2, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseInTimeNotBetween(Date value1, Date value2) {
            addCriterion("warehouse_in_time not between", value1, value2, "warehouseInTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeIsNull() {
            addCriterion("warehouse_out_time is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeIsNotNull() {
            addCriterion("warehouse_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeEqualTo(Date value) {
            addCriterion("warehouse_out_time =", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeNotEqualTo(Date value) {
            addCriterion("warehouse_out_time <>", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeGreaterThan(Date value) {
            addCriterion("warehouse_out_time >", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("warehouse_out_time >=", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeLessThan(Date value) {
            addCriterion("warehouse_out_time <", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("warehouse_out_time <=", value, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeIn(List<Date> values) {
            addCriterion("warehouse_out_time in", values, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeNotIn(List<Date> values) {
            addCriterion("warehouse_out_time not in", values, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeBetween(Date value1, Date value2) {
            addCriterion("warehouse_out_time between", value1, value2, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWarehouseOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("warehouse_out_time not between", value1, value2, "warehouseOutTime");
            return (Criteria) this;
        }

        public Criteria andWebUserIdIsNull() {
            addCriterion("web_user_id is null");
            return (Criteria) this;
        }

        public Criteria andWebUserIdIsNotNull() {
            addCriterion("web_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andWebUserIdEqualTo(Integer value) {
            addCriterion("web_user_id =", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdNotEqualTo(Integer value) {
            addCriterion("web_user_id <>", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdGreaterThan(Integer value) {
            addCriterion("web_user_id >", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_user_id >=", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdLessThan(Integer value) {
            addCriterion("web_user_id <", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("web_user_id <=", value, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdIn(List<Integer> values) {
            addCriterion("web_user_id in", values, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdNotIn(List<Integer> values) {
            addCriterion("web_user_id not in", values, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdBetween(Integer value1, Integer value2) {
            addCriterion("web_user_id between", value1, value2, "webUserId");
            return (Criteria) this;
        }

        public Criteria andWebUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("web_user_id not between", value1, value2, "webUserId");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Integer value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Integer value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Integer value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Integer value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Integer> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Integer> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Integer value1, Integer value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_id not between", value1, value2, "appId");
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

        public Criteria andGunIdEqualTo(Integer value) {
            addCriterion("gun_id =", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotEqualTo(Integer value) {
            addCriterion("gun_id <>", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdGreaterThan(Integer value) {
            addCriterion("gun_id >", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gun_id >=", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdLessThan(Integer value) {
            addCriterion("gun_id <", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdLessThanOrEqualTo(Integer value) {
            addCriterion("gun_id <=", value, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdIn(List<Integer> values) {
            addCriterion("gun_id in", values, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotIn(List<Integer> values) {
            addCriterion("gun_id not in", values, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdBetween(Integer value1, Integer value2) {
            addCriterion("gun_id between", value1, value2, "gunId");
            return (Criteria) this;
        }

        public Criteria andGunIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gun_id not between", value1, value2, "gunId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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