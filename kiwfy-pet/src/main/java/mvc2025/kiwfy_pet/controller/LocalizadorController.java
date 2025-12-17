package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Localizador;
import mvc2025.kiwfy_pet.service.LocalizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/localizadores")
public class LocalizadorController {
    
    @Autowired
    private LocalizadorService localizadorService;
    
    // Listar todos os tutor
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("localizadores", localizadorService.listarTodos());
        return "localizadores/list";
    }
    
    // Mostrar formulário para criar novo tutor
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("localizador", new Localizador());
        return "localizadores/form";
    }
    
    // Mostrar formulário para editar tutor existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var localizador = localizadorService.buscarPorId(id);
        if (localizador.isPresent()) {
            model.addAttribute("localizador", localizador.get());
            return "localizadores/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "localizador não encontrado!");
            return "redirect:/localizadores";
        }
    }
    
    // Salvar tutor (criar ou atualizar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Localizador localizador, RedirectAttributes redirectAttributes) {
        localizadorService.salvar(localizador);
        redirectAttributes.addFlashAttribute("sucesso", "localizador salvo com sucesso!");
        return "redirect:/localizadores";
    }

    @GetMapping("/encontrado/{id}")
    public String marcarEncontrado(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        localizadorService.marcarEncontrado(id);
        redirectAttributes.addFlashAttribute("sucesso", "Registro marcado como encontrado!");
        return "redirect:/localizadores";
    }
    
    // Excluir tutor
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        localizadorService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "localizador excluído com sucesso!");
        return "redirect:/localizadores";
    }
}

