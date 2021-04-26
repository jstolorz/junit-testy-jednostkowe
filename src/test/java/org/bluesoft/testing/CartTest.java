package org.bluesoft.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class CartTest {


    @Test
    @Disabled
    @DisplayName("Cart is able to process 1000 orders in 100 ms.")
    void simulateLargeOrder() {
        // given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), () -> cart.simulateLargeOrder());
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders().size(), equalTo(1));
        assertThat(cart.getOrders(), hasSize(1));
        assertThat(cart.getOrders(), anyOf(       // dowolny jeden spelniony
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(), allOf(       // wszystkie spelnone
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll(
                ()-> assertThat(cart.getOrders(), notNullValue()),
                ()-> assertThat(cart.getOrders(), hasSize(1)) ,
                ()-> {
                    List<Meal> mealList = cart.getOrders().get(0).getMeals();
                    assertThat(mealList, empty());
                }
        );

    }
}