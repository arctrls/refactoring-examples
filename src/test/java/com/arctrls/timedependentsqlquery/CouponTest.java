package com.arctrls.timedependentsqlquery;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CouponTest {

    @Test
    void isDownloadable_ShouldThrowException_WhenArgumentIsNull() {
        final var coupon = new Coupon();

        assertThatThrownBy(() -> coupon.isDownloadable(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isDownloadable_ShouldReturnTrue_WhenTimeIsDownloadable() {
        final var availableFrom = Instant.parse("2021-12-25T00:00:00Z");
        final var availableTo = Instant.parse("2021-12-25T23:59:59Z");
        final var currentTime = Instant.parse("2021-12-25T12:00:00Z");
        final var coupon = new Coupon(availableFrom, availableTo);

        final var isDownloadable = coupon.isDownloadable(currentTime);

        assertTrue(isDownloadable);
    }
}