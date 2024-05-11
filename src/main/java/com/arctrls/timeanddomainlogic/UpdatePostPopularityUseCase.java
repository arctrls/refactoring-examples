package com.arctrls.timeanddomainlogic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePostPopularityUseCase {

    private final PostRepository postRepository;

    public void update() {
        final List<Post> posts = postRepository.findAll();

        for (final Post post : posts) {
            post.updatePopularity(Instant.now());
            postRepository.save(post);
        }
    }

}
