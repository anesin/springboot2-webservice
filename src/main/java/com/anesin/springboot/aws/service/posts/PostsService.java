package com.anesin.springboot.aws.service.posts;

import com.anesin.springboot.aws.domain.posts.Posts;
import com.anesin.springboot.aws.domain.posts.PostsRepository;
import com.anesin.springboot.aws.web.dto.PostsListResponseDto;
import com.anesin.springboot.aws.web.dto.PostsResponseDto;
import com.anesin.springboot.aws.web.dto.PostsSaveRequestDto;
import com.anesin.springboot.aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;


  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }


  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository
                      .findById(id)
                      .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    posts.update(requestDto.getTitle(), requestDto.getContent());
    return id;
  }


  @Transactional
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. Id=" + id));
    postsRepository.delete(posts);
  }


  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository
                       .findById(id)
                       .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    return new PostsResponseDto(entity);
  }


  @Transactional(readOnly = true)
  public List<PostsListResponseDto> findAllDesc() {
    return postsRepository.findAllDesc()
                          .stream()
                          .map(PostsListResponseDto::new)
                          .collect(Collectors.toList());
  }

}
