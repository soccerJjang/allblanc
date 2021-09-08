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
public class CommonVO {
	
	public CommonVO() {
	}

	/** 생성일시 */
	private Date createDt;
	
	/** 생성자 */
	private long createSeq;
	
	/** 수정일시 */
	private Date modifyDt;
	
	/** 수정자 */
	private long modifySeq;
    
    /** 현재 페이지 번호 */
    private int currentPageNo = 1;
	
	/** 페이지당 게시글 수 */
    private int pageUnit = 10;
    
    /** 페이지 사이즈 */
    private int pageSize = 10;

    /** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;

    /** 레코드 번호 */
    private int rowNo = 0;
    
    /** 검색 조건 키 */
    private String searchKey;
    
    /** 검색 조건 value */
    private String searchValue;
}