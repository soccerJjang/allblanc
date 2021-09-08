package kr.co.allblanc.adm.prd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.prd.domain.AdmProductCategoryVO;
import kr.co.allblanc.adm.prd.mapper.AdmProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdmProductCategoryService {
	
	@Autowired
	private AdmProductCategoryMapper admProductCategoryMapper;
	
	public List<AdmProductCategoryVO> selectProductCategoryOneDepthList(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.selectProductCategoryOneDepthList(vo);
	}
	
	public List<AdmProductCategoryVO> selectProductCategoryAllDepthList(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.selectProductCategoryAllDepthList(vo);
	}
	
	public long selectProductCategoryMaxSortOrdr(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.selectProductCategoryMaxSortOrdr(vo);
	}
	
	public AdmProductCategoryVO selectProductCategoryDetail(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.selectProductCategoryDetail(vo);
	}
	
	public List<AdmProductCategoryVO> selectProductCategoryChildList(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.selectProductCategoryChildList(vo);
	}
	
	public int productCategoryRegistration(AdmProductCategoryVO vo) {
		if(vo != null && vo.getSortOrdr() < 1) {
			vo.setSortOrdr(admProductCategoryMapper.selectProductCategoryMaxSortOrdr(vo));
		}
		if(vo != null && vo.getProductCategorySeq() > 0) {
			return admProductCategoryMapper.productCategoryModification(vo);
		} else {
			return admProductCategoryMapper.productCategoryRegistration(vo);
		}
	}
	
	public int productCategoryDeletion(AdmProductCategoryVO vo) {
		return admProductCategoryMapper.productCategoryDeletion(vo);
	}
	
	public void productCategoryOrdrUpdate(AdmProductCategoryVO vo) {
		try {
			admProductCategoryMapper.productCategoryOrdrUpdate(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
