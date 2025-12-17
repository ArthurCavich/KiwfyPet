package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.model.Tutor;
import mvc2025.kiwfy_pet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Salva tutor com lista de pets (mestre/detalhe).
     * - Remove pets sem nome (linhas vazias do formulário)
     * - Garante vínculo tutor->pet
     * - orphanRemoval cuida dos removidos
     */
    @Transactional
    public Tutor salvarComPets(Tutor tutor) {
        if (tutor.getPets() != null) {
            tutor.getPets().removeIf(p -> p.getNome() == null || p.getNome().trim().isEmpty());
            for (Pet pet : tutor.getPets()) {
                pet.setTutor(tutor);
            }
        }
        return TutorRepository.save(tutor);
    }
    
    public void excluir(Long id) {
        TutorRepository.deleteById(id);
    }
}

