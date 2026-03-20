package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleRequestDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.CommentRequestDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public CommentDTO createComment(CommentRequestDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(
                () -> new NoSuchElementException("Article not found with id " + dto.getArticleId())
        );
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new NoSuchElementException("User not found with id " + dto.getUserId())
        );

        Comment comment = commentMapper.toEntity(dto, article, user);
        Comment newComment = commentRepository.save(comment);
        return commentMapper.toDTO(newComment);
    }

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
