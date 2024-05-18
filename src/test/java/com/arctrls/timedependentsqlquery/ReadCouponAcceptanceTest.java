package com.arctrls.timedependentsqlquery;

import com.arctrls.supports.IntegrationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@IntegrationTest
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ReadCouponAcceptanceTest {

    @LocalServerPort private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void couponApiShouldReturnExpectedResponse() {
        RestAssured.

                given().

                when()
                .get("/coupon/1").

                then()
                .statusCode(200)
                .body("couponId", equalTo(1))
                .body("isDownloadable", equalTo(true));
    }
}