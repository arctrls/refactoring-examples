package com.arctrls.timedependentsqlquery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@RequiredArgsConstructor
class ReadCouponService {
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    boolean isDownloadable(final Long couponId, final Instant now) {
        return couponRepository.findById(couponId)
                .map(coupon -> coupon.isDownloadable(now))
                .orElse(false);
    }
}
