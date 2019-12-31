package server.Businesses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageBus {
	private static String UPLOAD_FOLDER = "/src/main/resources/images/";
	
	public String storageImage(MultipartFile img,String type) {
		String currentPath = Paths.get("").toAbsolutePath().toString();
		File directory = new File(currentPath + UPLOAD_FOLDER + type);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		try { 
            byte[] bytes = img.getBytes();
            
            Date date= new Date();
       	 	long time = date.getTime();
       	 	String imgName = String.valueOf(time) + "_" + img.getOriginalFilename();
            Path path = Paths.get(currentPath + UPLOAD_FOLDER + type +"/" + imgName);
            Files.write(path, bytes);
            
            return type + "/" + imgName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
	}
}
