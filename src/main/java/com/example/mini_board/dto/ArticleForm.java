package com.example.mini_board.dto;

import com.example.mini_board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private String author;

    public Article toEntity() {
        return new Article(id, title, content, author, null);
    }
}
