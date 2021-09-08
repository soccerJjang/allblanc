package kr.co.allblanc.adm.sls.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface AdmSalesManagerMapper {
	
	Integer salesManagerTotalCnt(AdmBulletinBoardVO vo);
	
	List<AdmBulletinBoardVO> salesManagerList(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO salesManagerDetail(AdmBulletinBoardVO vo);
	
	Integer salesManagerRegistration(AdmBulletinBoardVO vo);
	
	Integer salesManagerModification(AdmBulletinBoardVO vo);
	
	Integer salesManagerDeletion(AdmBulletinBoardVO vo);
	
	Integer salesManagerOrdrUpdate(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO salesManagerPreDetail(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO salesManagerNextDetail(AdmBulletinBoardVO vo);
	
	Integer salesStatustucsTotalCnt(AdmBulletinBoardVO vo);
	
	List<AdmBulletinBoardVO> salesStatustucsList(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO salesStatustucsDetail(AdmBulletinBoardVO vo);
}
