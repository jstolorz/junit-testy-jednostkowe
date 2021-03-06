package org.bluesoft.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountedPrice);
        assertThat(discountedPrice, equalTo(28));
    }

    @Test
    void referencesToTheSomeObjectShouldBeEqual(){

        //given + when
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1,meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void referencesToTheDiffrentObjectShouldNotBeEqual(){

        //given + when
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(33);

        //then
        assertNotSame(meal1,meal2);
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(20,"Kebab");
        Meal meal2 = new Meal(20,"Kebab");

        //then
        assertEquals(meal1,meal2);
    }


    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice(){
        //given
        Meal meal = new Meal(8,"soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(23));
    }

    @ParameterizedTest
    @ValueSource(ints = {5,10,15,18})
    void mealPricesShouldBeLowerThan20(int price){
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price,greaterThanOrEqualTo(10));
    }

    @Tag("Fries")
    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger",10),
                Arguments.of("Cheeseburger",18)
        );
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeName = Arrays.asList("Cheesecake","Fruitcake","Cupcake");
        return cakeName.stream();
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {3,1,5,8})
    void mealPricesShouldBeLowerThan10(int price){

        if(price > 5){
            throw  new IllegalArgumentException();
        }

        assertThat(price, lessThan(10));
    }

    @Tag("Fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
       //given
       Order order = new Order();
       order.addMealToOrder(new Meal(10,2,"Hamburger"));
       order.addMealToOrder(new Meal(7,4,"Fries"));
       order.addMealToOrder(new Meal(22,3,"Pizza"));

       Collection<DynamicTest> dynamicTests = new ArrayList<>();

       for (int i = 0; i < order.getMeals().size(); i++){
           int price = order.getMeals().get(i).getPrice();
           int quantity = order.getMeals().get(i).getQuantity();

           Executable executable = () -> {
             assertThat(calculatePrice(price,quantity), lessThan(67));
           };

           String name = "Test name: " + i;

           DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
           dynamicTests.add(dynamicTest);
       }

       return dynamicTests;
    }

    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }



}
