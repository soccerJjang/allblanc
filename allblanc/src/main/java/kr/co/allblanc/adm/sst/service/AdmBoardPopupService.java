package kr.co.allblanc.adm.sst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.allblanc.adm.sst.mapper.AdmBoardPopupMapper;
import kr.co.allblanc.common.mapper.CommonFileMapper;
import kr.co.allblanc.common.util.MultipartFileUtils;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmBoardPopupService {
	
	@Autowired
	private AdmBoardPopupMapper admPopupMapper;
	
	@Autowired
	private CommonFileMapper commonFileMapper;
	
	@Autowired
	private MultipartFileUtils multipartFileUtils;
	
	public int boardPopupTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)admPopupMapper.boardPopupTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> boardPopupList(AdmBulletinBoardVO vo) {
		return admPopupMapper.boardPopupList(vo);
	}
	
	public AdmBulletinBoardVO boardPopupDetail(AdmBulletinBoardVO vo) {
		return admPopupMapper.boardPopupDetail(vo);
	}
	
	public int boardPopupRegistration(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		int boardPopupSeq = admPopupMapper.boardPopupRegistration(vo);
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "popup");
				vo.setFilePath(fileMap.get("FILE_PATH"));
				vo.setNewFileNm(fileMap.get("NEW_FILE_NAME"));
				vo.setOriginalFileNm(fileMap.get("ORIGIN_FILE_NAME"));
				vo.setFileSize(Integer.parseInt(fileMap.get("FILE_SIZE")));
				commonFileMapper.insertFile(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardPopupSeq;
	}
	
	public int boardPopupModification(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "popup");
				vo.setFilePath(fileMap.get("FILE_PATH"));
				vo.setNewFileNm(fileMap.get("NEW_FILE_NAME"));
				vo.setOriginalFileNm(fileMap.get("ORIGIN_FILE_NAME"));
				vo.setFileSize(Integer.parseInt(fileMap.get("FILE_SIZE")));
				
				List<AdmBulletinBoardVO> filelist = commonFileMapper.selectFileList(vo);
				if(filelist != null && filelist.size() > 0) {
					vo.setBoardFileSeq(filelist.get(0).getBoardFileSeq());
					commonFileMapper.updateFile(vo);
				} else {
					commonFileMapper.insertFile(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return admPopupMapper.boardPopupModification(vo);
	}
	
	public int boardPopupDeletion(AdmBulletinBoardVO vo) {
		return admPopupMapper.boardPopupDeletion(vo);
	}
}
