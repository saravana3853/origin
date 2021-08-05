package com.assignments.main;


import com.assignments.model.BooleanBasedRuleVariables;
import com.assignments.model.Range;
import com.assignments.model.RangeBasedRuleVariables;
import com.assignments.model.UserDetails;
import com.google.gson.Gson;
import org.mvel2.MVEL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignments")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.assignments.entity" )
@EnableMongoRepositories(basePackages = "com.assignments.repository")
@EnableSwagger2
@Configuration
public class OriginMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        return super.run(application);
    }

    public static void main(String[] args) {
        SpringApplication.run(OriginMain.class, args);

        String expression = "foobar > 99 ? foobar :99";

        Map vars = new HashMap();
        vars.put("foobar", new Integer(100));

        // We know this expression should return a boolean.
        Integer result = (Integer) MVEL.eval(expression, vars);
        System.out.println(result);

        List<Range> scores = new ArrayList<>();

        Range range = new Range();
        range.setLower(200000l);
        range.setUpper(500000l);
        range.setScore(-1);

        scores.add(range);

        Range range2 = new Range();
        range2.setLower(1l);
        range2.setUpper(199999l);
        range2.setScore(0);

        scores.add(range2);

        RangeBasedRuleVariables variables = new RangeBasedRuleVariables();
        variables.setVarName("income");
        variables.setVarType("range");
        variables.setScores(scores);

        Gson gson = new Gson();
        String jsonStr=gson.toJson(variables);
        System.out.println(jsonStr);

        Map<String,Integer> list = variables.toMvelExpression();
        System.out.println(list);

        UserDetails userDetails = new UserDetails();
        userDetails.setIncome(100000l);
        for (String expr: list.keySet()) {
            System.out.println("("+expr+"):"+MVEL.eval(expr,userDetails));
        }

        Map<Boolean,Integer> booleanVariables = new HashMap<>();
        booleanVariables.put(Boolean.TRUE,-1);
        booleanVariables.put(Boolean.FALSE,0);

        BooleanBasedRuleVariables booleanBasedRuleVariables = new BooleanBasedRuleVariables();
        booleanBasedRuleVariables.setVarName("married");
        booleanBasedRuleVariables.setVarType("boolean");
        booleanBasedRuleVariables.setScores(booleanVariables);

        System.out.println(new Gson().toJson(booleanBasedRuleVariables));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
