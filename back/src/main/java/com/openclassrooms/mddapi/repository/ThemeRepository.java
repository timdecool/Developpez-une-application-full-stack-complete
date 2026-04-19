package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("SELECT t FROM Theme t JOIN Subscription s ON s.theme = t WHERE s.user.id = :userId")
    List<Theme> findSubscribedThemesByUserId(@Param("userId") Long userId);

}
