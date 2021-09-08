package kr.co.allblanc.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;

@Mapper
public interface CommonFileMapper {
	
	public List<AdmBulletinBoardVO> selectFileList(AdmBulletinBoardVO vo);
	
	public void insertFile(AdmBulletinBoardVO vo);
	
	public void updateFile(AdmBulletinBoardVO vo);
	
}
