package br.com.child.manager.repository;

import br.com.child.manager.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByName(String name);
}
