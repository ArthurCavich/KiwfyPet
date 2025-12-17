package mvc2025.kiwfy_pet.repository;

import mvc2025.kiwfy_pet.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    // Métodos customizados podem ser adicionados aqui se necessário
}

