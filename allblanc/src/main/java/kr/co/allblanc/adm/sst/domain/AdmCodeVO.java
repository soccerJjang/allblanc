package kr.co.allblanc.adm.sst.domain;

import kr.co.allblanc.common.domain.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmCodeVO extends CommonVO {
	
	/* manager code */ 
	private long codeSeq;
	private long parentsCodeSeq;
	private String code;
	private String codeNm;
	private String codeDc;
	private long sortOrdr;
	private String useAt;
	private String sortOrdrUpdateFlag;
	
	private long oneDepthCodeManagerSeq;
	private long twoDepthCodeManagerSeq;
	private long threeDepthCodeManagerSeq;
	private long depth;
	
	private String codeSeqArray;
	private String sortOrdrArray;
	private String sortOrdrUpdateFlagArray;
}