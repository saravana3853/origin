package com.assignments.repository;

import com.assignments.entity.RiskCategoryData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RiskCategoryRepository extends MongoRepository<RiskCategoryData,String> {
}
