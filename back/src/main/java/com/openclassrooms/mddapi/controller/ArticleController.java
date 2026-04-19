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

/**
 * REST controller handling article management operations
 * Protected endpoints: POST /articles, GET /articles, GET /articles/{id}
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Returns all articles
     *
     * @return 200 with list of ArticleDTO
     */
    @GetMapping("")
    private ResponseEntity<List<ArticleDTO>> findAllArticles() {
        List<ArticleDTO> articles = articleService.findAllArticles();
        return ResponseEntity.ok(articles);
    }

    /**
     * Returns an article based on requested id
     *
     * @param id article identifier
     * @return 200 with ArticleDTO on success
     *         404 if article is not found
     */
    @GetMapping("/{id}")
    private ResponseEntity<ArticleDTO> findArticle(@PathVariable("id") final Long id) {
        ArticleDTO article = articleService.findArticleById(id);
        return ResponseEntity.ok(article);
    }

    /**
     * Creates and returns an article.
     * @param dto ArticleRequestDTO article data with title, content and theme identifier.
     * @return 201 with ArticleDTO on success
     *         400 if request body is invalid or fails validation
     */
    @PostMapping("")
    private ResponseEntity<ArticleDTO> createArticle(
            @Valid @RequestBody ArticleRequestDTO dto
    ) {
        ArticleDTO article = articleService.createArticle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}
