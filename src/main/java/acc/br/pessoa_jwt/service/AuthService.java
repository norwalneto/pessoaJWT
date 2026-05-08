package acc.br.pessoa_jwt.service;

import acc.br.pessoa_jwt.config.JwtService;
import acc.br.pessoa_jwt.domain.dto.JwtResponseDTO;
import acc.br.pessoa_jwt.domain.dto.LoginRequestDTO;
import acc.br.pessoa_jwt.domain.dto.RegisterRequestDTO;
import acc.br.pessoa_jwt.domain.model.User;
import acc.br.pessoa_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtResponseDTO register(RegisterRequestDTO dto) {

        repository.findByEmail(dto.email()).ifPresent(u -> {
            throw new RuntimeException("Usuário já existe");
        });

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(dto.role())
                .build();

        repository.save(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );

        String token = jwtService.generateToken(userDetails);

        return new JwtResponseDTO(token);
    }

    public JwtResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );

        String token = jwtService.generateToken(userDetails);

        return new JwtResponseDTO(token);
    }
}