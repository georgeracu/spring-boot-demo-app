package com.georgeracu.demo.springboot.adapter.time.rest;


import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TimeControllerTest {

    private final TimeController timeController = new TimeController();

    @Test
    void shouldReturnTimeStamp() {
        // arrange

        // act
        final GetTime response = timeController.getTime();

        // assert
        assertThat(response).isNotNull();
        assertThat(response.timeStamp()).isNotNull()
                .isBeforeOrEqualTo(ZonedDateTime.now());
    }
}