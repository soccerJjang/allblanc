package kr.co.allblanc.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
public class MultipartFileUtils {
	
	@Value("${upload.url}")
	private String uploadUrl;
	
	@Value("${upload.path}")
	private String uploadPath;
			
	public Map<String, String> toMap(MultipartFile multipartFile, String path) throws Exception {
		String orgFileName = multipartFile.getOriginalFilename();
		String saveFileName = getUUIDFileName(orgFileName);
		
		String filePath = "";
		String fileURL = "";
		String dbFilePath = "";
		
		if(!"".equals(path) && path != null){
			path = path.replaceAll("\\/", "");
			path = path.replaceAll("\\\\", "");
			filePath = uploadPath + File.separatorChar + path + File.separatorChar + getDate("yyyyMMdd") + File.separatorChar;
			fileURL = uploadUrl + "/" + path + "/" + getDate("yyyyMMdd") + "/";
			dbFilePath = path + "/" + getDate("yyyyMMdd") + "/";
		} else {
			filePath = uploadPath + File.separatorChar + getDate("yyyyMMdd") + File.separatorChar;
			fileURL = uploadUrl + "/" + getDate("yyyyMMdd") + "/";
			dbFilePath = getDate("yyyyMMdd") + "/";
		}
		
		
		File file = new File(filePath + saveFileName);
		File saveDir = new File(filePath);
		
		if (!saveDir.exists()){
			if (saveDir.mkdirs() == Boolean.FALSE) {
				throw new Exception("upload directory create failure.");
			}
		}
						
		multipartFile.transferTo(file);		
		
		Map<String, String> m = Maps.newHashMap();
		
		m.put("FILE_PATH", dbFilePath);
		m.put("NEW_FILE_NAME", saveFileName);
		m.put("ORIGIN_FILE_NAME", orgFileName);
		m.put("FILE_URL", fileURL + saveFileName);
//		m.put("FILE_TYPE", fileType);
//		m.put("CONTENT_TYPE", String.valueOf(contentType));
		m.put("FILE_SIZE", String.valueOf(multipartFile.getSize()));
//		m.put("EXPIRE_DAY", String.valueOf(expireDay));
		
		return m;
	}

	public List<Map<String, String>> toMaps(MultipartFile[] multipartFiles, String path) throws Exception {

		List<Map<String, String>> l = Lists.newArrayList();
		
		int num = 0;
		for (MultipartFile multipartFile : multipartFiles) {
			if(multipartFile != null && !"".equals(multipartFile.getOriginalFilename())) {
//				l.add(toMap(multipartFile, path, "I", 9, 0));
				l.add(toMap(multipartFile, path));
				num ++;
			}
		}

		return l;
	}
	
	public int fileDelete(String path) {
		int res = 0;
		File file = new File("/WVWas/"+path);
		if(file.exists()) {
			if(file.delete()) {
				res++;
			} else {
				res = 0;
			}
		}
		return res;
	}
	
	public static String getUUIDFileName(String fileName) {
		String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		fileName = UUID.randomUUID().toString() + fileType;
		return fileName;
	}
	
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	public static String getDate(String format) {
		return getDate(getCalendar(), format);
	}
	
	public static String getDate(Calendar c, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(c.getTime());
	} 
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
}
