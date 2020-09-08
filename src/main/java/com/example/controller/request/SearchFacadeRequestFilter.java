package com.example.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Valid
public class SearchFacadeRequestFilter {

    @NotNull
    private String attribute;
    @NotNull
    private String operator;
    private String value;
    @Valid
    private Range range;

    @AssertTrue(message = "Valid operators: eq, gte, lte.")
    public boolean onlyValidOperators() {
        return "eq".equals(operator)
            || "gte".equals(operator)
            || "lte".equals(operator);
    }

    @AssertTrue(message = "Both 'value' and 'range' are not allowed.")
    public boolean bothValueAndRangeAreNotAllowed() {
        return !(value != null && range != null);
    }

    @AssertTrue(message = "When operator is 'gte' or 'lte' then 'range' is not allowed.")
    public boolean gteOrLteOperatorForcesRangeToBeNull() {
        return ("gte".equals(operator) || "lte".equals(operator))
            && range == null;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public static class Range {

        @NotNull
        private String from;
        @NotNull
        private String to;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
