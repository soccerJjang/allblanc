package kr.co.allblanc.adm.sst.domain;

import kr.co.allblanc.common.domain.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmManagerMenuVO extends CommonVO {
	
	/* manager menu */ 
	private long menuSeq;
	private long parentsMenuSeq;
	private String menuNm;
	private String menuUrl;
	private String menuType;
	private long sortOrdr;
	private String useAt;
	private String loginAt;
	
	private long oneDepthMenuSeq;
	private long twoDepthMenuSeq;
	private long threeDepthMenuSeq;
}