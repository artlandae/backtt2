package com.apptt2.backend.correo;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
@RestController
@RequestMapping("/correo")
@CrossOrigin()
public class CorreoController {

    private final CorreoService correoService;

    public CorreoController(CorreoService correoService) {
        this.correoService = correoService;
    }

    @PostMapping("/registro")
    public String sendCorreo(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            correoService.sendCorreo(to, subject, body);
            return "Correo enviado exitosamente";
        } catch (MessagingException e) {
            return "Error al enviar correo: " + e.getMessage();
        }
    }
}