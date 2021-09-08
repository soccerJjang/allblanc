package kr.co.allblanc.adm.prd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.allblanc.adm.prd.domain.AdmProductCategoryVO;
import kr.co.allblanc.adm.prd.service.AdmProductCategoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 상품 카테고리 컨트롤러
 */
@Controller
@Slf4j
@RequestMapping(value = "${adm.url}/prd/*")
public class AdmProductCategoryController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/prd/";
	
	@Autowired
	private AdmProductCategoryService admProductCategoryService;
	
	/**
	 * 관리자 상품 카테고리 목록
	 */
	@RequestMapping(value = "productCategoryList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String productCategoryList(@ModelAttribute("searchVO") AdmProductCategoryVO vo, Model model) {
		
		List<AdmProductCategoryVO> resultList = admProductCategoryService.selectProductCategoryOneDepthList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("maxSortOrdr", admProductCategoryService.selectProductCategoryMaxSortOrdr(vo));
		
		return adminPath + baseUrl + "productCategoryList";
	}
	
	/**
	 * 관리자 상품 카테고리 상세
	 */
	@RequestMapping(value = "productCategoryDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String productCategoryDetail(@ModelAttribute("searchVO") AdmProductCategoryVO vo, Model model) {
		
		AdmProductCategoryVO resultInfo = admProductCategoryService.selectProductCategoryDetail(vo);
		model.addAttribute("resultInfo", resultInfo);
		model.addAttribute("maxSortOrdr", admProductCategoryService.selectProductCategoryMaxSortOrdr(vo));
		
		return "/" + adminPath + baseUrl + "productCategoryDetail";
	}
	
	/**
	 * 관리자 하위 상품 카테고리 목록
	 */
	@RequestMapping(value = "productCategoryChildList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String productCategoryChildList(@ModelAttribute("searchVO") AdmProductCategoryVO vo, Model model) {
		
		List<AdmProductCategoryVO> resultList = admProductCategoryService.selectProductCategoryChildList(vo);
		model.addAttribute("resultList", resultList);
		
		return "/" + adminPath + baseUrl + "productCategoryChildList";
	}
	
	/**
	 * 관리자 상품 카테고리 등록
	 */
	@RequestMapping(value = "productCategoryRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String productCategoryRegistration(@ModelAttribute("searchVO") AdmProductCategoryVO vo, Model model) {
		
		admProductCategoryService.productCategoryRegistration(vo);
		return "redirect:" + adminUrl + baseUrl + "productCategoryList.do";
	}
	
	/**
	 * 관리자 상품 카테고리 삭제
	 */
	@RequestMapping(value = "productCategoryDeletion.do", method = {RequestMethod.POST})
	public String productCategoryDeletion(@ModelAttribute("searchVO") AdmProductCategoryVO vo, ModelMap model) {
		
		admProductCategoryService.productCategoryDeletion(vo);
		
		return "redirect:" + adminUrl + baseUrl + "productCategoryList.do";
	}
	
	/**
	 * 관리자 상품 카테고리 정렬
	 */
	@RequestMapping(value = "productCategoryOrdrUpdate.do", method = {RequestMethod.POST})
	public String productCategoryOrdrUpdate(@ModelAttribute("searchVO") AdmProductCategoryVO listAdmProductCategoryVO, ModelMap model) {
		
		if(listAdmProductCategoryVO != null 
				&& listAdmProductCategoryVO.getProductCategorySeqArray().contains(",")
				&& listAdmProductCategoryVO.getSortOrdrArray().contains(",")) {
			String[] arrayProductCategorySeq = listAdmProductCategoryVO.getProductCategorySeqArray().split(",");
			String[] arraySortOrdr = listAdmProductCategoryVO.getSortOrdrArray().split(",");
			for(int i = 0; i < arrayProductCategorySeq.length; i++) {
				AdmProductCategoryVO vo = new AdmProductCategoryVO();
				vo.setProductCategorySeq(Integer.parseInt(arrayProductCategorySeq[i]));
				vo.setSortOrdr(Integer.parseInt(arraySortOrdr[i]));
				admProductCategoryService.productCategoryOrdrUpdate(vo);
			}
		}
		
		return "redirect:" + adminUrl + baseUrl + "productCategoryList.do";
	}
}
