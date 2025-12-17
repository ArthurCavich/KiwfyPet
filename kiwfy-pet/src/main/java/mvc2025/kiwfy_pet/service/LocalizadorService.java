package mvc2025.kiwfy_pet.service;

import mvc2025.kiwfy_pet.model.Localizador;
import mvc2025.kiwfy_pet.repository.LocalizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizadorService {
    
    @Autowired
    private LocalizadorRepository LocalizadorRepository;
    
    public List<Localizador> listarTodos() {
        return LocalizadorRepository.findAll();
    }
    
    public Optional<Localizador> buscarPorId(Long id) {
        return LocalizadorRepository.findById(id);
    }
    
    public Localizador salvar(Localizador localizador) {
        return LocalizadorRepository.save(localizador);
    }
    
    public void excluir(Long id) {
        LocalizadorRepository.deleteById(id);
    }
}

