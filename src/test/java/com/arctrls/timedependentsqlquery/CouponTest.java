package com.arctrls.timedependentsqlquery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CouponTest {
    private final Instant availableFrom = Instant.parse("2021-12-25T00:00:00Z");
    private final Instant availableTo = Instant.parse("2021-12-25T23:59:59Z");

    private Coupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new Coupon(availableFrom, availableTo);
    }

    @Test
    void isDownloadable_ShouldThrowException_WhenArgumentIsNull() {
        assertThatThrownBy(() -> coupon.isDownloadable(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isDownloadable_ShouldReturnTrue_WhenTimeIsDownloadable() {
        final var currentTime = Instant.parse("2021-12-25T12:00:00Z");

        final var isDownloadable = coupon.isDownloadable(currentTime);

        assertTrue(isDownloadable);
    }

    @Test
    void isDownloadable_ShouldReturnFalse_WhenTimeIs1NanoSecBeforeAvailableFrom() {
        final var currentTime = availableFrom.minusNanos(1);

        final var isDownloadable = coupon.isDownloadable(currentTime);

        assertFalse(isDownloadable);
    }

    @Test
    void isDownloadable_ShouldReturnFalse_WhenTimeIs1NanoSecAfterAvailableTo() {
        final var currentTime = availableTo.plusNanos(1);

        final var isDownloadable = coupon.isDownloadable(currentTime);

        assertFalse(isDownloadable);
    }

    @Test
    void isDownloadable_ShouldReturnTrue_WhenTimeIsEqualToAvailableFrom() {
        final var isDownloadable = coupon.isDownloadable(availableFrom);

        assertTrue(isDownloadable);
    }

    @Test
    void isDownloadable_ShouldReturnTrue_WhenTimeIsEqualToAvailableTo() {
        final var isDownloadable = coupon.isDownloadable(availableTo);

        assertTrue(isDownloadable);
    }
}