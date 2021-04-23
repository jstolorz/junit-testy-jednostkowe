package org.bluesoft.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Disabled
    @Test
    void simulateLargeOrder() {
        // given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(10), () -> cart.simulateLargeOrder());
    }
}