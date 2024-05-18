package com.arctrls.timedependentsqlquery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

import java.time.Instant;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
public class Coupon {
    private Long id;
    private String name;
    private Instant availableFrom;
    private Instant availableTo;

    public Coupon(final Instant availableFrom, final Instant availableTo) {
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public boolean isDownloadable(final Instant now) {
        Assert.notNull(now, "[now] must not be null.");
        if (now.equals(availableFrom)) return true;
        if (now.equals(availableTo)) return true;
        return now.isAfter(availableFrom) && now.isBefore(availableTo);
    }
}
