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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public String listArticles(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/list";
    }

    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("/articles/new")
    public String createArticle(ArticleForm form){
        Article article = form.toEntity();
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @GetMapping("articles/{id}")
    public String detailArticle(@PathVariable Long id,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/detail";
    }

    @GetMapping("articles/{id}/edit")
    public String editArticle(@PathVariable Long id,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("articles/{id}/edit")
    public String updateArticle(@PathVariable Long id,ArticleForm form){
        Article target = articleRepository.findById(id).orElse(null);
        if (target != null) {
            target.setTitle(form.getTitle());
            target.setContent(form.getContent());
            target.setAuthor(form.getAuthor());
            articleRepository.save(target);
        }
        return "redirect:/articles/" + id;
    }

    @GetMapping("articles/{id}/delete") //삭제는 POST를 쓸 BODY가 필요없기 때문에 Get으로 대체
    public String deleteArticle(@PathVariable Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            articleRepository.delete(target);
        }
        return "redirect:/articles";
    }

}
