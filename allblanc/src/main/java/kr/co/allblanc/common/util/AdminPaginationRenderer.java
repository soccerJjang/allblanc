package kr.co.allblanc.common.util;

import java.text.MessageFormat;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
* 페이징 처리를 위한 클래스
*/
public class AdminPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {
	
	private ServletContext servletContext;
	
    public AdminPaginationRenderer() {
    }
    
    public void initVariables() {
    	
    	firstPageLabel    = "";
    	previousPageLabel = " <a href=\"?pageIndex={1}\" class=\"btn-prev\"  onclick=\"{0}({1});return false; \">이전</a> ";
    	currentPageLabel  = " <a href=\"#\" class=\"on\">{0}</a> ";
    	otherPageLabel    = " <a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a> ";
    	nextPageLabel     = " <a href=\"?pageIndex={1}\" class=\"btn-next\"  onclick=\"{0}({1});return false; \">다음</a> ";
    	lastPageLabel     = "";
    	
    }
    
    public void setServletContext(ServletContext servletContext) {
    	this.servletContext = servletContext;
    	initVariables();
    }
    
    @Override
    public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
    	StringBuffer strBuff = new StringBuffer();

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();

		if (totalPageCount > pageSize) {
			if (firstPageNoOnPageList > pageSize) {
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1) }));
			} else {
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
			}
		}
		strBuff.append("<p>");

		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo) {
				strBuff.append(MessageFormat.format(currentPageLabel, new Object[] { Integer.toString(i) }));
			} else {
				strBuff.append(MessageFormat.format(otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
			}
		}
		strBuff.append("</p>");

		if (totalPageCount > pageSize) {
			if (lastPageNoOnPageList < totalPageCount) {
				strBuff.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize) }));
			} else {
				strBuff.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
			}
		}
		return strBuff.toString();
    	
    }
}