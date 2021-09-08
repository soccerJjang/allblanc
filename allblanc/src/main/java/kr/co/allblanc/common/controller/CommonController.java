package kr.co.allblanc.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Front 메인 컨트롤러
 */
@Controller
@Slf4j
@RequestMapping(value = "${common.url}/*")
public class CommonController {
	
	@Value("${common.path}")
	private String commonPath;
	private String baseUrl = "/";
	
	/**
	 * 에러 페이지
	 * 1.잘못된 경로로의 접근시
	 * 2.사용자가 직접 링크를 입력하거나 또는 즐겨찾기를 이용하여 관리자 메뉴에 접근시
	 */
	@RequestMapping(value = "/error.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		
		
		return "/" + commonPath + baseUrl + "error";
	}
	
	/**
	 * 에러 페이지
	 * 1.요청된 화면이 없는 경우
	 */
	@RequestMapping(value = "/error404.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String error404(Model model) {
		
		
		return "/" + commonPath + baseUrl + "error404";
	}
	
	/**
	 * 에러 페이지
	 * 1.1시간 이상 장기간 로그인이 없는 경우
	 */
	@RequestMapping(value = "/logOut.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String logOut(Model model) {
		
		
		return "/" + commonPath + baseUrl + "logOut";
	}
	
}
