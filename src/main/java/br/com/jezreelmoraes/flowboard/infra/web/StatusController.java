package br.com.jezreelmoraes.flowboard.infra.web;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    @GetMapping
    public Map<String, Object> status() {
        return Map.of(
            "service", "FlowBoard API",
            "time", Instant.now()
        );
    }
}
