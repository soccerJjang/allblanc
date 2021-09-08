package kr.co.allblanc.frt.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.allblanc.adm.prd.mapper.AdmBoardCommonMapper;
import kr.co.allblanc.common.mapper.CommonFileMapper;
import kr.co.allblanc.common.util.MultipartFileUtils;
import kr.co.allblanc.frt.main.mapper.FrtBoardCommonMapper;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FrtBoardCommonService {
	
	@Autowired
	private FrtBoardCommonMapper frtCommonMapper;
	
	@Autowired
	private CommonFileMapper commonFileMapper;
	
	public int frtBoardCommonTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)frtCommonMapper.frtBoardCommonTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> frtBoardCommonList(AdmBulletinBoardVO vo) {
		return frtCommonMapper.frtBoardCommonList(vo);
	}
	
	public AdmBulletinBoardVO frtBoardCommonDetail(AdmBulletinBoardVO vo) {
		return frtCommonMapper.frtBoardCommonDetail(vo);
	}
	
	public int frtBoardCommonOrder(AdmBulletinBoardVO vo) {
		
		int boardCommonSeq = frtCommonMapper.frtBoardCommonOrder(vo);
		
		return boardCommonSeq;
	}
}
