package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Owner;
import mvc2025.kiwfy_pet.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;
    
    public List<Owner> listarTodos() {
        return ownerRepository.findAll();
    }
    
    public Optional<Owner> buscarPorId(Long id) {
        return ownerRepository.findById(id);
    }
    
    public Owner salvar(Owner owner) {
        return ownerRepository.save(owner);
    }
    
    public void excluir(Long id) {
        ownerRepository.deleteById(id);
    }
}

