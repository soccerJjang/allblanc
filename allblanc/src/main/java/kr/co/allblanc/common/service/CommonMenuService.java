package kr.co.allblanc.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.allblanc.common.domain.CommonMenuVO;
import kr.co.allblanc.common.mapper.CommonMenuMapper;
import lombok.extern.slf4j.Slf4j;

@Service
public class CommonMenuService {
	
	@Autowired
	private CommonMenuMapper comMenuMapper;
	

	public List<CommonMenuVO> selectAdmGnbMenuList() {
		return comMenuMapper.selectAdmGnbMenuList();
	}

	public CommonMenuVO selectAdmRootMenuSeq(CommonMenuVO commonMenuVO) {
		return comMenuMapper.selectAdmRootMenuSeq(commonMenuVO);
	}
	
	public List<CommonMenuVO> selectAdmLnbMenuList(CommonMenuVO commonMenuVO) {
		return comMenuMapper.selectAdmLnbMenuList(commonMenuVO);
	}

	public CommonMenuVO selectFrtRootMenuSeq(CommonMenuVO commonMenuVO) {
		return comMenuMapper.selectFrtRootMenuSeq(commonMenuVO);
	}
	
	public List<CommonMenuVO> selectFrtGnbMenuList() {
		return comMenuMapper.selectFrtGnbMenuList();
	}
	
	public List<CommonMenuVO> selectFrtLnbMenuList(CommonMenuVO commonMenuVO) {
		return comMenuMapper.selectFrtLnbMenuList(commonMenuVO);
	}
}
