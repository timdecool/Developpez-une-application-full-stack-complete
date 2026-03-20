package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleRequestDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    private ResponseEntity<List<ArticleDTO>> findAllArticles() {
        List<ArticleDTO> articles = articleService.findAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ArticleDTO> findArticle(@PathVariable("id") final Long id) {
        ArticleDTO article = articleService.findArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping("")
    private ResponseEntity<ArticleDTO> createArticle(
            @Valid @RequestBody ArticleRequestDTO dto
    ) {
        ArticleDTO article = articleService.createArticle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}
