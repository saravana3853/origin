package com.assignments.api;

import com.assignments.entity.RiskCategoryData;
import com.assignments.model.BusinessUseCases;
import com.assignments.model.UserDetails;
import com.assignments.service.RiskEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/risk")
public class RiskEvaluationController {

    @Autowired
    RiskEvaluationService riskEvaluationService;

    @PostMapping("/getRiskScore")
    public Map<BusinessUseCases, Integer> getRiskScore(@RequestBody UserDetails details){
        return riskEvaluationService.getRiskScore(details);
    }

    @PostMapping("/addRiskCategory")
    public ResponseEntity addRiskCategory(@RequestBody List<RiskCategoryData> details){
         riskEvaluationService.addRiskCategory(details);
         return ResponseEntity.ok().build();
    }

    @PostMapping("/getRiskCategory")
    public ResponseEntity addRiskCategory(@RequestBody UserDetails details){
        return ResponseEntity.ok(riskEvaluationService.getRiskCategory(details));
    }
}
