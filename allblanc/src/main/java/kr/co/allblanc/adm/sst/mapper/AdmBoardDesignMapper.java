package kr.co.allblanc.adm.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface AdmBoardDesignMapper {
	
	public Integer boardDesignTotalCnt(AdmBulletinBoardVO vo);
	
	public List<AdmBulletinBoardVO> boardDesignList(AdmBulletinBoardVO vo);
	
	public AdmBulletinBoardVO boardDesignDetail(AdmBulletinBoardVO vo);
	
	public Integer boardDesignRegistration(AdmBulletinBoardVO vo);
	
	public Integer boardDesignModification(AdmBulletinBoardVO vo);
	
	public Integer boardDesignDeletion(AdmBulletinBoardVO vo);
	
	public Integer boardDesignOrdrUpdate(AdmBulletinBoardVO vo);
	
	public AdmBulletinBoardVO boardDesignPreDetail(AdmBulletinBoardVO vo);
	
	public AdmBulletinBoardVO boardDesignNextDetail(AdmBulletinBoardVO vo);
}
