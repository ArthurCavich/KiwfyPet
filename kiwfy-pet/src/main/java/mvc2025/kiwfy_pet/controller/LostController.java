package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Lost;
import mvc2025.kiwfy_pet.service.LostService;
import mvc2025.kiwfy_pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/lost")
public class LostController {
    
    @Autowired
    private LostService lostService;
    
    @Autowired
    private PetService petService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lostPets", lostService.listarTodos());
        return "lost/list";
    }
    
    @GetMapping("/nao-encontrados")
    public String listarNaoEncontrados(Model model) {
        model.addAttribute("lostPets", lostService.listarNaoEncontrados());
        return "lost/list";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        Lost lost = new Lost();
        lost.setDataPerda(LocalDateTime.now());
        model.addAttribute("lost", lost);
        model.addAttribute("pets", petService.listarTodos());
        return "lost/form";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var lost = lostService.buscarPorId(id);
        if (lost.isPresent()) {
            model.addAttribute("lost", lost.get());
            model.addAttribute("pets", petService.listarTodos());
            return "lost/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Registro de perda não encontrado!");
            return "redirect:/lost";
        }
    }
    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Lost lost,
                        @RequestParam(required = false) Long petId,
                        RedirectAttributes redirectAttributes) {
        if (petId != null) {
            var pet = petService.buscarPorId(petId);
            if (pet.isPresent()) {
                lost.setPet(pet.get());
            }
        }
        lostService.salvar(lost);
        redirectAttributes.addFlashAttribute("sucesso", "Registro de perda salvo com sucesso!");
        return "redirect:/lost";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        lostService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Registro de perda excluído com sucesso!");
        return "redirect:/lost";
    }
    
    @GetMapping("/encontrado/{id}")
    public String marcarComoEncontrado(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        lostService.marcarComoEncontrado(id);
        redirectAttributes.addFlashAttribute("sucesso", "Pet marcado como encontrado!");
        return "redirect:/lost";
    }
}

