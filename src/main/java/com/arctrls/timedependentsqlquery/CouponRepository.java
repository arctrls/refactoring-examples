package com.arctrls.timedependentsqlquery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
interface CouponRepository {

    @Select("""
            select id, name, start_dt availableFrom, end_dt availableTo
            from coupon
            where id = #{couponId};
            """)
    Optional<Coupon> findById(final Long couponId);
}
