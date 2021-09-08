package kr.co.allblanc.adm.sls.controller;

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
import kr.co.allblanc.adm.sls.service.AdmSalesManagerService;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 매출 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/sls/*")
public class AdmSalesManagerController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/sls/";
	
	@Autowired
	private AdmSalesManagerService admSalesManagerService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
	
	/**
	 * 매출 관리 페이지
	 */
	@RequestMapping(value = "salesManagerList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String salesManagerList(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {

		log.info("salesManagerList Start : ########################");
		
		int totalCnt = admSalesManagerService.salesManagerTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmBulletinBoardVO> resultList = admSalesManagerService.salesManagerList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "salesManagerList");

		log.info("salesManagerList End : ########################");
		
		return adminPath + baseUrl + "salesManagerList";
	}
	
	/**
	 * 매출 통계 페이지
	 */
	@RequestMapping(value = "salesStatustucsList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String salesStatustucsList(@ModelAttribute("searchVO") AdmBulletinBoardVO vo, Model model) {

		log.info("salesStatustucsList Start : ########################");
		
		int totalCnt = admSalesManagerService.salesStatustucsTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		paginationInfo.setTotalRecordCount(totalCnt);
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmBulletinBoardVO> resultList = admSalesManagerService.salesStatustucsList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		model.addAttribute("paginationInfo", paginationInfo);
		log.info("return : " + adminPath + baseUrl + "salesManagerList");

		log.info("salesStatustucsList End : ########################");
		
		return adminPath + baseUrl + "salesManagerList";
	}
}
