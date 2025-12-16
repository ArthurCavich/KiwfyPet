package mvc2025.kiwfy_pet.repository;

import mvc2025.kiwfy_pet.model.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostRepository extends JpaRepository<Lost, Long> {
    List<Lost> findByEncontradoFalse();
}

