package kr.co.allblanc.adm.stats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.allblanc.adm.lgn.service.AdmLoginService;
import kr.co.allblanc.common.domain.CommonUserVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 대시보드 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/stats/*")
public class AdmDashboardController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/stats/";
	
	@Autowired
	private AdmLoginService admLoginService;
	
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 대시보드 페이지
	 */
	@RequestMapping(value = "dashboard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dashboard(CommonUserVO commonUserVO, Model model) {

		return adminPath + baseUrl + "dashboard";
	}
}
