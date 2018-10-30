package com.tct.db.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebUserLoginExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebUserLoginExample() {
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

        public Criteria andWebIpIsNull() {
            addCriterion("web_ip is null");
            return (Criteria) this;
        }

        public Criteria andWebIpIsNotNull() {
            addCriterion("web_ip is not null");
            return (Criteria) this;
        }

        public Criteria andWebIpEqualTo(String value) {
            addCriterion("web_ip =", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpNotEqualTo(String value) {
            addCriterion("web_ip <>", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpGreaterThan(String value) {
            addCriterion("web_ip >", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpGreaterThanOrEqualTo(String value) {
            addCriterion("web_ip >=", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpLessThan(String value) {
            addCriterion("web_ip <", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpLessThanOrEqualTo(String value) {
            addCriterion("web_ip <=", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpLike(String value) {
            addCriterion("web_ip like", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpNotLike(String value) {
            addCriterion("web_ip not like", value, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpIn(List<String> values) {
            addCriterion("web_ip in", values, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpNotIn(List<String> values) {
            addCriterion("web_ip not in", values, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpBetween(String value1, String value2) {
            addCriterion("web_ip between", value1, value2, "webIp");
            return (Criteria) this;
        }

        public Criteria andWebIpNotBetween(String value1, String value2) {
            addCriterion("web_ip not between", value1, value2, "webIp");
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

        public Criteria andLogintimeIsNull() {
            addCriterion("logintime is null");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNotNull() {
            addCriterion("logintime is not null");
            return (Criteria) this;
        }

        public Criteria andLogintimeEqualTo(Date value) {
            addCriterion("logintime =", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotEqualTo(Date value) {
            addCriterion("logintime <>", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThan(Date value) {
            addCriterion("logintime >", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("logintime >=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThan(Date value) {
            addCriterion("logintime <", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThanOrEqualTo(Date value) {
            addCriterion("logintime <=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeIn(List<Date> values) {
            addCriterion("logintime in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotIn(List<Date> values) {
            addCriterion("logintime not in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeBetween(Date value1, Date value2) {
            addCriterion("logintime between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotBetween(Date value1, Date value2) {
            addCriterion("logintime not between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeIsNull() {
            addCriterion("logouttime is null");
            return (Criteria) this;
        }

        public Criteria andLogouttimeIsNotNull() {
            addCriterion("logouttime is not null");
            return (Criteria) this;
        }

        public Criteria andLogouttimeEqualTo(Date value) {
            addCriterion("logouttime =", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeNotEqualTo(Date value) {
            addCriterion("logouttime <>", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeGreaterThan(Date value) {
            addCriterion("logouttime >", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("logouttime >=", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeLessThan(Date value) {
            addCriterion("logouttime <", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeLessThanOrEqualTo(Date value) {
            addCriterion("logouttime <=", value, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeIn(List<Date> values) {
            addCriterion("logouttime in", values, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeNotIn(List<Date> values) {
            addCriterion("logouttime not in", values, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeBetween(Date value1, Date value2) {
            addCriterion("logouttime between", value1, value2, "logouttime");
            return (Criteria) this;
        }

        public Criteria andLogouttimeNotBetween(Date value1, Date value2) {
            addCriterion("logouttime not between", value1, value2, "logouttime");
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