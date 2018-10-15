package br.unip.ads.pim4.config;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.repository.AtendenteRepository;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private AtendenteRepository atendenteRepo;    

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        // Regras de autenticacao (usaremos via Basic Authentication)

        EMail email = new EMail(login);
        Atendente atendente = atendenteRepo.findByPessoaEmailAndSenha(email, senha);
        if (atendente != null) {
			return new UsernamePasswordAuthenticationToken(login, senha, emptyList());
        }

        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}