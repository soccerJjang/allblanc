package kr.co.allblanc.common.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.lgn.service.AdmLoginService;
import kr.co.allblanc.common.domain.CommonUserVO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdmAuthProvider implements AuthenticationProvider {
	
	@Autowired
    private AdmUserDetailsService admUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetails admUserDetails = null;
		Collection<? extends GrantedAuthority> authorities = null;

		try {
			admUserDetails = admUserDetailsService.loadUserByUsername(username);
			if(admUserDetails == null) {
				return null;
			}
			log.info("pw : {}", passwordEncoder.encode(password));
			if(passwordEncoder.matches(password , admUserDetails.getPassword())) {
			} else {
				return null;
			}
			authorities = admUserDetails.getAuthorities();
		} catch(Exception e) {
			e.printStackTrace();
			log.info("authenticate exception : {}", e.getMessage());
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
}