package kr.co.allblanc.common.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonMenuVO {

	public CommonMenuVO() {
	}

	/** 생성일시 */
	private Date createDt;
	
	/** 생성자 */
	private long createSeq;
	
	/** 수정일시 */
	private Date modifyDt;
	
	/** 수정자 */
	private long modifySeq;
    
    /** 메뉴 뎁스 */
    private int menuDepth = 0;
	
	/** 메뉴 seq */
    private int menuSeq = 0;
    
    /** 부모 메뉴 seq */
    private int parentsMenuSeq = 0;
    
    /** 하위 메뉴 개수 */
    private int childMenuCnt = 0;
    
    /** 최상위 메뉴 seq */
    private int rootMenuSeq = 0;

    /** 메뉴 명 */
    private String menuNm;

    /** 메뉴 주소 */
    private String menuUrl;
}