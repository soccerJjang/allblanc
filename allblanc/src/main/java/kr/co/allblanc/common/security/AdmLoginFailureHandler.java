package kr.co.allblanc.common.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;


@Service
public class AdmLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	/*
	 * @Override public void onAuthenticationFailure(HttpServletRequest request ,
	 * HttpServletResponse response, Authentication authentication) throws
	 * IOException, ServletException {
	 * 
	 * super.onAuthenticationFailure(request, response, authentication); }
	 */
	
}
