package acc.br.pessoa_jwt.service;

import acc.br.pessoa_jwt.domain.model.User;
import acc.br.pessoa_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(

                user.getEmail(),
                user.getPassword(),

                List.of(
                        new SimpleGrantedAuthority(
                                user.getRole().name()
                        )
                )
        );
    }
}
