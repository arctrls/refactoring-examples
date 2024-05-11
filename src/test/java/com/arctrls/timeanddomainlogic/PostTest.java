package com.arctrls.timeanddomainlogic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    private final long likeCount = 10L;
    private final long viewCount = 100L;
    private final LocalDateTime postCreatedAt = LocalDate.of(2024, 1, 1).atStartOfDay();

    @Test
    @DisplayName("좋아요 10, 조회수 100, 생성으로부터 9시간 59분 경과했을 때, 인기도는 10 + 100 = 110 이다.")
    void updatePopularity_case_0() {
        // given
        final var now = postCreatedAt.plusHours(9).plusMinutes(59);
        final var post = createPostWith(likeCount, viewCount, postCreatedAt);

        // when
        post.updatePopularity(toInstant(now));

        // then
        assertThat(post.popularity()).isEqualTo(110L);
    }

    @Test
    @DisplayName("좋아요 10, 조회수 100, 생성으로부터 10시간 경과했을 때, 인기도는 (10 + 100) / (10(h) * 0.5) = 55 이다.")
    void updatePopularity_case_1() {
        // given
        final var now = postCreatedAt.plusHours(10);
        final var post = createPostWith(likeCount, viewCount, postCreatedAt);

        // when
        post.updatePopularity(toInstant(now));

        // then
        assertThat(post.popularity()).isEqualTo(55L);
    }

    private Post createPostWith(final long likeCount, final long viewCount, final LocalDateTime createdAt) {
        return new Post(likeCount, viewCount, 0L, toInstant(createdAt));
    }

    private Instant toInstant(final LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
