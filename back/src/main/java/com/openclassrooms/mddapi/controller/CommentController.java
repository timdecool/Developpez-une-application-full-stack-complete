package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.CommentRequestDTO;
import com.openclassrooms.mddapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller handling comment management operations.
 * Protected endpoints: GET comments/article/{id}, POST comments
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments associated to given article.
     *
     * @param id article identifier
     * @return 200 with a list of comments CommentDTO on success
     *         404 if article is not found
     */
    @GetMapping("/article/{id}")
    private ResponseEntity<List<CommentDTO>> findAllCommentsByArticle(@PathVariable("id") final Long id) {
        List<CommentDTO> comments = commentService.findAllCommentsByArticle(id);
        return ResponseEntity.ok(comments);
    }

    /**
     * Creates and returns new comment
     *
     * @param dto comment data with content and article id
     * @return 201 with CommentDTO on success
     *         400 if request body is invalid or fails validation
     *         404 if article or user is not found
     */
    @PostMapping("")
    private ResponseEntity<CommentDTO> createComment(
            @Valid @RequestBody CommentRequestDTO dto
    ) {
        CommentDTO comment = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

}
