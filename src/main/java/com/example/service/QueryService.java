package com.example.service;

import com.example.controller.request.SearchFacadeRequestFilter;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService<E> {

    private final MongoTemplate mongoTemplate;
    private final Class<E> genericType;

    @SuppressWarnings("unchecked")
    public QueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.genericType = (Class <E>) GenericTypeResolver.resolveTypeArgument(getClass(), QueryService.class);
    }

    public List<E> query(SearchFacadeRequestFilter filter) {
        return mongoTemplate.find(FilterCriteria.from(filter).toQuery(), genericType);
    }

    public List<E> query(List<SearchFacadeRequestFilter> filters) {
        FilterCriteria criteria = FilterCriteria.empty();
        for (SearchFacadeRequestFilter filter : filters) {
            criteria = criteria.and(filter);
        }
        return mongoTemplate.find(criteria.toQuery(), genericType);
    }
}
