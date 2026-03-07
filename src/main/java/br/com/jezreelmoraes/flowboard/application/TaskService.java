package br.com.jezreelmoraes.flowboard.application;

import br.com.jezreelmoraes.flowboard.domain.model.Task;
import br.com.jezreelmoraes.flowboard.domain.model.TaskCreateRequest;
import br.com.jezreelmoraes.flowboard.domain.model.TaskResponse;
import br.com.jezreelmoraes.flowboard.domain.model.TaskUpdateRequest;
import br.com.jezreelmoraes.flowboard.domain.repository.TaskRepository;
import br.com.jezreelmoraes.flowboard.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> BusinessException.notFound("Task not found with id: " + id));
        return toResponse(task);
    }

    @Transactional
    public TaskResponse create(TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(request.priority());
        task.setCompleted(false);

        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    @Transactional
    public TaskResponse update(UUID id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> BusinessException.notFound("Task not found with id: " + id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setCompleted(request.completed());
        task.setPriority(request.priority());

        Task updated = taskRepository.save(task);
        return toResponse(updated);
    }

    @Transactional
    public void delete(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw BusinessException.notFound("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getCompleted(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
