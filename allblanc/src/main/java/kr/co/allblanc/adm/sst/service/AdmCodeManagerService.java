package kr.co.allblanc.adm.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.sst.domain.AdmCodeVO;
import kr.co.allblanc.adm.sst.mapper.AdmCodeManagerMapper;

@Service
public class AdmCodeManagerService {
	
	@Autowired
	private AdmCodeManagerMapper admCodeManagerMapper;
	
	public List<AdmCodeVO> selectCodeManagerOneDepthList(AdmCodeVO vo) {
		return admCodeManagerMapper.selectCodeManagerOneDepthList(vo);
	}
	
	public long selectCodeManagerMaxSortOrdr(AdmCodeVO vo) {
		return admCodeManagerMapper.selectCodeManagerMaxSortOrdr(vo);
	}
	
	public AdmCodeVO selectCodeManagerDetail(AdmCodeVO vo) {
		return admCodeManagerMapper.selectCodeManagerDetail(vo);
	}
	
	public List<AdmCodeVO> selectCodeManagerChildList(AdmCodeVO vo) {
		return admCodeManagerMapper.selectCodeManagerChildList(vo);
	}
	
	public int codeManagerRegistration(AdmCodeVO vo) {
		if(vo != null && vo.getSortOrdr() < 1) {
			vo.setSortOrdr(admCodeManagerMapper.selectCodeManagerMaxSortOrdr(vo));
		}
		if(vo != null && vo.getCodeSeq() > 0) {
			return admCodeManagerMapper.codeManagerModification(vo);
		} else {
			return admCodeManagerMapper.codeManagerRegistration(vo);
		}
	}
	
	public int codeManagerDeletion(AdmCodeVO vo) {
		return admCodeManagerMapper.codeManagerDeletion(vo);
	}
	
	public void codeManagerOrdrUpdate(AdmCodeVO vo) {
		try {
			admCodeManagerMapper.codeManagerOrdrUpdate(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
