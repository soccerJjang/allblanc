package kr.co.allblanc.common.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * spring security4 기본 암호화(BCryptPasswordEncoder) 서용 util
*/
public class BCryptPasswordUtil implements PasswordEncoder {

	private PasswordEncoder passwordEncoder;
	
	public BCryptPasswordUtil() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public BCryptPasswordUtil(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String encode(CharSequence rawPassword) {

		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	
}