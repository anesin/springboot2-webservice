package com.anesin.springboot.aws.web.dto;

import com.anesin.springboot.aws.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostsListResponseDto {

  private Long id;
  private String title;
  private String author;
  private LocalDateTime modifiedDate;


  public PostsListResponseDto(Posts entity) {
    this.id           = entity.getId();
    this.title        = entity.getTitle();
    this.author       = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }

}
