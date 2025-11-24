package mvc2025.kiwfy_pet.repository;

import mvc2025.kiwfy_pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Métodos customizados podem ser adicionados aqui se necessário
}

