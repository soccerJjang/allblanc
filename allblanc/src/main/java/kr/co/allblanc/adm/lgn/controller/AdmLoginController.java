package kr.co.allblanc.adm.lgn.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.allblanc.adm.lgn.service.AdmLoginService;
import kr.co.allblanc.common.domain.AdmUserDetails;
import kr.co.allblanc.common.domain.CommonUserVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 로그인 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value = "${adm.url}/lgn/*")
public class AdmLoginController {
	
	@Value("${adm.path}")
	private String adminPath;
	@Value("${adm.url}")
	private String adminUrl;
	private String baseUrl = "/lgn/";
	private String mainUrl = "/stats/dashboard.do";
	
	@Autowired
	private AdmLoginService admLoginService;
	
	/**
	 * 로그인 페이지
	 */
	@RequestMapping(value = "login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(CommonUserVO commonUserVO, Model model) {

		try {
			// 세션이 있는 상태이면 메인으로 보내고 아니면 로그인으로 ..
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			log.info("auth 정보:{}" , auth.toString());
			log.info("auth getAuthorities 정보:{}" , auth.getAuthorities());
			log.info("auth getPrincipal 정보:{}" , auth.getPrincipal());
			
			// 생성된 인증정보에 맞게 화면 이동
			Collection<? extends GrantedAuthority>  auths = auth.getAuthorities(); 
			if ( auths.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent() ) {
				log.info("메인화면으로 이동");
				return "redirect:" + adminUrl + mainUrl;
			} else {
				log.info("로그인화면으로 이동");
				return "/" + adminPath + baseUrl + "login";
			}
		} catch(Exception e) {
			log.info("Exception : {}, 로그인화면으로 이동", e.getMessage());
			return "/" + adminPath + baseUrl + "login";
		}
	}
	
	/**
	 * 로그인 프로세스
	 */
	@RequestMapping(value = "loginProcess.do", method = {RequestMethod.POST})
	public String loginProcess(AdmUserDetails admUserDetails) {
		AdmUserDetails adm = admLoginService.selectAdmInfo(admUserDetails);
		if(adm == null) {
			log.info("로그인화면으로 이동");
			return "/" + adminPath + baseUrl + "login";
		} else {
			
		}
		return "redirect:" + adminUrl + mainUrl;
	}
	
	/**
	 * 로그아웃
	 */
	@RequestMapping(value = "logout.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session, Model model) {
		
		session.invalidate();
		
		return "redirect:" + adminUrl + baseUrl + "login.do";
	}
}
