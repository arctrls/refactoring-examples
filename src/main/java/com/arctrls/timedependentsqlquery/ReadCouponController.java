package com.arctrls.timedependentsqlquery;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@RestController
@RequiredArgsConstructor
class ReadCouponController {
    private final ReadCouponService readCouponService;

    @GetMapping("/coupon/{couponId}")
    CouponResponse coupon(
            @PathVariable("couponId") final Long couponId,
            @RequestParam(value = "testDateTime", required = false) @DateTimeFormat(iso = DATE_TIME) final Instant testDateTime) {
        final boolean isDownloadable = readCouponService.isDownloadable(couponId, currentTime(testDateTime));
        return new CouponResponse(couponId, isDownloadable);
    }

    private Instant currentTime(final Instant testDateTime) {
        return null != testDateTime ? testDateTime : Instant.now();
    }

    record CouponResponse(Long couponId, boolean isDownloadable) {}
}
