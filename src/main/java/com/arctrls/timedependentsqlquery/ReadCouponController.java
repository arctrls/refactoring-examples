package com.arctrls.timedependentsqlquery;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ReadCouponController {
    private final ReadCouponService readCouponService;

    @GetMapping("/coupon/{couponId}")
    CouponResponse coupon(@PathVariable("couponId") final Long couponId) {
        final boolean isDownloadable = readCouponService.isDownloadable(couponId);
        return new CouponResponse(couponId, isDownloadable);
    }

    record CouponResponse(Long couponId, boolean isDownloadable) {}
}
