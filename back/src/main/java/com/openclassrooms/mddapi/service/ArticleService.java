package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleRequestDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service handling business logic for article management.
 * Provides operations for creating and retrieving articles.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * Creates a new article authored by the authenticated user.
     *
     * @param dto the article data transfer object ArticleRequestDTO containing title, content and theme id
     * @return created article as data transfer object ArticleDTO
     * @throws NoSuchElementException if the theme or authenticated user is not found
     */
    public ArticleDTO createArticle(ArticleRequestDTO dto) {
        Theme theme = themeRepository.findById(dto.getThemeId()).orElseThrow(
                () -> new NoSuchElementException("Theme not found with id " + dto.getThemeId())
        );

        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );
        Article article = articleMapper.toEntity(dto, theme, user);
        Article newArticle = articleRepository.save(article);
        return articleMapper.toDTO(newArticle);
    }

    /**
     * Retrieves all articles sorted by last update date in descending order.
     *
     * @return list of all articles as data transfer objects ArticleDTO
     */
    public List<ArticleDTO> findAllArticles() {
        return articleRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an article by its id.
     *
     * @param id the article identifier
     * @return the article as data transfer object ArticleDTO
     * @throws NoSuchElementException if no article is found with the given id
     */
    public ArticleDTO findArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Article not found with id " + id)
        );
        return articleMapper.toDTO(article);
    }
}
