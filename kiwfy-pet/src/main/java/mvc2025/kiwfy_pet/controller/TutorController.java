package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Tutor;
import mvc2025.kiwfy_pet.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tutores")
public class TutorController {
    
    @Autowired
    private TutorService tutorService;
    
    // Listar todos os tutor
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tutores", tutorService.listarTodos());
        return "tutores/list";
    }
    
    // Mostrar formulário para criar novo tutor
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutores/form";
    }
    
    // Mostrar formulário para editar tutor existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var tutor = tutorService.buscarPorId(id);
        if (tutor.isPresent()) {
            model.addAttribute("tutor", tutor.get());
            return "tutores/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "tutor não encontrado!");
            return "redirect:/tutores";
        }
    }
    
    // Salvar tutor (criar ou atualizar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Tutor tutor, RedirectAttributes redirectAttributes) {
        tutorService.salvar(tutor);
        redirectAttributes.addFlashAttribute("sucesso", "tutor salvo com sucesso!");
        return "redirect:/tutores";
    }
    
    // Excluir tutor
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tutorService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "tutor excluído com sucesso!");
        return "redirect:/tutores";
    }
}

