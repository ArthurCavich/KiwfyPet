package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.service.OwnerService;
import mvc2025.kiwfy_pet.service.PetService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pets")
public class PetController {
    
    @Autowired
    private PetService petService;
    
    @Autowired
    private OwnerService ownerService;
    
    // Listar todos os pets
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pets", petService.listarTodos());
        return "pets/list";
    }
    
    // Mostrar formulário para criar novo pet
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerService.listarTodos());
        return "pets/form";
    }
    
    // Mostrar formulário para editar pet existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var pet = petService.buscarPorId(id);
        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            model.addAttribute("owners", ownerService.listarTodos());
            return "pets/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Pet não encontrado!");
            return "redirect:/pets";
        }
    }
    
    // Salvar pet (criar ou atualizar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pet pet, 
                        @RequestParam(required = false) Long ownerId,
                        RedirectAttributes redirectAttributes) {
        if (ownerId != null) {
            var owner = ownerService.buscarPorId(ownerId);
            if (owner.isPresent()) {
                pet.setOwner(owner.get());
            }
        }
        petService.salvar(pet);
        redirectAttributes.addFlashAttribute("sucesso", "Pet salvo com sucesso!");
        return "redirect:/pets";
    }
    
    // Excluir pet
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        petService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Pet excluído com sucesso!");
        return "redirect:/pets";
    }
}

