package kr.co.allblanc.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ExcelDownUtils {

	@Value("${upload.path}")
	private String uploadPath;
	@Value("${upload.url}")
	private String uploadUrl;
	
	public void download(
			HttpServletResponse response,
			Map<String, Object> bean, 
			String fileName, 
			String templateFile)
            throws InvalidFormatException {
	
		// 받아오는 매개변수 bean는 디비에서 뽑아온 데이터
		// fileName 은 다운로드 받을때 지정되는 파일명
		// templateFile 는 템플릿 엑셀 파일명이다.
		
		// 별도로 다운로드 만들기 귀찮으까 이런식으로 만들어서 바로 엑셀 생성후 다운 받게 
		try {
		
		    InputStream is = new BufferedInputStream(new FileInputStream(templateFile));
		    XLSTransformer xls = new XLSTransformer();
		    
		    
		    Workbook workbook = xls.transformXLS(is, bean);		    
		    
		    // 임직원관리의 경우 엑셀에 이미지 포함..
		    if(templateFile.endsWith("파일명.xlsx")) {
			    Sheet sheet0 = workbook.getSheetAt(0);
		        for (Row row: sheet0) {
		        	int rowNum = row.getRowNum();
		        	//파일명 12번째
		            String path = row.getCell(13).getStringCellValue();
		            if(rowNum > 1 && !path.isEmpty()) {
		            	// TODO: 업로드경로가 겹치는듯.
		            	path = uploadPath+path.replace(uploadUrl,"");
			            // 이미지 파일 로드
			            InputStream inputStream = new FileInputStream(path);
			            byte[] bytes = IOUtils.toByteArray(inputStream);
			            int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
			            inputStream.close();
			            
			            CreationHelper helper = workbook.getCreationHelper();
			            Drawing drawing = sheet0.createDrawingPatriarch();
			            ClientAnchor anchor = helper.createClientAnchor();
			            
			            // 이미지를 출력할 CELL 위치 선정
			            anchor.setCol1(11);
			            anchor.setRow1(row.getRowNum());
			            
			            // 이미지 그리기
			            Picture pict = drawing.createPicture(anchor, pictureIdx);
			            
			            // 이미지 사이즈 비율 설정
			            //pict.resize();
			            pict.resize(1.0, 1.0);
			            //double height = pict.getImageDimension().getHeight();
			            row.setHeightInPoints((float) 180);	 
		            }
		        }
		    }
		    
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("utf-8"),"8859_1") + ".xlsx\"");
		    
		    OutputStream os = response.getOutputStream();
		    
		    workbook.write(os);
		    
		    workbook.close();		    
		    is.close();
		    os.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
