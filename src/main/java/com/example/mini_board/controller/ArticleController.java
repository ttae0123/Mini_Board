package com.example.mini_board.controller;

import com.example.mini_board.dto.ArticleForm;
import com.example.mini_board.entity.Article;
import com.example.mini_board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles") //글 목록
    public String listArticles(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/list";
    }

    @GetMapping("/articles/new") //작성 폼 O
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("/articles/new") //글 저장 후 목록 리다이렉트 O
    public String createArticle(ArticleForm form){
        Article article = form.toEntity();
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @GetMapping("articles/{id}") //글 상세 조회
    public String detailArticle(@PathVariable Long id,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        log.info(articleEntity.toString());
        return "articles/detail";
    }

    @GetMapping("articles/{id}/edit") //글 수정 폼
    public String editArticle(@PathVariable String id){
        return "articles/edit";
    }

    @PostMapping("articles/{id}/edit") //글 수정 후 상세 페이지 리다이렉트
    public String updateArticle(@PathVariable Long id){
        return "redirect:/articles";//+id
    }

    @PostMapping("articles/{id}/delete") //글 삭제 후 목록 리다이렉트
    public String deleteArticle(@PathVariable Long id){
        return "redirect:/articles";
    }

}
