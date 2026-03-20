package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/theme/{themeId}")
    public ResponseEntity<Void> subscribe(
            @PathVariable("themeId") final Long themeId
    ) {
        subscriptionService.subscribe(themeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable("id") final Long id
    ) {
        subscriptionService.unsubscribe(id);
        return ResponseEntity.ok().build();
    }

}
