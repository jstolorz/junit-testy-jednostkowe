package org.bluesoft.testing;

import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
      Account newAccount = new Account();
      //then
      assertFalse(newAccount.isActive());

      //assertThat(newAccount.isActive(), equalTo(false));

      // Hamcrest
      // assertThat(newAccount.isActive(), is(false));

      // assertJ
      assertThat(newAccount.isActive()).isFalse();


    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account account = new Account();
        assertFalse(account.isActive());
        //when
        account.activate();
        //then
        assertTrue(account.isActive());

        // Hamcrest
        //assertThat(account.isActive(), equalTo(true));

        // assertJ
        assertThat(account.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
         //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);

        // Hamcrest
        //assertThat(address, nullValue());

        // assertJ
        assertThat(address).isNull();

    }

    @Test
    void defaultDeliveryAddresShouldNotBeNullAfterSet(){
        //given
        Address address = new Address("Żorska","105A");
        Account account = new Account();

        //when
        account.setDefaultDeliveryAddress(address);

        //then
        assertNotNull(account.getDefaultDeliveryAddress());

        // Hamcrest
        //assertThat(account.getDefaultDeliveryAddress(), notNullValue());

        // assertJ
        assertThat(account.getDefaultDeliveryAddress()).isNotNull();
    }
}