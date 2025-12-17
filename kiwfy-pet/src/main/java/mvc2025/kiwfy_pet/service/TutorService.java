package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Tutor;
import mvc2025.kiwfy_pet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {
    
    @Autowired
    private TutorRepository TutorRepository;
    
    public List<Tutor> listarTodos() {
        return TutorRepository.findAll();
    }
    
    public Optional<Tutor> buscarPorId(Long id) {
        return TutorRepository.findById(id);
    }
    
    public Tutor salvar(Tutor tutor) {
        return TutorRepository.save(tutor);
    }
    
    public void excluir(Long id) {
        TutorRepository.deleteById(id);
    }
}

