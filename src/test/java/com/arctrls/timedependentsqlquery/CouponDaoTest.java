package com.arctrls.timedependentsqlquery;

import com.arctrls.supports.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@IntegrationTest
class CouponDaoTest {

    @Autowired private CouponDao sut;

    @Test
    void findById() {
        final Coupon coupon = sut.findById(1L).orElseThrow();

        assertThat(coupon).hasNoNullFieldsOrProperties();
    }
}