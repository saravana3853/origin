package com.assignments.api;

import com.assignments.model.BusinessUseCases;
import com.assignments.model.RuleVariables;
import com.assignments.service.RulesService;
import com.assignments.utils.RuleUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Iterator;

@RestController
@RequestMapping("/rules")
public class RulesController {

    @Autowired
    RulesService rulesService;

    @GetMapping("/test")
    public ResponseEntity addRules(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/addRules")
    public ResponseEntity addRules(@RequestBody String input){
        Gson gson = new Gson();
        JsonObject jsonObject=gson.fromJson(input, JsonObject.class);
        String ruleType=jsonObject.get("varType").getAsString();
        rulesService.addRules(input,ruleType);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addBulkRuleSet")
    public ResponseEntity addBulkRuleSet(@RequestBody String input){
        rulesService.addBulkRuleSet(input);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getRuleSet")
    public ResponseEntity getRuleSet(@RequestParam BusinessUseCases businessUse){
        return ResponseEntity.ok(rulesService.getRuleSet(businessUse));
    }
}
