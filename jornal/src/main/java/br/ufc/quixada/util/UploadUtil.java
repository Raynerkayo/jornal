package br.ufc.quixada.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	public static void saveFile(String path, MultipartFile file) {

		File saveFile = new File(path);
		try {
			FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
			System.out.println("Arquivo salvo em: " + saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
