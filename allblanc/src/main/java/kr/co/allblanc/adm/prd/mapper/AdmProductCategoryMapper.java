package kr.co.allblanc.adm.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.allblanc.adm.prd.domain.AdmProductCategoryVO;

@Mapper
public interface AdmProductCategoryMapper {
	
	public List<AdmProductCategoryVO> selectProductCategoryOneDepthList(AdmProductCategoryVO vo);
	
	public List<AdmProductCategoryVO> selectProductCategoryAllDepthList(AdmProductCategoryVO vo);
	
	public long selectProductCategoryMaxSortOrdr(AdmProductCategoryVO vo);
	
	public AdmProductCategoryVO selectProductCategoryDetail(AdmProductCategoryVO vo);
	
	public List<AdmProductCategoryVO> selectProductCategoryChildList(AdmProductCategoryVO vo);
	
	public Integer productCategoryRegistration(AdmProductCategoryVO vo);
	
	public Integer productCategoryModification(AdmProductCategoryVO vo);
	
	public Integer productCategoryDeletion(AdmProductCategoryVO vo);
	
	public Integer productCategoryOrdrUpdate(AdmProductCategoryVO vo);
	
	public AdmProductCategoryVO productCategoryPreDetail(AdmProductCategoryVO vo);
	
	public AdmProductCategoryVO productCategoryNextDetail(AdmProductCategoryVO vo);
}
