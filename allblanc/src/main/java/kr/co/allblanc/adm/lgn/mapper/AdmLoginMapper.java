package kr.co.allblanc.adm.lgn.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.common.domain.AdmUserDetails;

@Mapper
public interface AdmLoginMapper {
	
	public AdmUserDetails loadUserByUsername(String adminId);
	
	public AdmUserDetails selectAdmInfo(AdmUserDetails userDetails);
	
	public int updateAdmLoginDt(String adminSeq);
}
