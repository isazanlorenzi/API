# API

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

<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        .form-container {
            max-width: 400px;
            margin: auto;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Login</h2>

        <!-- Mostra mensagem de erro se existir -->
        <div th:if="${error}" class="error" th:text="${error}"></div>

        <!-- Formulário de login -->
        <form method="POST" th:action="@{/login}">
            <label for="username">Usuário:</label><br>
            <input type="text" id="username" name="username" required><br><br>

            <label for="password">Senha:</label><br>
            <input type="password" id="password" name="password" required><br><br>

            <button type="submit">Entrar</button>
        </form>
    </div>
</body>
</html>

<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #007BFF;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 th:text="'Bem-vindo, ' + ${user} + '!'"></h2>
        <p>Você está logado no sistema.</p>
        <a th:href="@{/logout}">Sair</a>
    </div>
</body>
</html>
