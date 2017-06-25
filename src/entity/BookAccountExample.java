package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BookAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public BookAccountExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumIsNull() {
            addCriterion("max_borrow_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumIsNotNull() {
            addCriterion("max_borrow_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumEqualTo(Short value) {
            addCriterion("max_borrow_num =", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumNotEqualTo(Short value) {
            addCriterion("max_borrow_num <>", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumGreaterThan(Short value) {
            addCriterion("max_borrow_num >", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumGreaterThanOrEqualTo(Short value) {
            addCriterion("max_borrow_num >=", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumLessThan(Short value) {
            addCriterion("max_borrow_num <", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumLessThanOrEqualTo(Short value) {
            addCriterion("max_borrow_num <=", value, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumIn(List<Short> values) {
            addCriterion("max_borrow_num in", values, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumNotIn(List<Short> values) {
            addCriterion("max_borrow_num not in", values, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumBetween(Short value1, Short value2) {
            addCriterion("max_borrow_num between", value1, value2, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andMaxBorrowNumNotBetween(Short value1, Short value2) {
            addCriterion("max_borrow_num not between", value1, value2, "maxBorrowNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumIsNull() {
            addCriterion("borrowed_num is null");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumIsNotNull() {
            addCriterion("borrowed_num is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumEqualTo(Short value) {
            addCriterion("borrowed_num =", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumNotEqualTo(Short value) {
            addCriterion("borrowed_num <>", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumGreaterThan(Short value) {
            addCriterion("borrowed_num >", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumGreaterThanOrEqualTo(Short value) {
            addCriterion("borrowed_num >=", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumLessThan(Short value) {
            addCriterion("borrowed_num <", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumLessThanOrEqualTo(Short value) {
            addCriterion("borrowed_num <=", value, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumIn(List<Short> values) {
            addCriterion("borrowed_num in", values, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumNotIn(List<Short> values) {
            addCriterion("borrowed_num not in", values, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumBetween(Short value1, Short value2) {
            addCriterion("borrowed_num between", value1, value2, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andBorrowedNumNotBetween(Short value1, Short value2) {
            addCriterion("borrowed_num not between", value1, value2, "borrowedNum");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNull() {
            addCriterion("register_date is null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNotNull() {
            addCriterion("register_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateEqualTo(Date value) {
            addCriterionForJDBCDate("register_date =", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("register_date <>", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThan(Date value) {
            addCriterionForJDBCDate("register_date >", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("register_date >=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThan(Date value) {
            addCriterionForJDBCDate("register_date <", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("register_date <=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIn(List<Date> values) {
            addCriterionForJDBCDate("register_date in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("register_date not in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("register_date between", value1, value2, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("register_date not between", value1, value2, "registerDate");
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