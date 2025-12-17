package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.model.Tutor;
import mvc2025.kiwfy_pet.repository.PetRepository;
import mvc2025.kiwfy_pet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Optional<Pet> buscarPorId(Long id) {
        return petRepository.findById(id);
    }

    public Pet salvar(Pet pet) {
        // Garantir vínculo com tutor, se informado
        if (pet.getTutor() != null && pet.getTutor().getId() != null) {
            Optional<Tutor> tutorOpt = tutorRepository.findById(pet.getTutor().getId());
            tutorOpt.ifPresent(pet::setTutor);
        } else {
            pet.setTutor(null);
        }
        return petRepository.save(pet);
    }

    public void excluir(Long id) {
        petRepository.deleteById(id);
    }
}
