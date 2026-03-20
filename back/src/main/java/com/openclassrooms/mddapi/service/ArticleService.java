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

    public ArticleDTO createArticle(ArticleRequestDTO dto) {
        Theme theme = themeRepository.findById(dto.getThemeId()).orElseThrow(
                () -> new NoSuchElementException("Theme not found with id " + dto.getThemeId())
        );

        User user = userRepository.findByEmail(AuthService.getCurrentUser());
        Article article = articleMapper.toEntity(dto, theme, user);
        Article newArticle = articleRepository.save(article);
        return articleMapper.toDTO(newArticle);
    }

    public List<ArticleDTO> findAllArticles() {
        return articleRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ArticleDTO findArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Article not found with id " + id)
        );
        return articleMapper.toDTO(article);
    }
}
