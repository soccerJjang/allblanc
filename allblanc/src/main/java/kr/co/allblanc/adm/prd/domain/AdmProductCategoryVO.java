package kr.co.allblanc.adm.prd.domain;

import kr.co.allblanc.common.domain.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmProductCategoryVO extends CommonVO {
	
	/* product category */ 
	private long productCategorySeq;
	private long parentsProductCategorySeq;
	private String productCategoryNm;
	private String productCategoryUrl;
	private String productCategoryType;
	private long sortOrdr;
	private String useAt;
	
	private long oneDepthProductCategorySeq;
	private long twoDepthProductCategorySeq;
	private long threeDepthProductCategorySeq;
	private long depth;
	
	private String productCategorySeqArray;
	private String sortOrdrArray;
	private String sortOrdrUpdateFlagArray;
}