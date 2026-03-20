package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleRequestDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.CommentRequestDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    private ResponseEntity<List<CommentDTO>> findAllCommentsByArticle(@PathVariable("id") final Long id) {
        List<CommentDTO> comments = commentService.findAllCommentsByArticle(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("")
    private ResponseEntity<CommentDTO> createComment(
            @Valid @RequestBody CommentRequestDTO dto
    ) {
        CommentDTO article = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}
