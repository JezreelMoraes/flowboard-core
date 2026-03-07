package br.com.jezreelmoraes.flowboard.domain.repository;

import br.com.jezreelmoraes.flowboard.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
