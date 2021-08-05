package com.assignments.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetails {

    Long income;

    Boolean married=Boolean.FALSE;

    HouseOwnership home;

    int age;

    Boolean dependants=Boolean.FALSE;


}
