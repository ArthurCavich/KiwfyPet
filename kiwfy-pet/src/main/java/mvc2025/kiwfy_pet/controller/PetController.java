package mvc2025.kiwfy_pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.service.PetService;
import mvc2025.kiwfy_pet.service.TutorService;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pets", petService.listarTodos());
        return "pets/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("tutores", tutorService.listarTodos());
        return "pets/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var pet = petService.buscarPorId(id);
        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            model.addAttribute("tutores", tutorService.listarTodos());
            return "pets/form";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Pet não encontrado!");
            return "redirect:/pets";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pet pet, RedirectAttributes redirectAttributes) {
        petService.salvar(pet);
        redirectAttributes.addFlashAttribute("sucesso", "Pet salvo com sucesso!");
        return "redirect:/pets";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        petService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Pet excluído com sucesso!");
        return "redirect:/pets";
    }
}

