package kr.co.allblanc.common.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import kr.co.allblanc.adm.lgn.service.AdmLoginService;
import kr.co.allblanc.adm.sst.domain.AdmAdminVO;
import kr.co.allblanc.common.domain.AdmUserDetails;
import kr.co.allblanc.common.mapper.CommonMenuMapper;
import lombok.extern.slf4j.Slf4j;

@Service
public class AdmLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static int TIME = 60 * 30; // 30분
	@Autowired
	private AdmLoginService admLoginSerive;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
			, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		request.getSession().setMaxInactiveInterval(TIME);
		admLoginSerive.updateAdmLoginDt((String)authentication.getPrincipal());
		
		try {
			AdmUserDetails admUserDetails = admLoginSerive.loadUserByUsername((String) authentication.getPrincipal());
			request.getSession().setAttribute("adminId", (String)authentication.getPrincipal());
			request.getSession().setAttribute("adminNm", (String)admUserDetails.getAdminNm());
		} catch(Exception e) {
			e.printStackTrace();
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
