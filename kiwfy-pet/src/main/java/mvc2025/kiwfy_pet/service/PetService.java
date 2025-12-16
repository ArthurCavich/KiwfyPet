package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Owner;
import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.repository.OwnerRepository;
import mvc2025.kiwfy_pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private OwnerRepository ownerRepository;
    
    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }
    
    public Optional<Pet> buscarPorId(Long id) {
        return petRepository.findById(id);
    }
    
    public Pet salvar(Pet pet) {
        // Se o owner foi selecionado pelo ID, buscar o objeto Owner completo
        if (pet.getOwner() != null && pet.getOwner().getId() != null) {
            Optional<Owner> ownerOpt = ownerRepository.findById(pet.getOwner().getId());
            if (ownerOpt.isPresent()) {
                pet.setOwner(ownerOpt.get());
            }
        }
        return petRepository.save(pet);
    }
    
    public void excluir(Long id) {
        petRepository.deleteById(id);
    }
}

