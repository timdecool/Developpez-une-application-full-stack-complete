package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.CommentRequestDTO;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service handling business logic for comment management.
 * Provides operations for creating and retrieving comments.
 */
@Service
public class CommentService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * Creates a new comment authored by the authenticated user.
     *
     * @param dto the comment data transfer object CommentRequestDTO
     * @return the created comment as data transfer object CommentDTO
     * @throws NoSuchElementException if associated article or user is not found
     */
    public CommentDTO createComment(CommentRequestDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(
                () -> new NoSuchElementException("Article not found with id " + dto.getArticleId())
        );
        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );;

        Comment comment = commentMapper.toEntity(dto, article, user);
        Comment newComment = commentRepository.save(comment);
        return commentMapper.toDTO(newComment);
    }

    /**
     * Retrieves all articles associated to article.
     *
     * @param articleId requested article identifier
     * @return list of all comments as data transfer objects CommentDTO
     */
    public List<CommentDTO> findAllCommentsByArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new NoSuchElementException("Article not found with id " + articleId)
        );

        return commentRepository.findAllByArticleOrderByUpdatedAtDesc(article)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
