package kr.co.allblanc.adm.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface AdmBoardPopupMapper {
	
	Integer boardPopupTotalCnt(AdmBulletinBoardVO vo);
	
	List<AdmBulletinBoardVO> boardPopupList(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO boardPopupDetail(AdmBulletinBoardVO vo);
	
	Integer boardPopupRegistration(AdmBulletinBoardVO vo);
	
	Integer boardPopupModification(AdmBulletinBoardVO vo);
	
	Integer boardPopupDeletion(AdmBulletinBoardVO vo);
}
