package mvc2025.kiwfy_pet.controller;

import mvc2025.kiwfy_pet.service.LostService;
import mvc2025.kiwfy_pet.service.OwnerService;
import mvc2025.kiwfy_pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mvc2025.kiwfy_pet.service.LocalizadorService;

@Controller
public class HomeController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private LostService lostService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalPets", petService.listarTodos().size());
        model.addAttribute("totalOwners", ownerService.listarTodos().size());
        model.addAttribute("totalLost", lostService.listarNaoEncontrados().size());
        return "index";
    }
}
