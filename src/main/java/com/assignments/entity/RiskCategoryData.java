package com.assignments.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter
@Getter
@Document(collection = "risk_category")
public class RiskCategoryData {

    @Id
    String id;

    String category;

    String rule;
}
