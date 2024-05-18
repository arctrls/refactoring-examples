package com.arctrls.timedependentsqlquery;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CouponTest {

    @Test
    void isDownloadable_ShouldThrowException_WhenArgumentIsNull() {
        final var coupon = new Coupon();

        assertThatThrownBy(() -> coupon.isDownloadable(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}