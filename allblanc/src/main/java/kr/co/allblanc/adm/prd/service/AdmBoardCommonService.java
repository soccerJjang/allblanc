package kr.co.allblanc.adm.prd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.allblanc.adm.prd.mapper.AdmBoardCommonMapper;
import kr.co.allblanc.common.mapper.CommonFileMapper;
import kr.co.allblanc.common.util.MultipartFileUtils;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmBoardCommonService {
	
	@Autowired
	private AdmBoardCommonMapper admCommonMapper;
	
	@Autowired
	private CommonFileMapper commonFileMapper;
	
	@Autowired
	private MultipartFileUtils multipartFileUtils;
	
	public int boardCommonTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)admCommonMapper.boardCommonTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> boardCommonList(AdmBulletinBoardVO vo) {
		return admCommonMapper.boardCommonList(vo);
	}
	
	public AdmBulletinBoardVO boardCommonDetail(AdmBulletinBoardVO vo) {
		return admCommonMapper.boardCommonDetail(vo);
	}
	
	public int boardCommonRegistration(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		int boardCommonSeq = admCommonMapper.boardCommonRegistration(vo);
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "common");
				vo.setFilePath(fileMap.get("FILE_PATH"));
				vo.setNewFileNm(fileMap.get("NEW_FILE_NAME"));
				vo.setOriginalFileNm(fileMap.get("ORIGIN_FILE_NAME"));
				vo.setFileSize(Integer.parseInt(fileMap.get("FILE_SIZE")));
				commonFileMapper.insertFile(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardCommonSeq;
	}
	
	public int boardCommonModification(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "common");
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
		
		return admCommonMapper.boardCommonModification(vo);
	}
	
	public int boardCommonDeletion(AdmBulletinBoardVO vo) {
		return admCommonMapper.boardCommonDeletion(vo);
	}
	
	public void boardCommonOrdrUpdate(AdmBulletinBoardVO vo) {
		try {
			if("Y".equals(vo.getSortOrdrUpdateFlag())) { // 위로 정렬
				AdmBulletinBoardVO boardCommonPreDetail = admCommonMapper.boardCommonPreDetail(vo);
				if(boardCommonPreDetail != null) {
					long sortOrdr = vo.getSortOrdr();
					vo.setSortOrdr(boardCommonPreDetail.getSortOrdr());
					boardCommonPreDetail.setSortOrdr(sortOrdr);
					admCommonMapper.boardCommonOrdrUpdate(vo);
					admCommonMapper.boardCommonOrdrUpdate(boardCommonPreDetail);
				}
			} else { // 아래로 정렬
				AdmBulletinBoardVO boardCommonNextDetail = admCommonMapper.boardCommonNextDetail(vo);
				if(boardCommonNextDetail != null) {
					long sortOrdr = vo.getSortOrdr();
					vo.setSortOrdr(boardCommonNextDetail.getSortOrdr());
					boardCommonNextDetail.setSortOrdr(sortOrdr);
					admCommonMapper.boardCommonOrdrUpdate(vo);
					admCommonMapper.boardCommonOrdrUpdate(boardCommonNextDetail);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
