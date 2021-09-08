package kr.co.allblanc.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.allblanc.common.domain.CommonMenuVO;
import kr.co.allblanc.common.service.CommonMenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * tiles 메뉴 셋팅 클래스
*/
@Slf4j
@Component
public class MenuPreparer implements ViewPreparer {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	public CommonMenuService commonMenuService;
	
	@Override
	public void execute(Request request, AttributeContext attributeContext) throws PreparerException {
        
		List<CommonMenuVO> gnbList = commonMenuService.selectAdmGnbMenuList();
        attributeContext.putAttribute("gnbList", new Attribute(gnbList), true);
        
		String url = httpServletRequest.getRequestURI();
        CommonMenuVO commonMenuVO = new CommonMenuVO();
        commonMenuVO.setMenuUrl(url);
        CommonMenuVO rootMenuVO = commonMenuService.selectAdmRootMenuSeq(commonMenuVO);
        if(rootMenuVO == null) {
	        attributeContext.putAttribute("currentMenuSeq", new Attribute(0), true);
	        attributeContext.putAttribute("rootMenuSeq", new Attribute(0), true);
        } else {
	        attributeContext.putAttribute("currentMenuSeq", new Attribute(rootMenuVO.getMenuSeq()), true);
	        attributeContext.putAttribute("rootMenuSeq", new Attribute(rootMenuVO.getRootMenuSeq()), true);
        }
        List<CommonMenuVO> lnbList = commonMenuService.selectAdmLnbMenuList(rootMenuVO);
        if(lnbList == null) {
        	attributeContext.putAttribute("lnbList", new Attribute(0), true);
        } else {
        	attributeContext.putAttribute("lnbList", new Attribute(lnbList), true);
        }
    }
}