package com.assignments.utils;

import com.assignments.model.BooleanBasedRuleVariables;
import com.assignments.model.EnumBasedRuleVariables;
import com.assignments.model.RangeBasedRuleVariables;
import com.assignments.model.RuleVariables;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class RuleUtils {

   public static RuleVariables getRulePayload(String input,String ruleType){

        Gson gson = new Gson();
       switch (ruleType){
           case "range":
               return gson.fromJson(input,RangeBasedRuleVariables.class);
           case "boolean":
               return gson.fromJson(input,BooleanBasedRuleVariables.class);
           case "list":
               return gson.fromJson(input,EnumBasedRuleVariables.class);
       }
       return null;
   }

}
