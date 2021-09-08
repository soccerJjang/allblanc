package kr.co.allblanc.adm.sls.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.allblanc.adm.sls.mapper.AdmSalesManagerMapper;
import kr.co.allblanc.common.mapper.CommonFileMapper;
import kr.co.allblanc.common.util.MultipartFileUtils;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmSalesManagerService {
	
	@Autowired
	private AdmSalesManagerMapper admCommonMapper;
	
	@Autowired
	private CommonFileMapper commonFileMapper;
	
	@Autowired
	private MultipartFileUtils multipartFileUtils;
	
	public int salesManagerTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)admCommonMapper.salesManagerTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> salesManagerList(AdmBulletinBoardVO vo) {
		return admCommonMapper.salesManagerList(vo);
	}
	
	public AdmBulletinBoardVO salesManagerDetail(AdmBulletinBoardVO vo) {
		return admCommonMapper.salesManagerDetail(vo);
	}
	
	public int salesStatustucsTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)admCommonMapper.salesStatustucsTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> salesStatustucsList(AdmBulletinBoardVO vo) {
		return admCommonMapper.salesManagerList(vo);
	}
}
