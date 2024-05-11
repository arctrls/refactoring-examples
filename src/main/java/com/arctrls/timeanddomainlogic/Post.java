package com.arctrls.timeanddomainlogic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Duration;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@Accessors(fluent = true)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

    @Column(nullable = false)
    private Long popularity = 0L;

    Post(final Long viewCount,
         final Long likeCount,
         final Long popularity,
         final Instant createdAt) {
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.popularity = popularity;
        this.createdAt = createdAt;
    }

    void updatePopularity(final Instant now) {
        final int postAge = measurePostAge(now);
        popularity = (likeCount + viewCount) / postAge;
    }

    private int measurePostAge(final Instant now) {
        final int hours = Duration.between(createdAt, now).toHoursPart();
        return 5 > hours ? 1 : hours / 5;
    }

}
