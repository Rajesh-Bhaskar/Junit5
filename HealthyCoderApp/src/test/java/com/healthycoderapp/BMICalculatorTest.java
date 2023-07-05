package com.healthycoderapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String enviroment = "dev";

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all unit Test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all unit Test");
    }

    @Nested
    class  IsDietRecommendedTest {
        @Test
        void Should_ReturnTrue_When_DietReccomended(){

            // given

            double weight = 89.0;
            double height = 1.72;


            //when

            boolean reccomended = BMICalculator.isDietRecommended(weight,height);


            //then
            assertTrue(reccomended);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }

        @Test
        void Should_ReturnFalse_When_DietReccomended(){

            // given

            double weight = 50;
            double height = 1.92;


            //when

            boolean reccomended = BMICalculator.isDietRecommended(weight,height);


            //then
            assertFalse(reccomended);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }

        @Test
        void Should_ThrowartichmaticException_When_DietReccomended(){

            // given

            double weight = 50;
            double height = 0.0;


            //when

            Executable executable = () -> BMICalculator.isDietRecommended(weight,height);


            //then
            assertThrows(ArithmeticException.class,executable);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }

        @ParameterizedTest
        @ValueSource(doubles = {89.0,95.0,110.0})
        void Should_ReturnTrue_When_DietReccomended(double coderWieght){

            // given

            double weight = coderWieght;
            double height = 1.72;


            //when

            boolean reccomended = BMICalculator.isDietRecommended(weight,height);


            //then
            assertTrue(reccomended);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }

        @ParameterizedTest(name = "weight={0},height={1}")
        @CsvSource(value = {"89.0,1.72","95.0,1.75","110.0,1.78"})
        void Should_ReturnTrue_When_DietReccomendedMutipleValue(double coderWieght,double coderheight){

            // given

            double weight = coderWieght;
            double height = coderheight;


            //when

            boolean reccomended = BMICalculator.isDietRecommended(weight,height);


            //then
            assertTrue(reccomended);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }

        @ParameterizedTest(name = "weight={0},height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip  = 1)
        void Should_ReturnTrue_When_DietReccomendedFilesystem(double coderWieght,double coderheight){

            // given

            double weight = coderWieght;
            double height = coderheight;


            //when

            boolean reccomended = BMICalculator.isDietRecommended(weight,height);


            //then
            assertTrue(reccomended);
            //assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
        }
    }

    @Nested
    @DisplayName(">>> sample inner class display name ")
    class FindCoderWithWorstBMITests{
        @Test
        @DisplayName(">>>>> Sample method display name")
        @Disabled
        void should_ReturnCoderWithWorstBMI_When_CoderListnotEmpty(){
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,64.7));
            //when
            Coder coderworstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertAll(
                    () ->  assertEquals(1.82,coderworstBMI.getHeight()),
                    () ->assertEquals(98.0,coderworstBMI.getWeight())
            );
        }

        @Test
        void should_ReturnNullWorstBMI_When_CoderListEmpty(){
            //given
            List<Coder> coders = new ArrayList<>();

            //when
            Coder coderworstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertNull(coderworstBMI);
        }

        @Test
        void should_ReturnCoderWithWorstBMIin1ms_When_CoderListhas10000Elemets(){
            //given
            assumeTrue(BMICalculatorTest.this.enviroment.equals("prod"));
            List<Coder> coders =  new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0+i, 10.0+i));
            }
            //when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(500) , executable);
        }
    }


    @Nested
    class BMICalculatorTestclass{
        @Test
        void should_ReturnShouldreturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,64.7));
            double[] expected = {18.52,29.59,19.53};
            //when
            double[]  BMIsCORES  = BMICalculator.getBMIScores(coders);
            //then
            assertArrayEquals(expected, BMIsCORES);
        }
    }







}