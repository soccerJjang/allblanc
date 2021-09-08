package kr.co.allblanc.adm.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.sst.domain.AdmCodeVO;

@Mapper
public interface AdmCodeManagerMapper {
	
public List<AdmCodeVO> selectCodeManagerOneDepthList(AdmCodeVO vo);
	
	public long selectCodeManagerMaxSortOrdr(AdmCodeVO vo);
	
	public AdmCodeVO selectCodeManagerDetail(AdmCodeVO vo);
	
	public List<AdmCodeVO> selectCodeManagerChildList(AdmCodeVO vo);
	
	public Integer codeManagerRegistration(AdmCodeVO vo);
	
	public Integer codeManagerModification(AdmCodeVO vo);
	
	public Integer codeManagerDeletion(AdmCodeVO vo);
	
	public Integer codeManagerOrdrUpdate(AdmCodeVO vo);
	
	public AdmCodeVO codeManagerPreDetail(AdmCodeVO vo);
	
	public AdmCodeVO codeManagerNextDetail(AdmCodeVO vo);
}
