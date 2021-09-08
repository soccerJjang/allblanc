package kr.co.allblanc.adm.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.sst.mapper.AdmAdminManagerMapper;
import kr.co.allblanc.adm.sst.domain.AdmAdminVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmAdminManagerService {
	
	@Autowired
	private AdmAdminManagerMapper admDesignMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int adminManagerTotalCnt(AdmAdminVO vo) {
		return (Integer)admDesignMapper.adminManagerTotalCnt(vo);
	}
	
	public List<AdmAdminVO> adminManagerList(AdmAdminVO vo) {
		return admDesignMapper.adminManagerList(vo);
	}
	
	public AdmAdminVO adminManagerDetail(AdmAdminVO vo) {
		return admDesignMapper.adminManagerDetail(vo);
	}
	
	public int adminManagerRegistration(AdmAdminVO vo) {
		vo.setAdminPswd(passwordEncoder.encode(vo.getAdminPswd()));
		return admDesignMapper.adminManagerRegistration(vo);
	}
	
	public int adminManagerModification(AdmAdminVO vo) {
		vo.setAdminPswd(passwordEncoder.encode(vo.getAdminPswd()));
		return admDesignMapper.adminManagerModification(vo);
	}
	
	public int adminManagerDeletion(AdmAdminVO vo) {
		return admDesignMapper.adminManagerDeletion(vo);
	}
}
