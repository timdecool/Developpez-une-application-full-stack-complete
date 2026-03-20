package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleRequestDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    @Autowired
    ThemeMapper themeMapper;

    @Autowired
    UserMapper userMapper;


    public ArticleDTO toDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();

        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setDate(article.getUpdatedAt());
        dto.setContent(article.getContent());
        dto.setTheme(themeMapper.toDTO(article.getTheme()));
        dto.setAuthor(userMapper.toDTO(article.getCreatedBy()));
        return dto;
    }

    public Article toEntity(ArticleRequestDTO dto, Theme theme, User user) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setCreatedBy(user);
        article.setTheme(theme);
        return article;
    }
}
