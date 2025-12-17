package mvc2025.kiwfy_pet.repository;

import mvc2025.kiwfy_pet.model.Localizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizadorRepository extends JpaRepository<Localizador, Long> {
    // Métodos customizados podem ser adicionados aqui se necessário
}

