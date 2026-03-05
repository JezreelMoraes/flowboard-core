package br.com.jezreelmoraes.flowboard.infra.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StatusController {

    @GetMapping("/status")
    public Map<String, Object> status() {
        return Map.of(
            "service", "FlowBoard API",
            "time", Instant.now()
        );
    }

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> ping() {
        return ResponseEntity.ok(Map.of(
            "message", "pong",
            "status", "API UP"
        ));
    }
}
