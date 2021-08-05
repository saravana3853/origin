package com.assignments.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class BooleanBasedRuleVariables extends RuleVariables{

    Map<Boolean,Integer> scores = new HashMap<>();

    @Override
    public Map<String,Integer> toMvelExpression(){
        Map<String,Integer> expressions = new HashMap<>();
        for (Boolean value :
                scores.keySet()) {
            expressions.put(varName+"=="+value,scores.get(value));
        }
        return expressions;
    }
}
