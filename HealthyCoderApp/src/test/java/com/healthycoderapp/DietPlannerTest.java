package com.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;
    @BeforeEach
    void setup(){
        this.dietPlanner =  new DietPlanner(20,30,50);
    }

    @AfterEach
    void afterEach(){
        System.out.println("A unit test case was finished");
    }
    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void ShouldReturn_dietplan_when_correctCoder(){
        //given
        Coder coder = new Coder(1.82,75.0,26,Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);
        //when
        DietPlan actual =  dietPlanner.calculateDiet(coder);

        //then
        assertAll(
                () ->  assertEquals(expected.getCalories(),actual.getCalories()),
                () ->  assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate()),
                () ->  assertEquals(expected.getFat(),actual.getFat()),
                () ->  assertEquals(expected.getProtein(),actual.getProtein())
        );

    }

}