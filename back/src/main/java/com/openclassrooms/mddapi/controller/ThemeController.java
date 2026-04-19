package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller handling theme management operations.
 * Protected endpoints : GET /themes, GET /themes/me
 */
@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Retrieves and returns all themes
     *
     * @return 200 with a list of ThemeDTO
     */
    @GetMapping("")
    public ResponseEntity<List<ThemeDTO>> findAllThemes() {
        List<ThemeDTO> themes = themeService.findAllThemes();
        return ResponseEntity.ok(themes);
    }

    /**
     * Retrieves and returns all the authenticated user subscribed themes
     * @return 200 with a list of ThemeDTO
     *         404 if user is not found
     */
    @GetMapping("/me")
    public ResponseEntity<List<ThemeDTO>> findMyThemes() {
        List<ThemeDTO> themes = themeService.findMyThemes();
        return ResponseEntity.ok(themes);
    }

    /**
     * Creates a subscription for authenticated user of given theme
     *
     * @param id theme identifier
     * @return 200 on success
     *         404 if user or theme is not found
     */
    @PostMapping("/{id}/subscribe")
    public ResponseEntity<Void> subscribe(
            @PathVariable("id") final Long id
    ) {
        subscriptionService.subscribe(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a subscription for authenticated user of given theme
     *
     * @param id theme identifier
     * @return 200 on success
     *         404 if user, theme or subscription is not found
     */
    @DeleteMapping("/{id}/unsubscribe")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable("id") final Long id
    ) {
        subscriptionService.unsubscribe(id);
        return ResponseEntity.ok().build();
    }
}
