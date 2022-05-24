package com.eduanico.resourceserv.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Api Response Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ApiResponseCustomTest {

    @Autowired
    private ApiResponseCustom apiResponseCustom;


    @Test
    @DisplayName("Api response success is true when response successfully")
    void isSuccess_ReturnTrue_WhenSuccess() {
        apiResponseCustom = new ApiResponseCustom(true, "Success");
        assertThat(apiResponseCustom.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("Api response success is false when response failed")
    void isSuccess_ReturnFalse_WhenFail() {
        apiResponseCustom = new ApiResponseCustom(false, "Fail");
        assertThat(apiResponseCustom.isSuccess()).isFalse();
    }

    @Test
    @DisplayName("Return message of the api responde when successful")
    void getMessage_ReturnMessage() {
        apiResponseCustom = new ApiResponseCustom(true, "Success");
        assertThat(apiResponseCustom.getMessage()).isEqualTo("Success");
    }

    @Test
    @DisplayName("Return Timestamp of the api responde when successful")
    void getTimestamp_ReturnTimestamp() {
        apiResponseCustom = new ApiResponseCustom(true, "Success");
        assertThat(apiResponseCustom.getTimestamp()).isLessThanOrEqualTo(LocalDateTime.now().toString());
    }
}