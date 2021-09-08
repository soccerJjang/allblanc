package kr.co.allblanc.adm.prd.controller;

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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.allblanc.adm.prd.domain.AdmProductCategoryVO;
import kr.co.allblanc.adm.prd.service.AdmBoardCommonService;
import kr.co.allblanc.adm.prd.service.AdmProductCategoryService;
import kr.co.allblanc.adm.sst.service.AdmBoardPopupService;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 상품 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/prd/*")
public class AdmBoardCommonController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/prd/";
	
	@Autowired
	private AdmBoardCommonService admBoardCommonService;
	
	@Autowired
	private AdmProductCategoryService admProductCategoryService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
	
	/**
	 * 상품 리스트 페이지
	 */
	@RequestMapping(value = "boardCommonList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardCommonList(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
		
		int totalCnt = admBoardCommonService.boardCommonTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmBulletinBoardVO> resultList = admBoardCommonService.boardCommonList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "boardCommonList");
		
		return adminPath + baseUrl + "boardCommonList";
	}
	
	/**
	 * 상품 등록페이지
	 */
	@RequestMapping(value = "boardCommonRegistrationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardCommonRegistrationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
		
		AdmProductCategoryVO admProductCategoryVO = new AdmProductCategoryVO();
		List<AdmProductCategoryVO> productCategoryAllDepthList = admProductCategoryService.selectProductCategoryAllDepthList(admProductCategoryVO);
		model.addAttribute("productCategoryAllDepthList", productCategoryAllDepthList);
		
		return adminPath + baseUrl + "boardCommonRegistrationView";
	}
	
	/**
	 * 상품 등록
	 */
	@RequestMapping(value = "boardCommonRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardCommonRegistration(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardCommonRegistrationView.do";
		}
		
		admBoardCommonService.boardCommonRegistration(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardCommonList.do";
	}
	
	/**
	 * 상품 수정페이지
	 */
	@RequestMapping(value = "boardCommonModificationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardCommonModificationView(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {
		
		AdmProductCategoryVO admProductCategoryVO = new AdmProductCategoryVO();
		List<AdmProductCategoryVO> productCategoryAllDepthList = admProductCategoryService.selectProductCategoryAllDepthList(admProductCategoryVO);
		model.addAttribute("productCategoryAllDepthList", productCategoryAllDepthList);
		
		AdmBulletinBoardVO boardCommonDetail = admBoardCommonService.boardCommonDetail(vo);
		model.addAttribute("boardCommonDetail", boardCommonDetail);
		
		return adminPath + baseUrl + "boardCommonModificationView";
	}
	
	/**
	 * 상품 수정
	 */
	@RequestMapping(value = "boardCommonModification.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardCommonModification(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, MultipartFile imgFile
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "boardCommonModificationView.do";
		}
		
		admBoardCommonService.boardCommonModification(vo, imgFile);
		return "redirect:/" + adminPath + baseUrl + "boardCommonList.do";
	}
	
	/**
	 * 상품 삭제
	 */
	@RequestMapping(value = "boardCommonDeletion.do", method = {RequestMethod.POST})
	public String boardCommonDeletion(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, ModelMap model) {
		
		admBoardCommonService.boardCommonDeletion(vo);
		
		return "redirect:/" + adminPath + baseUrl + "boardCommonList.do";
	}
	
	/**
	 * 상품 정렬
	 */
	@RequestMapping(value = "boardCommonOrdrUpdate.do", method = {RequestMethod.POST})
	public String boardCommonOrdrUpdate(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, ModelMap model) {
		
		admBoardCommonService.boardCommonOrdrUpdate(vo);
		
		return "redirect:/" + adminPath + baseUrl + "boardCommonList.do";
	}
}
