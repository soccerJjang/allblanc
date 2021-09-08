package kr.co.allblanc.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.allblanc.common.domain.CommonMenuVO;

@Mapper
public interface CommonMenuMapper {
	
	public List<CommonMenuVO> selectAdmGnbMenuList();
	
	public CommonMenuVO selectAdmRootMenuSeq(CommonMenuVO commonMenuVO);
	
	public List<CommonMenuVO> selectAdmLnbMenuList(CommonMenuVO commonMenuVO);
	
	public List<CommonMenuVO> selectFrtGnbMenuList();
	
	public CommonMenuVO selectFrtRootMenuSeq(CommonMenuVO commonMenuVO);
	
	public List<CommonMenuVO> selectFrtLnbMenuList(CommonMenuVO commonMenuVO);
}
