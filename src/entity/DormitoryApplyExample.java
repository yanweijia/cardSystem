package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormitoryApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public DormitoryApplyExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Integer value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Integer value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Integer value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Integer value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Integer> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Integer> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRadnomIsNull() {
            addCriterion("radnom is null");
            return (Criteria) this;
        }

        public Criteria andRadnomIsNotNull() {
            addCriterion("radnom is not null");
            return (Criteria) this;
        }

        public Criteria andRadnomEqualTo(Boolean value) {
            addCriterion("radnom =", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomNotEqualTo(Boolean value) {
            addCriterion("radnom <>", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomGreaterThan(Boolean value) {
            addCriterion("radnom >", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomGreaterThanOrEqualTo(Boolean value) {
            addCriterion("radnom >=", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomLessThan(Boolean value) {
            addCriterion("radnom <", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomLessThanOrEqualTo(Boolean value) {
            addCriterion("radnom <=", value, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomIn(List<Boolean> values) {
            addCriterion("radnom in", values, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomNotIn(List<Boolean> values) {
            addCriterion("radnom not in", values, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomBetween(Boolean value1, Boolean value2) {
            addCriterion("radnom between", value1, value2, "radnom");
            return (Criteria) this;
        }

        public Criteria andRadnomNotBetween(Boolean value1, Boolean value2) {
            addCriterion("radnom not between", value1, value2, "radnom");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIsNull() {
            addCriterion("building_no is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIsNotNull() {
            addCriterion("building_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoEqualTo(String value) {
            addCriterion("building_no =", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotEqualTo(String value) {
            addCriterion("building_no <>", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoGreaterThan(String value) {
            addCriterion("building_no >", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoGreaterThanOrEqualTo(String value) {
            addCriterion("building_no >=", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLessThan(String value) {
            addCriterion("building_no <", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLessThanOrEqualTo(String value) {
            addCriterion("building_no <=", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLike(String value) {
            addCriterion("building_no like", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotLike(String value) {
            addCriterion("building_no not like", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIn(List<String> values) {
            addCriterion("building_no in", values, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotIn(List<String> values) {
            addCriterion("building_no not in", values, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoBetween(String value1, String value2) {
            addCriterion("building_no between", value1, value2, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotBetween(String value1, String value2) {
            addCriterion("building_no not between", value1, value2, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoIsNull() {
            addCriterion("dormitory_no is null");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoIsNotNull() {
            addCriterion("dormitory_no is not null");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoEqualTo(String value) {
            addCriterion("dormitory_no =", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoNotEqualTo(String value) {
            addCriterion("dormitory_no <>", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoGreaterThan(String value) {
            addCriterion("dormitory_no >", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoGreaterThanOrEqualTo(String value) {
            addCriterion("dormitory_no >=", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoLessThan(String value) {
            addCriterion("dormitory_no <", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoLessThanOrEqualTo(String value) {
            addCriterion("dormitory_no <=", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoLike(String value) {
            addCriterion("dormitory_no like", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoNotLike(String value) {
            addCriterion("dormitory_no not like", value, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoIn(List<String> values) {
            addCriterion("dormitory_no in", values, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoNotIn(List<String> values) {
            addCriterion("dormitory_no not in", values, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoBetween(String value1, String value2) {
            addCriterion("dormitory_no between", value1, value2, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andDormitoryNoNotBetween(String value1, String value2) {
            addCriterion("dormitory_no not between", value1, value2, "dormitoryNo");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("apply_time is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(Date value) {
            addCriterion("apply_time =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(Date value) {
            addCriterion("apply_time <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(Date value) {
            addCriterion("apply_time >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_time >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(Date value) {
            addCriterion("apply_time <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("apply_time <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<Date> values) {
            addCriterion("apply_time in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<Date> values) {
            addCriterion("apply_time not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(Date value1, Date value2) {
            addCriterion("apply_time between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("apply_time not between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Boolean value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Boolean value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Boolean value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Boolean value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Boolean value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Boolean> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Boolean> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Boolean value1, Boolean value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("result not between", value1, value2, "result");
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