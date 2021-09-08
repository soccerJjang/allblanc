package kr.co.allblanc.frt.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.allblanc.adm.prd.controller.AdmBoardCommonController;
import kr.co.allblanc.adm.prd.domain.AdmProductCategoryVO;
import kr.co.allblanc.adm.prd.service.AdmBoardCommonService;
import kr.co.allblanc.adm.prd.service.AdmProductCategoryService;
import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import kr.co.allblanc.frt.main.service.FrtBoardCommonService;
import lombok.extern.slf4j.Slf4j;

/**
 * Front 메인 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${frt.url}/*")
public class FrtMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrtMainController.class);
	
	@Value("${frt.path}")
	private String frtPath;
	private String baseUrl = "/";
	
	@Autowired
	private FrtBoardCommonService frtBoardCommonService;
	
	@Autowired
	private AdmProductCategoryService admProductCategoryService;
	
    @Autowired
    private DefaultBeanValidator beanValidator;
	
	/**
	 * 메인 페이지
	 */
	@RequestMapping(value = "main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, Model model) {
		
		int totalCnt = frtBoardCommonService.frtBoardCommonTotalCnt(vo);
		log.info("totalCnt : " + totalCnt);
		
		List<AdmBulletinBoardVO> resultList = frtBoardCommonService.frtBoardCommonList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("vo", vo);
		
		AdmProductCategoryVO admProductCategoryVO = new AdmProductCategoryVO();
		List<AdmProductCategoryVO> productCategoryAllDepthList = admProductCategoryService.selectProductCategoryAllDepthList(admProductCategoryVO);
		model.addAttribute("productCategoryAllDepthList", productCategoryAllDepthList);
		
		return frtPath + baseUrl + "main";
	}
	
	/**
	 * 상품 등록
	 */
	@RequestMapping(value = "order.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String order(@ModelAttribute("searchVO") AdmBulletinBoardVO vo
			, BindingResult bindingResult
			, Model model) {
		
		beanValidator.validate(vo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + frtPath + baseUrl + "main.do";
		}
		
		frtBoardCommonService.frtBoardCommonOrder(vo);
		return "redirect:/" + frtPath + baseUrl + "main.do";
	}
	
}
