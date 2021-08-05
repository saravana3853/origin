package com.assignments.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "rules_collection")
public class RuleData {

    @Id
    String ruleId;

    String variableName;

    String ruleType;

    String ruleExpr;

    String businessUse;

    int riskScore;

}
