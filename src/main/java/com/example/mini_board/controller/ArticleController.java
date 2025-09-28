package com.example.mini_board.controller;

import com.example.mini_board.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @GetMapping("/articles")
    public String list() {
        log.info("list");
        return "articles/list";
    }
}
