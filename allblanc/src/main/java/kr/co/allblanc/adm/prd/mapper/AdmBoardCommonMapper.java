package kr.co.allblanc.adm.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface AdmBoardCommonMapper {
	
	Integer boardCommonTotalCnt(AdmBulletinBoardVO vo);
	
	List<AdmBulletinBoardVO> boardCommonList(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO boardCommonDetail(AdmBulletinBoardVO vo);
	
	Integer boardCommonRegistration(AdmBulletinBoardVO vo);
	
	Integer boardCommonModification(AdmBulletinBoardVO vo);
	
	Integer boardCommonDeletion(AdmBulletinBoardVO vo);
	
	Integer boardCommonOrdrUpdate(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO boardCommonPreDetail(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO boardCommonNextDetail(AdmBulletinBoardVO vo);
}
