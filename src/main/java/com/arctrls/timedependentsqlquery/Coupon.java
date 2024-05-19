package com.arctrls.timedependentsqlquery;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

import java.time.Instant;

@Getter
@Accessors(fluent = true)
public class Coupon {
    private Long id;
    private String name;
    private Instant availableFrom;
    private Instant availableTo;

    public boolean isDownloadable(final Instant now) {
        Assert.notNull(now, "[now] must not be null.");
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
