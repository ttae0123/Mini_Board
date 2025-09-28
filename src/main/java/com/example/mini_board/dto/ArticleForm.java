package com.example.mini_board.dto;

import com.example.mini_board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private String author;

    public Article toEntity() {
        return new Article(id, title, content, author, null);
    }
}
