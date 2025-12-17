package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Owner;
import mvc2025.kiwfy_pet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("owners", ownerService.listarTodos());
        return "owners/list";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/form";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var owner = ownerService.buscarPorId(id);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
            return "owners/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Tutor não encontrado!");
            return "redirect:/owners";
        }
    }
    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Owner owner, RedirectAttributes redirectAttributes) {
        ownerService.salvar(owner);
        redirectAttributes.addFlashAttribute("sucesso", "Tutor salvo com sucesso!");
        return "redirect:/owners";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ownerService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Tutor excluído com sucesso!");
        return "redirect:/owners";
    }
}

