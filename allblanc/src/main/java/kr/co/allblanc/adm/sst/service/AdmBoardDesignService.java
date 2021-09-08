package kr.co.allblanc.adm.sst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.allblanc.adm.sst.mapper.AdmBoardDesignMapper;
import kr.co.allblanc.common.mapper.CommonFileMapper;
import kr.co.allblanc.common.util.MultipartFileUtils;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdmBoardDesignService {
	
	@Autowired
	private AdmBoardDesignMapper admDesignMapper;
	
	@Autowired
	private CommonFileMapper commonFileMapper;
	
	@Autowired
	private MultipartFileUtils multipartFileUtils;
	
	public int boardDesignTotalCnt(AdmBulletinBoardVO vo) {
		return (Integer)admDesignMapper.boardDesignTotalCnt(vo);
	}
	
	public List<AdmBulletinBoardVO> boardDesignList(AdmBulletinBoardVO vo) {
		return admDesignMapper.boardDesignList(vo);
	}
	
	public AdmBulletinBoardVO boardDesignDetail(AdmBulletinBoardVO vo) {
		return admDesignMapper.boardDesignDetail(vo);
	}
	
	public int boardDesignRegistration(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		int boardDesignSeq = admDesignMapper.boardDesignRegistration(vo);
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "design");
				vo.setFilePath(fileMap.get("FILE_PATH"));
				vo.setNewFileNm(fileMap.get("NEW_FILE_NAME"));
				vo.setOriginalFileNm(fileMap.get("ORIGIN_FILE_NAME"));
				vo.setFileSize(Integer.parseInt(fileMap.get("FILE_SIZE")));
				commonFileMapper.insertFile(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDesignSeq;
	}
	
	public int boardDesignModification(AdmBulletinBoardVO vo, MultipartFile imgFile) {
		
		Map<String, String> fileMap = new HashMap<>();
		try {
			if(imgFile != null && !imgFile.isEmpty()) {
				fileMap = multipartFileUtils.toMap(imgFile, "design");
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
		
		return admDesignMapper.boardDesignModification(vo);
	}
	
	public int boardDesignDeletion(AdmBulletinBoardVO vo) {
		return admDesignMapper.boardDesignDeletion(vo);
	}
	
	public void boardDesignOrdrUpdate(AdmBulletinBoardVO vo) {
		try {
			if("Y".equals(vo.getSortOrdrUpdateFlag())) { // 위로 정렬
				AdmBulletinBoardVO boardDesignPreDetail = admDesignMapper.boardDesignPreDetail(vo);
				if(boardDesignPreDetail != null) {
					long sortOrdr = vo.getSortOrdr();
					vo.setSortOrdr(boardDesignPreDetail.getSortOrdr());
					boardDesignPreDetail.setSortOrdr(sortOrdr);
					admDesignMapper.boardDesignOrdrUpdate(vo);
					admDesignMapper.boardDesignOrdrUpdate(boardDesignPreDetail);
				}
			} else { // 아래로 정렬
				AdmBulletinBoardVO boardDesignNextDetail = admDesignMapper.boardDesignNextDetail(vo);
				if(boardDesignNextDetail != null) {
					long sortOrdr = vo.getSortOrdr();
					vo.setSortOrdr(boardDesignNextDetail.getSortOrdr());
					boardDesignNextDetail.setSortOrdr(sortOrdr);
					admDesignMapper.boardDesignOrdrUpdate(vo);
					admDesignMapper.boardDesignOrdrUpdate(boardDesignNextDetail);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
