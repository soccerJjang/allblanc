package kr.co.allblanc.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.allblanc.common.domain.CommonUserVO;

@Mapper
public interface CommonUserMapper {
	
	public CommonUserVO selectAdminDetail(Map<String, Object> map);
	
	public CommonUserVO selectMemberDetail(Map<String, Object> map);
}
