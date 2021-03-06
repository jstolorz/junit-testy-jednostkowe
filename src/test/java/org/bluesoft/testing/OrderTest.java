package org.bluesoft.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Tag("Fries")
@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    Order order;

    @BeforeEach
    void initializeOrder(){
        System.out.println("Before each");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("After each");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals(){

        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1,ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){

        //given
        //Order order = new Order();

        //then
        // Hamcrest
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));

        // assertJ
        //assertThat(order.getMeals()).isEmpty();
    }

    @Test
    void addingMealToOrderShouldIncreseOrderSize(){

        //given
        Meal meal = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Juice");

        //Order order = new Order();

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), hasSize(2));
        assertThat(order.getMeals(), contains(meal,meal2));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){

        //given
        Meal meal = new Meal(15,"Burger");
        //Order order = new Order();

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeIncorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal1 = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Juice");

        //Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(),contains(meal1,meal2));
        assertThat(order.getMeals(),containsInAnyOrder(meal2,meal1));

    }

    @Test
    void testIfToMealListAreTheSame(){
        //given
        Meal meal1 = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Juice");
        Meal meal3 = new Meal(11,"Kebab");

        List<Meal> meals1 = Arrays.asList(meal1,meal2);
        List<Meal> meals2 = Arrays.asList(meal1,meal2);

        //then
        assertThat(meals1, is(meals2));

    }


}