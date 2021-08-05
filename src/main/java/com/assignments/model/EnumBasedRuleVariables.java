package com.assignments.model;

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
public class EnumBasedRuleVariables extends RuleVariables{

    Map<String,Integer> scores = new HashMap<>();

    @Override
    public Map<String,Integer> toMvelExpression(){
        Map<String,Integer> expressions = new HashMap<>();
        for (String acceptedValue :
                scores.keySet()) {
            expressions.put(varName+"== '"+acceptedValue+"'",scores.get(acceptedValue));
        }
        return expressions;
    }
}
