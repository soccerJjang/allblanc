package kr.co.allblanc.common.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
* 페이징 처리를 위한 클래스
*/
public class TextPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {
	
	private ServletContext servletContext;
	
    public TextPaginationRenderer() {
    }
    
    public void initVariables() {
    	
    	firstPageLabel    = " <a href=\"?pageIndex={1}\" class=\"paginationInfoText\" onclick=\"{0}({1});return false; \">처음</a> ";
    	previousPageLabel = " <a href=\"?pageIndex={1}\" class=\"paginationInfoText\"  onclick=\"{0}({1});return false; \">이전</a> ";
    	currentPageLabel  = " <strong style=\"color: red; font-size: 18px;\">{0}</strong> ";
    	otherPageLabel    = " <a href=\"?pageIndex={1}\" style=\"color: black; font-size: 18px;\" onclick=\"{0}({1});return false; \">{2}</a> ";
    	nextPageLabel     = " <a href=\"?pageIndex={1}\" class=\"paginationInfoText\"  onclick=\"{0}({1});return false; \">다음</a> ";
    	lastPageLabel     = " <a href=\"?pageIndex={1}\" class=\"paginationInfoText\" onclick=\"{0}({1});return false; \">끝</a> ";
    	
    }
    
    public void setServletContext(ServletContext servletContext) {
    	this.servletContext = servletContext;
    	initVariables();
    }
}