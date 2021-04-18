package org.bluesoft.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void myTest(){
      Account newAccount = new Account();
      assertFalse(newAccount.isActive());
    }
}