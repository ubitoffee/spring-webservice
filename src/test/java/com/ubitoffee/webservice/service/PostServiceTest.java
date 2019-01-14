package com.ubitoffee.webservice.service;

import com.ubitoffee.webservice.domain.posts.Posts;
import com.ubitoffee.webservice.domain.posts.PostsRepository;
import com.ubitoffee.webservice.dto.posts.PostsSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터를_posts_테이블에_저장() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("ubi.toffee@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        postsService.save(dto);

        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
    }
}
