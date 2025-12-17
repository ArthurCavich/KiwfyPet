package mvc2025.kiwfy_pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mvc2025.kiwfy_pet.service.LocalizadorService;

@Controller
public class HomeController {

    @Autowired
    private LocalizadorService localizadorService;

    @GetMapping({"/", "/home", "/home.html"})
    public String home(Model model) {
        model.addAttribute("localizadores", localizadorService.listarTodos());
        return "home/index";
    }
}
