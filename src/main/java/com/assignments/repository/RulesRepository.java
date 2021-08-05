package com.assignments.repository;

import com.assignments.entity.RuleData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends MongoRepository<RuleData,String> {

    List<RuleData> findAllByBusinessUse(String businessUse);
}
