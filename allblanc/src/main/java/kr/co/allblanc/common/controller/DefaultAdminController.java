package kr.co.allblanc.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class DefaultAdminController {

	public String getPath(HttpServletRequest request) {
		String path = request.getRequestURI();
		path = path.substring(1, path.indexOf("."));
		path = path.replace("wvAdm", "adm");
		return path;
	}
	
	public String getName(HttpServletRequest request) {
		String path = request.getServletPath();
		path = path.substring(path.lastIndexOf("/"));
		path = path.substring(1, path.indexOf("."));
		
		return path;
	}
}
