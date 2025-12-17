package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.model.Pet;
import mvc2025.kiwfy_pet.model.Tutor;
import mvc2025.kiwfy_pet.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/tutores-md")
public class TutorMestreDetalheController {

    @Autowired
    private TutorService tutorService;

    @GetMapping("/novo")
    public String novo(Model model) {
        Tutor tutor = new Tutor();
        tutor.setPets(new ArrayList<>());
        tutor.getPets().add(new Pet()); // começa com 1 linha
        model.addAttribute("tutor", tutor);
        return "tutores/md-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        var tutorOpt = tutorService.buscarPorId(id);
        if (tutorOpt.isEmpty()) {
            redirect.addFlashAttribute("erro", "Tutor não encontrado!");
            return "redirect:/tutores";
        }
        var tutor = tutorOpt.get();
        if (tutor.getPets() == null || tutor.getPets().isEmpty()) {
            tutor.setPets(new ArrayList<>());
            tutor.getPets().add(new Pet());
        }
        model.addAttribute("tutor", tutor);
        return "tutores/md-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("tutor") Tutor tutor, RedirectAttributes redirect) {
        tutorService.salvarComPets(tutor);
        redirect.addFlashAttribute("sucesso", "Tutor e pets salvos com sucesso!");
        return "redirect:/tutores";
    }
}


