package com.example.service;

import com.example.controller.request.SearchFacadeRequestFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FilterCriteria {

    private final Criteria criteria;

    public FilterCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public static FilterCriteria from(SearchFacadeRequestFilter filter) {
        return new FilterCriteria(process(filter));
    }

    private static Criteria process(SearchFacadeRequestFilter filter) {
        switch (filter.getOperator()) {
            case "eq":
                return eq(filter);
            case "gte":
                return gte(filter);
            case "lte":
                return lte(filter);
        }
        throw new IllegalArgumentException("Unknown operator");
    }

    private static Criteria eq(SearchFacadeRequestFilter filter) {
        Criteria criteria;
        if (filter.getValue() != null) {
            criteria = Criteria.where(filter.getAttribute()).is(filter.getValue());
        } else {
            criteria = Criteria.where(filter.getAttribute()).gte(filter.getRange().getFrom())
                .lt(filter.getRange().getTo());
        }
        return criteria;
    }

    private static Criteria gte(SearchFacadeRequestFilter filter) {
        return Criteria.where(filter.getAttribute()).gte(filter.getValue());
    }

    private static Criteria lte(SearchFacadeRequestFilter filter) {
        return Criteria.where(filter.getAttribute()).lte(filter.getValue());
    }

    public static FilterCriteria empty() {
        return new FilterCriteria(new Criteria());
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public FilterCriteria and(SearchFacadeRequestFilter filter) {
        return new FilterCriteria(this.criteria.andOperator(process(filter)));
    }

    public Query toQuery() {
        Query query = new Query();
        query.addCriteria(this.criteria);
        return query;
    }
}
