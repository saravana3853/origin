package com.assignments.service;

import com.assignments.entity.RuleData;
import com.assignments.model.BusinessUseCases;
import com.assignments.model.RuleVariables;
import com.assignments.repository.RulesRepository;
import com.assignments.utils.RuleUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class RulesService {

    @Autowired
    RulesRepository rulesRepository;

    public void addRules(String input,String ruleType){
        RuleVariables variables= RuleUtils.getRulePayload(input,ruleType);
        Map<String,Integer> expressions =variables.toMvelExpression();
        List<RuleData> ruleDatas= getRulesData(variables, expressions);
        rulesRepository.saveAll(ruleDatas);
    }

    public void addBulkRuleSet(String input){
        Gson gson = new Gson();
        JsonArray jsonArray=gson.fromJson(input, JsonArray.class);
        Iterator<JsonElement> iterator = jsonArray.iterator();
        List<RuleData> allRuleSet = new ArrayList<>();
        while(iterator.hasNext()){
            JsonObject element =  iterator.next().getAsJsonObject();
            RuleVariables variables=RuleUtils.getRulePayload(element.toString(),element.get("varType").getAsString());
            List<RuleData> ruleData = getRulesData(variables,variables.toMvelExpression());
            allRuleSet.addAll(ruleData);
        }
        rulesRepository.saveAll(allRuleSet);
    }

    public List<RuleData> getRuleSet(BusinessUseCases input){
      List<RuleData> ruleData = rulesRepository.findAllByBusinessUse(input.name());
      return ruleData;
    }

    private List<RuleData> getRulesData(RuleVariables variables, Map<String, Integer> expressions) {
        List<RuleData> ruleDatas = new ArrayList<>();
        for (String expr: expressions.keySet()) {
            RuleData ruleData =new RuleData();
            ruleData.setBusinessUse(variables.getBusinessUse());
            ruleData.setRuleExpr(expr);
            ruleData.setRiskScore(expressions.get(expr));
            ruleData.setVariableName(variables.getVarName());
            ruleData.setRuleType(variables.getVarType());
            ruleDatas.add(ruleData);
        }
        return ruleDatas;
    }

    public void getRuleSet(String businessUse){

    }
}
