package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.CommentRequestDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    @Autowired
    UserMapper userMapper;

    public CommentDTO toDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setAuthor(userMapper.toDTO(comment.getCreatedBy()));
        dto.setDate(comment.getUpdatedAt());
        return dto;
    }

    public Comment toEntity(CommentRequestDTO dto, Article article, User user) {
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setCreatedBy(user);
        comment.setContent(dto.getContent());
        return comment;
    }
}
