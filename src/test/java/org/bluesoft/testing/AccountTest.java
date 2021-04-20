package org.bluesoft.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
      Account newAccount = new Account();
      //then
      assertFalse(newAccount.isActive());
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
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
         //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
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
    }
}