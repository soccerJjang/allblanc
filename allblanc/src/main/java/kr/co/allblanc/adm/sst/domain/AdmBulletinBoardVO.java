package kr.co.allblanc.adm.sst.domain;

import kr.co.allblanc.common.domain.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmBulletinBoardVO extends CommonVO {
	
	/* board master */ 
	private long boardSeq;
	private String boardType;
	private String boardTypeNm;

	/* board common */
	private long boardCommonSeq;
	private String boardSj;
	private String boardCn;
	private String parentSeq;
	private long sortOrdr;
	private long readCnt;
	private String useAt;
	private String noticeStartDay;
	private String noticeEndDay;
	private String noticeAt;
	
	/* board album */
	private long boardAlbumSeq;
	
	/* board banner */
	private long boardBannerSeq;
	
	/* board comment */
	private long boardCommentSeq;
	private long boardCommentCn;
	
	/* board design */
	private long boardDesignSeq;
	private String videoUrl;
	private long categorySeq;
	private String sortOrdrUpdateFlag;
	
	/* board faq */
	private long boardFaqSeq;
	private long answerCn;
	
	/* board popup */
	private long boardPopupSeq;
	private String popupNm;
	private String popupDc;
	private long popupLocationLeft;
	private long popupLocationRight;
	private long popupSizeWidth;
	private long popupSizeHeight;
	private String popupTargetYn;
	private String popupKind;
	
	/* board thumbnail */
	private long boardThumbnailSeq;
	
	/* board product */
	private String productCode;
	private String productCodeAutoAt;
	private long category1Seq;
	private long category2Seq;
	private long category3Seq;
	private long price;
	private long productCnt;
	private String planNewAt;
	private String planBestAt;
	private String planRecommendAt;
	private String planDiscountAt;
	private long discountRate;
	private long saleCnt;
	
	/* board file */
	private long boardFileSeq;
	private String filePath;
	private String newFileNm;
	private String originalFileNm;
	private String fileExtr;
	private long fileSize;
	
	/* board list */
	private String boardCommonSeqArray;
	private String sortOrdrArray;
	private String sortOrdrUpdateFlagArray;
}