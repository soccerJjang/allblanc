package kr.co.allblanc.adm.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmAdminVO;

@Mapper
public interface AdmAdminManagerMapper {
	
	Integer adminManagerTotalCnt(AdmAdminVO vo);
	
	List<AdmAdminVO> adminManagerList(AdmAdminVO vo);
	
	AdmAdminVO adminManagerDetail(AdmAdminVO vo);
	
	Integer adminManagerRegistration(AdmAdminVO vo);
	
	Integer adminManagerModification(AdmAdminVO vo);
	
	Integer adminManagerDeletion(AdmAdminVO vo);
}
