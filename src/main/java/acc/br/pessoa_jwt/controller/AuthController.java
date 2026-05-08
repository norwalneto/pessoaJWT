package acc.br.pessoa_jwt.controller;

import acc.br.pessoa_jwt.domain.dto.JwtResponseDTO;
import acc.br.pessoa_jwt.domain.dto.LoginRequestDTO;
import acc.br.pessoa_jwt.domain.dto.RegisterRequestDTO;
import acc.br.pessoa_jwt.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> register(
            @RequestBody @Valid RegisterRequestDTO dto
    ) {

        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto
    ) {

        return ResponseEntity.ok(service.login(dto));
    }
}
