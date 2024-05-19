package com.arctrls.timedependentsqlquery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;


@Service
@RequiredArgsConstructor
class ReadCouponService {
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    boolean isDownloadable(final Long couponId, final Instant now) {
        final Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        return optionalCoupon
                .map(coupon -> coupon.isDownloadable(now))
                .orElse(false);
    }
}
