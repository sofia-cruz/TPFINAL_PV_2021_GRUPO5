package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AutenticationSuccessHandler implements AuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		boolean userConsultor = false;
		boolean userRegistrador = false;
		boolean normal = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			if (grantedAuthority.getAuthority().equals("normal")) {
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				normal = true;
				break;
			} else {
				if (grantedAuthority.getAuthority().equals("CONSULTOR")) {
					userConsultor = true;
					break;
				} else {
					userRegistrador = true;
					break;
				}
			}
	
	}
		if (normal) {  
			System.out.println("--despues de login-> me lleva a...");
			//aquí es donde entro por defecto
			redirectStrategy.sendRedirect(request, response, "/home");
		} else {
			if (userConsultor) {
				redirectStrategy.sendRedirect(request, response, "/consultor");
			} else {
				if (userRegistrador) {
					redirectStrategy.sendRedirect(request, response, "/registrador");
				} else {
					throw new IllegalStateException();
				}
			}
			}
		}
	}


