package nhom7.shopgiay.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	public String upload(MultipartFile mtf) throws IllegalStateException, IOException {
		String homeDir = System.getProperty("user.home");
		System.out.println(mtf.getContentType());
		File folderUpload = new File(homeDir, "shopgiay/images");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		String fileName = System.currentTimeMillis() + ".png";
		File oldFile = new File(folderUpload.getAbsolutePath(), fileName);
		System.out.println(oldFile);
		mtf.transferTo(oldFile);
		return fileName;
	}
}
