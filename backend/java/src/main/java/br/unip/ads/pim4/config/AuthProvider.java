package br.unip.ads.pim4.config;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.unip.ads.pim4.application.atendente.AtendenteAppService;
import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private AtendenteAppService atendenteAppService;    

    @Override
    public Authentication authenticate(Authentication auth) throws UsernameNotFoundException, AuthenticationServiceException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        // Regras de autenticacao (usaremos via Basic Authentication)
        
        try {
        	
	        AtendenteResumoDto atendente = atendenteAppService.buscarPorEmailESenha(login, senha);
	        if (atendente != null) {
				return new UsernamePasswordAuthenticationToken(login, senha, emptyList());
	        }
	        
        } catch (Exception e) {
        	
			throw new AuthenticationServiceException("Falha ao autenticar " + e.getMessage());
			
        }

        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
        
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}