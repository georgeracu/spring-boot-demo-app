package com.georgeracu.demo.springboot.domain.tdd;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralsKataTest {

    private final RomanNumeralsKata sut = new RomanNumeralsKata();

    @ParameterizedTest
    @CsvSource({"1,'I'"})
    void shouldReturnExpectedValue(int input, final String expected) {

        // act
        // assert

    }
}