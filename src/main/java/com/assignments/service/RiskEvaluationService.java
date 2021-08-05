package com.assignments.service;

import com.assignments.api.RulesController;
import com.assignments.entity.RiskCategoryData;
import com.assignments.entity.RuleData;
import com.assignments.model.BusinessUseCases;
import com.assignments.model.Category;
import com.assignments.model.UserDetails;
import com.assignments.repository.RiskCategoryRepository;
import com.assignments.repository.RulesRepository;
import io.swagger.models.auth.In;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RiskEvaluationService {

    @Autowired
    RulesRepository rulesRepository;

    @Autowired
    RiskCategoryRepository riskCategoryRepository;

    public Map<BusinessUseCases, Integer> getRiskScore(UserDetails details){
        Map<BusinessUseCases,Integer> map = new HashMap<>();
        for (BusinessUseCases businessUseCase :BusinessUseCases.values()
             ) {
            List<RuleData> ruleData=rulesRepository.findAllByBusinessUse(businessUseCase.name());
            Integer score = ruleData.stream().filter(m-> MVEL.evalToBoolean(m.getRuleExpr(),details))
                    .map(m->m.getRiskScore()).reduce(Integer::sum).orElse(0);
            map.put(businessUseCase,score);
        }
        return map;
    }

    public Map<BusinessUseCases, String> getRiskCategory(UserDetails details){

        Map<BusinessUseCases,Integer> riskScore =getRiskScore(details);
        Map<BusinessUseCases,String> riskCategory = new HashMap<>();
        List<RiskCategoryData> riskCategoryData = riskCategoryRepository.findAll();
        riskScore.entrySet().forEach(e-> {
            Map<String,Integer> score = new HashMap<>();
            score.put("riskscore",e.getValue());
            Optional<RiskCategoryData> data=riskCategoryData.stream().filter(m -> MVEL.evalToBoolean(m.getRule(),score)).findFirst();
            riskCategory.put(e.getKey(),data.isPresent()?data.get().getCategory():Category.InEligible.name());
        });

        return riskCategory;
    }

    public void addRiskCategory(List<RiskCategoryData> data){
        riskCategoryRepository.saveAll(data);
    }
}
