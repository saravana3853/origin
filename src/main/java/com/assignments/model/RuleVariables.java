package com.assignments.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public abstract class RuleVariables {

    String varName;

    String varType;

    String businessUse;

    public abstract Map<String,Integer> toMvelExpression();
}
