package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Lost;
import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.repository.LostRepository;
import mvc2025.kiwfy_pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LostService {
    
    @Autowired
    private LostRepository lostRepository;
    
    @Autowired
    private PetRepository petRepository;
    
    public List<Lost> listarTodos() {
        return lostRepository.findAll();
    }
    
    public List<Lost> listarNaoEncontrados() {
        return lostRepository.findByEncontradoFalse();
    }
    
    public Optional<Lost> buscarPorId(Long id) {
        return lostRepository.findById(id);
    }
    
    public Lost salvar(Lost lost) {
        // Se o pet foi selecionado pelo ID, buscar o objeto Pet completo
        if (lost.getPet() != null && lost.getPet().getId() != null) {
            Optional<Pet> petOpt = petRepository.findById(lost.getPet().getId());
            if (petOpt.isPresent()) {
                lost.setPet(petOpt.get());
            }
        }
        return lostRepository.save(lost);
    }
    
    public void excluir(Long id) {
        lostRepository.deleteById(id);
    }
    
    public Lost marcarComoEncontrado(Long id) {
        Optional<Lost> lostOpt = lostRepository.findById(id);
        if (lostOpt.isPresent()) {
            Lost lost = lostOpt.get();
            lost.setEncontrado(true);
            lost.setDataEncontrado(LocalDateTime.now());
            return lostRepository.save(lost);
        }
        return null;
    }
}

