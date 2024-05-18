package com.arctrls.timedependentsqlquery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@RequiredArgsConstructor
class ReadCouponService {
    private final CouponDao couponDao;

    @Transactional(readOnly = true)
    boolean isDownloadable(final Long couponId) {
        return couponDao.isDownloadable(couponId, Instant.now());
    }
}
