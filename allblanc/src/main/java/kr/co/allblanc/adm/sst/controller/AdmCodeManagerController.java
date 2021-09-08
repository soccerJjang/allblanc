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
import org.springmodules.validation.commons.DefaultBeanValidator;

import kr.co.allblanc.adm.sst.domain.AdmBulletinBoardVO;
import kr.co.allblanc.adm.sst.domain.AdmCodeVO;
import kr.co.allblanc.adm.sst.service.AdmCodeManagerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 코드 컨트롤러
 */
@Controller
@Slf4j
@RequestMapping(value = "${adm.url}/sst/*")
public class AdmCodeManagerController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/sst/";
	
	@Autowired
	private AdmCodeManagerService admCodeManagerService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
	
    /**
	 * 관리자 코드 목록
	 */
	@RequestMapping(value = "codeManagerList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String codeManagerList(@ModelAttribute("searchVO") AdmCodeVO vo, Model model) {
		
		List<AdmCodeVO> resultList = admCodeManagerService.selectCodeManagerOneDepthList(vo);
		model.addAttribute("resultList", resultList);
		model.addAttribute("maxSortOrdr", admCodeManagerService.selectCodeManagerMaxSortOrdr(vo));
		
		return adminPath + baseUrl + "codeManagerList";
	}
	
	/**
	 * 관리자 코드 상세
	 */
	@RequestMapping(value = "codeManagerDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String codeManagerDetail(@ModelAttribute("searchVO") AdmCodeVO vo, Model model) {
		
		AdmCodeVO resultInfo = admCodeManagerService.selectCodeManagerDetail(vo);
		model.addAttribute("resultInfo", resultInfo);
		model.addAttribute("maxSortOrdr", admCodeManagerService.selectCodeManagerMaxSortOrdr(vo));
		
		return "/" + adminPath + baseUrl + "codeManagerDetail";
	}
	
	/**
	 * 관리자 하위 코드 목록
	 */
	@RequestMapping(value = "codeManagerChildList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String codeManagerChildList(@ModelAttribute("searchVO") AdmCodeVO vo, Model model) {
		
		List<AdmCodeVO> resultList = admCodeManagerService.selectCodeManagerChildList(vo);
		model.addAttribute("resultList", resultList);
		
		return "/" + adminPath + baseUrl + "codeManagerChildList";
	}
	
	/**
	 * 관리자 코드 등록
	 */
	@RequestMapping(value = "codeManagerRegistration.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String codeManagerRegistration(@ModelAttribute("searchVO") AdmCodeVO vo, Model model) {
		
		admCodeManagerService.codeManagerRegistration(vo);
		return "redirect:" + adminUrl + baseUrl + "codeManagerList.do";
	}
	
	/**
	 * 관리자 코드 삭제
	 */
	@RequestMapping(value = "codeManagerDeletion.do", method = {RequestMethod.POST})
	public String codeManagerDeletion(@ModelAttribute("searchVO") AdmCodeVO vo, ModelMap model) {
		
		log.info("codeManagerDeletion Start : ########################");
		
		admCodeManagerService.codeManagerDeletion(vo);
		
		log.info("codeManagerDeletion End : ########################");
		
		return "redirect:" + adminUrl + baseUrl + "codeManagerList.do";
	}
	
	/**
	 * 관리자 코드 정렬
	 */
	@RequestMapping(value = "codeManagerOrdrUpdate.do", method = {RequestMethod.POST})
	public String codeManagerOrdrUpdate(@ModelAttribute("searchVO") AdmCodeVO listAdmCodeVO, ModelMap model) {
		
		log.info("codeManagerOrdrUpdate Start : ########################");
		
		if(listAdmCodeVO != null 
				&& listAdmCodeVO.getCodeSeqArray().contains(",")
				&& listAdmCodeVO.getSortOrdrArray().contains(",")) {
			String[] arrayCodeSeq = listAdmCodeVO.getCodeSeqArray().split(",");
			String[] arraySortOrdr = listAdmCodeVO.getSortOrdrArray().split(",");
			for(int i = 0; i < arrayCodeSeq.length; i++) {
				AdmCodeVO vo = new AdmCodeVO();
				vo.setCodeSeq(Integer.parseInt(arrayCodeSeq[i]));
				vo.setSortOrdr(Integer.parseInt(arraySortOrdr[i]));
				admCodeManagerService.codeManagerOrdrUpdate(vo);
			}
		}
		
		log.info("codeManagerOrdrUpdate End : ########################");
		
		return "redirect:" + adminUrl + baseUrl + "codeManagerList.do";
	}
}
