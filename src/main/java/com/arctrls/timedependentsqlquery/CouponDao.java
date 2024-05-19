package com.arctrls.timedependentsqlquery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.Instant;
import java.util.Optional;

@Mapper
interface CouponDao {

    @Select("""
            select exists(select id
                                  from coupon
                                  where id = #{couponId}
                                    and #{now} between start_dt and end_dt)
            """)
    boolean isDownloadable(final Long couponId, final Instant now);

    Optional<Coupon> findById(final Long couponId);
}
