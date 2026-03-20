package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("")
    public ResponseEntity<List<ThemeDTO>> findAllThemes() {
        List<ThemeDTO> themes = themeService.findAllThemes();
        return ResponseEntity.ok(themes);
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<Void> subscribe(
            @PathVariable("id") final Long themeId
    ) {
        subscriptionService.subscribe(themeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/unsubscribe")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable("id") final Long id
    ) {
        subscriptionService.unsubscribe(id);
        return ResponseEntity.ok().build();
    }

}
