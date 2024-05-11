package com.arctrls.timeanddomainlogic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePostPopularityUseCase {

    private final PostRepository postRepository;

    public void update() {
        final List<Post> posts = postRepository.findAll();

        for (final Post post : posts) {
            final long likeCount = post.likeCount();
            final long postAge = post.measurePostAge();
            post.updatePopularity(likeCount, postAge);
            postRepository.save(post);
        }
    }
}
