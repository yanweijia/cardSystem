package entity;

import java.util.ArrayList;
import java.util.List;

public class DormitoryUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public DormitoryUserExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andBuilding_noIsNull() {
            addCriterion("building_no is null");
            return (Criteria) this;
        }

        public Criteria andBuilding_noIsNotNull() {
            addCriterion("building_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuilding_noEqualTo(String value) {
            addCriterion("building_no =", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noNotEqualTo(String value) {
            addCriterion("building_no <>", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noGreaterThan(String value) {
            addCriterion("building_no >", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noGreaterThanOrEqualTo(String value) {
            addCriterion("building_no >=", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noLessThan(String value) {
            addCriterion("building_no <", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noLessThanOrEqualTo(String value) {
            addCriterion("building_no <=", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noLike(String value) {
            addCriterion("building_no like", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noNotLike(String value) {
            addCriterion("building_no not like", value, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noIn(List<String> values) {
            addCriterion("building_no in", values, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noNotIn(List<String> values) {
            addCriterion("building_no not in", values, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noBetween(String value1, String value2) {
            addCriterion("building_no between", value1, value2, "building_no");
            return (Criteria) this;
        }

        public Criteria andBuilding_noNotBetween(String value1, String value2) {
            addCriterion("building_no not between", value1, value2, "building_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noIsNull() {
            addCriterion("dormitory_no is null");
            return (Criteria) this;
        }

        public Criteria andDormitory_noIsNotNull() {
            addCriterion("dormitory_no is not null");
            return (Criteria) this;
        }

        public Criteria andDormitory_noEqualTo(String value) {
            addCriterion("dormitory_no =", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noNotEqualTo(String value) {
            addCriterion("dormitory_no <>", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noGreaterThan(String value) {
            addCriterion("dormitory_no >", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noGreaterThanOrEqualTo(String value) {
            addCriterion("dormitory_no >=", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noLessThan(String value) {
            addCriterion("dormitory_no <", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noLessThanOrEqualTo(String value) {
            addCriterion("dormitory_no <=", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noLike(String value) {
            addCriterion("dormitory_no like", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noNotLike(String value) {
            addCriterion("dormitory_no not like", value, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noIn(List<String> values) {
            addCriterion("dormitory_no in", values, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noNotIn(List<String> values) {
            addCriterion("dormitory_no not in", values, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noBetween(String value1, String value2) {
            addCriterion("dormitory_no between", value1, value2, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andDormitory_noNotBetween(String value1, String value2) {
            addCriterion("dormitory_no not between", value1, value2, "dormitory_no");
            return (Criteria) this;
        }

        public Criteria andBed_noIsNull() {
            addCriterion("bed_no is null");
            return (Criteria) this;
        }

        public Criteria andBed_noIsNotNull() {
            addCriterion("bed_no is not null");
            return (Criteria) this;
        }

        public Criteria andBed_noEqualTo(String value) {
            addCriterion("bed_no =", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noNotEqualTo(String value) {
            addCriterion("bed_no <>", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noGreaterThan(String value) {
            addCriterion("bed_no >", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noGreaterThanOrEqualTo(String value) {
            addCriterion("bed_no >=", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noLessThan(String value) {
            addCriterion("bed_no <", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noLessThanOrEqualTo(String value) {
            addCriterion("bed_no <=", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noLike(String value) {
            addCriterion("bed_no like", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noNotLike(String value) {
            addCriterion("bed_no not like", value, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noIn(List<String> values) {
            addCriterion("bed_no in", values, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noNotIn(List<String> values) {
            addCriterion("bed_no not in", values, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noBetween(String value1, String value2) {
            addCriterion("bed_no between", value1, value2, "bed_no");
            return (Criteria) this;
        }

        public Criteria andBed_noNotBetween(String value1, String value2) {
            addCriterion("bed_no not between", value1, value2, "bed_no");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(String value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(String value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(String value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(String value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLike(String value) {
            addCriterion("user_id like", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotLike(String value) {
            addCriterion("user_id not like", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<String> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<String> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }
    }

    /**
     */
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