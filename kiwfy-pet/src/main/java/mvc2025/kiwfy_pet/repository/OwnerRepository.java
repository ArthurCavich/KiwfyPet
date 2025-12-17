package mvc2025.kiwfy_pet.repository;

import mvc2025.kiwfy_pet.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

