package kr.co.allblanc.adm.sst.domain;

import java.util.Date;

import kr.co.allblanc.common.domain.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmAdminVO extends CommonVO {
	
	/* admin info */ 
	private long adminSeq;
	private String adminId;
	private String adminNm;
	private String adminPswd;
	private String adminDivision;
	private String useAt;
	private Date loginDt;
}