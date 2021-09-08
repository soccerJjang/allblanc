package kr.co.allblanc.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelService {

    
    /**
     * voMap을 엑셀 워크북 객체로 생성
     * @param list
     * @return 생성된 워크북
     */
    public static SXSSFWorkbook makeExcelWorkbook(List<Map<String, Object>> listMap, Map<String, Object> headerMap) {
        
    	SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("sheet1");
        
        int rowCnt = 0;
        int cellCnt = 0;
        int limitCnt = 10000;
        Cell headerCell = null;
        Row headerRow = sheet.createRow(rowCnt);
        
    	for(String hkey : headerMap.keySet()) {
    			headerCell = headerRow.createCell(cellCnt);
    			headerCell.setCellValue((String)headerMap.get(hkey));
    			cellCnt++;
    	}
        Row bodyRow = null;
        Cell bodyCell = null;
        
        try {
        
	        if(listMap.size() > 0) {
	        	for(Map<String, Object> voMap : listMap) {
	            	bodyRow = sheet.createRow(rowCnt+1);
	            	rowCnt++;
	            	cellCnt = 0;
	            	if(rowCnt % limitCnt == 0) {
	            		((SXSSFSheet)sheet).flushRows(limitCnt);
	            	}
		        	for(String hkey : headerMap.keySet()) {
		        		for(String key : voMap.keySet()) {
	    	        		if(key.equals(hkey)) {
	    	        			bodyCell = bodyRow.createCell(cellCnt);
	    	        			cellCnt++;
	    	    	            bodyCell.setCellValue((String)voMap.get(key));
	    	        		}
	    	        	}
	    	        }
	            }
			}
	        
        } catch (Exception e) {
			log.error(e.toString());
		}
        return workbook;
    }
    
    /**
     * 컨트롤러에서 호출
     * @param list
     * @return
     */
    public static SXSSFWorkbook excelFileDownload(List<Map<String, Object>> listMap, Map<String, Object> headerMap) {
        return makeExcelWorkbook(listMap, headerMap);
    }
    

}
