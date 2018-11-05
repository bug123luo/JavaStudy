package com.tct.db.po;

import java.util.ArrayList;
import java.util.List;

public class AppExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppExample() {
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

        public Criteria andAppSystemIsNull() {
            addCriterion("app_system is null");
            return (Criteria) this;
        }

        public Criteria andAppSystemIsNotNull() {
            addCriterion("app_system is not null");
            return (Criteria) this;
        }

        public Criteria andAppSystemEqualTo(String value) {
            addCriterion("app_system =", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotEqualTo(String value) {
            addCriterion("app_system <>", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemGreaterThan(String value) {
            addCriterion("app_system >", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemGreaterThanOrEqualTo(String value) {
            addCriterion("app_system >=", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLessThan(String value) {
            addCriterion("app_system <", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLessThanOrEqualTo(String value) {
            addCriterion("app_system <=", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLike(String value) {
            addCriterion("app_system like", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotLike(String value) {
            addCriterion("app_system not like", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemIn(List<String> values) {
            addCriterion("app_system in", values, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotIn(List<String> values) {
            addCriterion("app_system not in", values, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemBetween(String value1, String value2) {
            addCriterion("app_system between", value1, value2, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotBetween(String value1, String value2) {
            addCriterion("app_system not between", value1, value2, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionIsNull() {
            addCriterion("app_system_version is null");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionIsNotNull() {
            addCriterion("app_system_version is not null");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionEqualTo(String value) {
            addCriterion("app_system_version =", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionNotEqualTo(String value) {
            addCriterion("app_system_version <>", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionGreaterThan(String value) {
            addCriterion("app_system_version >", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionGreaterThanOrEqualTo(String value) {
            addCriterion("app_system_version >=", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionLessThan(String value) {
            addCriterion("app_system_version <", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionLessThanOrEqualTo(String value) {
            addCriterion("app_system_version <=", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionLike(String value) {
            addCriterion("app_system_version like", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionNotLike(String value) {
            addCriterion("app_system_version not like", value, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionIn(List<String> values) {
            addCriterion("app_system_version in", values, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionNotIn(List<String> values) {
            addCriterion("app_system_version not in", values, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionBetween(String value1, String value2) {
            addCriterion("app_system_version between", value1, value2, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppSystemVersionNotBetween(String value1, String value2) {
            addCriterion("app_system_version not between", value1, value2, "appSystemVersion");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameIsNull() {
            addCriterion("app_phone_type_name is null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameIsNotNull() {
            addCriterion("app_phone_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameEqualTo(String value) {
            addCriterion("app_phone_type_name =", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameNotEqualTo(String value) {
            addCriterion("app_phone_type_name <>", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameGreaterThan(String value) {
            addCriterion("app_phone_type_name >", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("app_phone_type_name >=", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameLessThan(String value) {
            addCriterion("app_phone_type_name <", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameLessThanOrEqualTo(String value) {
            addCriterion("app_phone_type_name <=", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameLike(String value) {
            addCriterion("app_phone_type_name like", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameNotLike(String value) {
            addCriterion("app_phone_type_name not like", value, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameIn(List<String> values) {
            addCriterion("app_phone_type_name in", values, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameNotIn(List<String> values) {
            addCriterion("app_phone_type_name not in", values, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameBetween(String value1, String value2) {
            addCriterion("app_phone_type_name between", value1, value2, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppPhoneTypeNameNotBetween(String value1, String value2) {
            addCriterion("app_phone_type_name not between", value1, value2, "appPhoneTypeName");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNull() {
            addCriterion("app_name is null");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNotNull() {
            addCriterion("app_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppNameEqualTo(String value) {
            addCriterion("app_name =", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotEqualTo(String value) {
            addCriterion("app_name <>", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThan(String value) {
            addCriterion("app_name >", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThanOrEqualTo(String value) {
            addCriterion("app_name >=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThan(String value) {
            addCriterion("app_name <", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThanOrEqualTo(String value) {
            addCriterion("app_name <=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLike(String value) {
            addCriterion("app_name like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotLike(String value) {
            addCriterion("app_name not like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameIn(List<String> values) {
            addCriterion("app_name in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotIn(List<String> values) {
            addCriterion("app_name not in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameBetween(String value1, String value2) {
            addCriterion("app_name between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotBetween(String value1, String value2) {
            addCriterion("app_name not between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppMacIsNull() {
            addCriterion("app_mac is null");
            return (Criteria) this;
        }

        public Criteria andAppMacIsNotNull() {
            addCriterion("app_mac is not null");
            return (Criteria) this;
        }

        public Criteria andAppMacEqualTo(String value) {
            addCriterion("app_mac =", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacNotEqualTo(String value) {
            addCriterion("app_mac <>", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacGreaterThan(String value) {
            addCriterion("app_mac >", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacGreaterThanOrEqualTo(String value) {
            addCriterion("app_mac >=", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacLessThan(String value) {
            addCriterion("app_mac <", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacLessThanOrEqualTo(String value) {
            addCriterion("app_mac <=", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacLike(String value) {
            addCriterion("app_mac like", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacNotLike(String value) {
            addCriterion("app_mac not like", value, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacIn(List<String> values) {
            addCriterion("app_mac in", values, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacNotIn(List<String> values) {
            addCriterion("app_mac not in", values, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacBetween(String value1, String value2) {
            addCriterion("app_mac between", value1, value2, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppMacNotBetween(String value1, String value2) {
            addCriterion("app_mac not between", value1, value2, "appMac");
            return (Criteria) this;
        }

        public Criteria andAppImeiIsNull() {
            addCriterion("app_imei is null");
            return (Criteria) this;
        }

        public Criteria andAppImeiIsNotNull() {
            addCriterion("app_imei is not null");
            return (Criteria) this;
        }

        public Criteria andAppImeiEqualTo(String value) {
            addCriterion("app_imei =", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiNotEqualTo(String value) {
            addCriterion("app_imei <>", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiGreaterThan(String value) {
            addCriterion("app_imei >", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiGreaterThanOrEqualTo(String value) {
            addCriterion("app_imei >=", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiLessThan(String value) {
            addCriterion("app_imei <", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiLessThanOrEqualTo(String value) {
            addCriterion("app_imei <=", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiLike(String value) {
            addCriterion("app_imei like", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiNotLike(String value) {
            addCriterion("app_imei not like", value, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiIn(List<String> values) {
            addCriterion("app_imei in", values, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiNotIn(List<String> values) {
            addCriterion("app_imei not in", values, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiBetween(String value1, String value2) {
            addCriterion("app_imei between", value1, value2, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppImeiNotBetween(String value1, String value2) {
            addCriterion("app_imei not between", value1, value2, "appImei");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIsNull() {
            addCriterion("app_phone is null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIsNotNull() {
            addCriterion("app_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneEqualTo(String value) {
            addCriterion("app_phone =", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotEqualTo(String value) {
            addCriterion("app_phone <>", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneGreaterThan(String value) {
            addCriterion("app_phone >", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("app_phone >=", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLessThan(String value) {
            addCriterion("app_phone <", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLessThanOrEqualTo(String value) {
            addCriterion("app_phone <=", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLike(String value) {
            addCriterion("app_phone like", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotLike(String value) {
            addCriterion("app_phone not like", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIn(List<String> values) {
            addCriterion("app_phone in", values, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotIn(List<String> values) {
            addCriterion("app_phone not in", values, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneBetween(String value1, String value2) {
            addCriterion("app_phone between", value1, value2, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotBetween(String value1, String value2) {
            addCriterion("app_phone not between", value1, value2, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeIsNull() {
            addCriterion("app_readable_code is null");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeIsNotNull() {
            addCriterion("app_readable_code is not null");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeEqualTo(String value) {
            addCriterion("app_readable_code =", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeNotEqualTo(String value) {
            addCriterion("app_readable_code <>", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeGreaterThan(String value) {
            addCriterion("app_readable_code >", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeGreaterThanOrEqualTo(String value) {
            addCriterion("app_readable_code >=", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeLessThan(String value) {
            addCriterion("app_readable_code <", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeLessThanOrEqualTo(String value) {
            addCriterion("app_readable_code <=", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeLike(String value) {
            addCriterion("app_readable_code like", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeNotLike(String value) {
            addCriterion("app_readable_code not like", value, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeIn(List<String> values) {
            addCriterion("app_readable_code in", values, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeNotIn(List<String> values) {
            addCriterion("app_readable_code not in", values, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeBetween(String value1, String value2) {
            addCriterion("app_readable_code between", value1, value2, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppReadableCodeNotBetween(String value1, String value2) {
            addCriterion("app_readable_code not between", value1, value2, "appReadableCode");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(Integer value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(Integer value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(Integer value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(Integer value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(Integer value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<Integer> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<Integer> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(Integer value1, Integer value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("app_type not between", value1, value2, "appType");
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