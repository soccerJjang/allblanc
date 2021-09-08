package kr.co.allblanc.adm.lgn.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.lgn.mapper.AdmLoginMapper;
import kr.co.allblanc.common.domain.AdmUserDetails;
import kr.co.allblanc.common.domain.CommonUserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmLoginService {
	
	@Autowired
	private AdmLoginMapper admLoginMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AdmUserDetails loadUserByUsername(String adminId) {
		log.debug("id : {}", adminId);
		return admLoginMapper.loadUserByUsername(adminId);
	}
	
	public AdmUserDetails selectAdmInfo(AdmUserDetails commonUserVO) {
		log.debug("id : {}", commonUserVO.getAdminId());
		log.debug("pwd : {}", commonUserVO.getPassword());
		AdmUserDetails admUserDetails = admLoginMapper.selectAdmInfo(commonUserVO);
		log.debug("db pwd : {}", admUserDetails.getPassword());
		if(passwordEncoder.matches(passwordEncoder.encode(commonUserVO.getPassword()) , admUserDetails.getPassword())) {
			return admUserDetails;
		} else {
			return null;
		}
	}
	
	public int updateAdmLoginDt(String adminId) {
		return admLoginMapper.updateAdmLoginDt(adminId);
	}
}
