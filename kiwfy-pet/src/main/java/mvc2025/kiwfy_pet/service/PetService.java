package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;
    
    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }
    
    public Optional<Pet> buscarPorId(Long id) {
        return petRepository.findById(id);
    }
    
    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }
    
    public void excluir(Long id) {
        petRepository.deleteById(id);
    }
}

