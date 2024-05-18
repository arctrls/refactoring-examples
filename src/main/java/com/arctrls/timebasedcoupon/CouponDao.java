package com.arctrls.timebasedcoupon;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
interface CouponDao {

    @Select("""
            select exists(select id
                                  from coupon
                                  where id = #{couponId}
                                    and now() between start_dt and end_dt)
            """)
    boolean isDownloadable(final Long couponId);
}
