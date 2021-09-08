package kr.co.allblanc.frt.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface FrtBoardCommonMapper {
	
	Integer frtBoardCommonTotalCnt(AdmBulletinBoardVO vo);
	
	List<AdmBulletinBoardVO> frtBoardCommonList(AdmBulletinBoardVO vo);
	
	AdmBulletinBoardVO frtBoardCommonDetail(AdmBulletinBoardVO vo);
	
	Integer frtBoardCommonOrder(AdmBulletinBoardVO vo);
}
