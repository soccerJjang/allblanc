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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.allblanc.adm.sst.domain.AdmAdminVO;
import kr.co.allblanc.adm.sst.service.AdmAdminManagerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/sst/*")
public class AdmAdminManagerController {
	
	@Value("${adm.path}")
	private String adminPath;
	private String baseUrl = "/sst/";
	
	@Autowired
	private AdmAdminManagerService admAdminManagerService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
	
	/**
	 * 관리자 목록
	 */
	@RequestMapping(value = "adminManagerList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminManagerList(@ModelAttribute("searchVO") AdmAdminVO vo, Model model) {
		
		log.info("managerList Start : ########################");
		
		int totalCnt = admAdminManagerService.adminManagerTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmAdminVO> boardMasterList = admAdminManagerService.adminManagerList(vo);
		model.addAttribute("resultList", boardMasterList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "adminManagerList");

		log.info("managerList End : ########################");
		
		return adminPath + baseUrl + "adminManagerList";
	}
	
	/**
	 * 관리자 등록페이지
	 */
	@RequestMapping(value = "adminManagerRegistrationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminManagerRegistrationView(@ModelAttribute("searchVO") AdmAdminVO vo, Model model) {
				
		return adminPath + baseUrl + "adminManagerRegistrationView";
	}
	
	/**
	 * 관리자 등록
	 */
	@RequestMapping(value = "adminManagerRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminManagerRegistration(@ModelAttribute("searchVO") AdmAdminVO vo
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "adminManagerRegistrationView.do";
		}
		
		admAdminManagerService.adminManagerRegistration(vo);
		return "redirect:/" + adminPath + baseUrl + "adminManagerList.do";
	}
	
	/**
	 * 관리자 수정페이지
	 */
	@RequestMapping(value = "adminManagerModificationView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminManagerModificationView(@ModelAttribute("searchVO") AdmAdminVO vo, Model model) {
		
		AdmAdminVO adminManagerDetail = admAdminManagerService.adminManagerDetail(vo);
		model.addAttribute("adminManagerDetail", adminManagerDetail);
		
		return adminPath + baseUrl + "adminManagerModificationView";
	}
	
	/**
	 * 관리자 수정
	 */
	@RequestMapping(value = "adminManagerModification.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminManagerModification(@ModelAttribute("searchVO") AdmAdminVO vo
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + adminPath + baseUrl + "adminManagerModificationView.do";
		}
		
		admAdminManagerService.adminManagerModification(vo);
		return "redirect:/" + adminPath + baseUrl + "adminManagerList.do";
	}
	
	/**
	 * 관리자 삭제
	 */
	@RequestMapping(value = "adminManagerDeletion.do", method = {RequestMethod.POST})
	public String adminManagerDeletion(@ModelAttribute("searchVO") AdmAdminVO vo, ModelMap model) {
		
		log.info("adminManagerDeletion Start : ########################");
		
		admAdminManagerService.adminManagerDeletion(vo);
		
		log.info("adminManagerDeletion End : ########################");
		
		return "redirect:/" + adminPath + baseUrl + "adminManagerList.do";
	}
	
}
