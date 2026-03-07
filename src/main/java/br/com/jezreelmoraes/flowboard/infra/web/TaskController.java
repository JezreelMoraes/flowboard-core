package br.com.jezreelmoraes.flowboard.infra.web;

import br.com.jezreelmoraes.flowboard.domain.model.TaskCreateRequest;
import br.com.jezreelmoraes.flowboard.domain.model.TaskResponse;
import br.com.jezreelmoraes.flowboard.domain.model.TaskUpdateRequest;
import br.com.jezreelmoraes.flowboard.infra.api.ApiResponse;
import br.com.jezreelmoraes.flowboard.application.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> findAll() {
        List<TaskResponse> tasks = taskService.findAll();
        return ResponseEntity.ok(ApiResponse.ok(tasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> findById(@PathVariable UUID id) {
        TaskResponse task = taskService.findById(id);
        return ResponseEntity.ok(ApiResponse.ok(task));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> create(@Valid @RequestBody TaskCreateRequest request) {
        TaskResponse created = taskService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody TaskUpdateRequest request) {
        TaskResponse updated = taskService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
