package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Controller
public class AuthController {

    private Map<String, String> users = new HashMap<>();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController() {
        // Simulação de banco de dados: usuário "admin" com senha "1234"
        users.put("admin", passwordEncoder.encode("1234"));
    }

    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html no diretório templates
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        if (users.containsKey(username) &&
                passwordEncoder.matches(password, users.get(username))) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Credenciais inválidas");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", session.getAttribute("user"));
        return "dashboard"; // dashboard.html
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
