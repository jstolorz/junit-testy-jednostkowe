package org.bluesoft.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Zorska, 10", "Zdrowotna, 10", "Cielmicka, 10/3"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void givenAddressesShouldNotBeEmptyAndHaveProperNamesFromFile(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
    }


}