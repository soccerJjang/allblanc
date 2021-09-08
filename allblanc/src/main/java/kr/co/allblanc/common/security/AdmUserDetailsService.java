package kr.co.allblanc.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.lgn.mapper.AdmLoginMapper;
import kr.co.allblanc.common.domain.AdmUserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdmLoginMapper admLoginMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return admLoginMapper.loadUserByUsername(username);
	}
}