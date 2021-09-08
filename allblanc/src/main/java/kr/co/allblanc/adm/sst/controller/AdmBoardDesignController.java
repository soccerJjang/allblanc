package kr.co.allblanc.adm.sst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.allblanc.adm.sst.service.AdmBoardDesignService;
import kr.co.allblanc.adm.sst.service.AdmBoardPopupService;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 디자인 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/sst/*")
public class AdmBoardDesignController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/sst/";
	
	@Autowired
	private AdmBoardDesignService admBoardDesignService;
	
	@Autowired
	private AdmBoardPopupService admBoardPopupService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
	
	/**
	 * 디자인 셋팅 리스트 페이지
	 */
	@RequestMapping(value = "boardDesignList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDesignList(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {

		log.info("boardDesignList Start : ########################");
		
		int totalCnt = admBoardDesignService.boardDesignTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmBulletinBoardVO> boardMasterList = admBoardDesignService.boardDesignList(vo);
		model.addAttribute("resultList", boardMasterList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "boardDesignList");

		log.info("boardDesignList End : ########################");
		
		return adminPath + baseUrl + "boardDesignList";
	}
	
	/**
	 * 디자인 등록페이지
	 */
	@RequestMapping(value = "boardDesignRegistrationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDesignRegistrationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
				
		return adminPath + baseUrl + "boardDesignRegistrationView";
	}
	
	/**
	 * 디자인 등록
	 */
	@RequestMapping(value = "boardDesignRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDesignRegistration(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardDesignRegistrationView.do";
		}
		
		admBoardDesignService.boardDesignRegistration(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardDesignList.do";
	}
	
	/**
	 * 디자인 수정페이지
	 */
	@RequestMapping(value = "boardDesignModificationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDesignModificationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
		
		AdmBulletinBoardVO boardDesignDetail = admBoardDesignService.boardDesignDetail(vo);
		model.addAttribute("boardDesignDetail", boardDesignDetail);
		
		return adminPath + baseUrl + "boardDesignModificationView";
	}
	
	/**
	 * 디자인 수정
	 */
	@RequestMapping(value = "boardDesignModification.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDesignModification(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardDesignModificationView.do";
		}
		
		admBoardDesignService.boardDesignModification(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardDesignList.do";
	}
	
	/**
	 * 디자인 삭제
	 */
	@RequestMapping(value = "boardDesignDeletion.do", method = {RequestMethod.POST})
	public String boardDesignDeletion(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, RedirectAttributesModelMap redirectAttributesModelMap
			, ModelMap model) {
		
		admBoardDesignService.boardDesignDeletion(vo);
		redirectAttributesModelMap.addFlashAttribute("resultMsg", "삭제되었습니다.");
		return "redirect:/" + adminPath + baseUrl + "boardDesignList.do";
	}
	
	/**
	 * 디자인 정렬
	 */
	@RequestMapping(value = "boardDesignOrdrUpdate.do", method = {RequestMethod.POST})
	public String boardDesignOrdrUpdate(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, ModelMap model) {
		
		admBoardDesignService.boardDesignOrdrUpdate(vo);
		
		return "redirect:/" + adminPath + baseUrl + "boardDesignList.do";
	}
	
	/**
	 * 팝업 리스트 페이지
	 */
	@RequestMapping(value = "boardPopupList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardPopupList(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {

		log.info("boardPopupList Start : ########################");
		
		int totalCnt = admBoardPopupService.boardPopupTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmBulletinBoardVO> boardMasterList = admBoardPopupService.boardPopupList(vo);
		model.addAttribute("resultList", boardMasterList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "boardPopupList");

		log.info("boardPopupList End : ########################");
		
		return adminPath + baseUrl + "boardPopupList";
	}
	
	/**
	 * 팝업 등록페이지
	 */
	@RequestMapping(value = "boardPopupRegistrationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardPopupRegistrationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
				
		return adminPath + baseUrl + "boardPopupRegistrationView";
	}
	
	/**
	 * 팝업 등록
	 */
	@RequestMapping(value = "boardPopupRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardPopupRegistration(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardPopupRegistrationView.do";
		}
		
		admBoardPopupService.boardPopupRegistration(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardPopupList.do";
	}
	
	/**
	 * 팝업 수정페이지
	 */
	@RequestMapping(value = "boardPopupModificationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardPopupModificationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
		
		AdmBulletinBoardVO boardPopupDetail = admBoardPopupService.boardPopupDetail(vo);
		model.addAttribute("boardPopupDetail", boardPopupDetail);
		
		return adminPath + baseUrl + "boardPopupModificationView";
	}
	
	/**
	 * 팝업 수정
	 */
	@RequestMapping(value = "boardPopupModification.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardPopupModification(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardPopupModificationView.do";
		}
		
		admBoardPopupService.boardPopupModification(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardPopupList.do";
	}
	
	/**
	 * 팝업 삭제
	 */
	@RequestMapping(value = "boardPopupDeletion.do", method = {RequestMethod.POST})
	public String boardPopupDeletion(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, ModelMap model) {
		
		log.info("boardPopupDeletion Start : ########################");
		
		admBoardPopupService.boardPopupDeletion(vo);
		
		log.info("boardPopupDeletion End : ########################");
		
		return "redirect:/" + adminPath + baseUrl + "boardPopupList.do";
	}
}
