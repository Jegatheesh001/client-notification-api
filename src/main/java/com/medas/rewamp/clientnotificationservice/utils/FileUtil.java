package com.medas.rewamp.clientnotificationservice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility for files
 *
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 23, 2020
 *
 */
@Slf4j
@Component
public class FileUtil {
	
	/**
	 * Method to get Base64 from file
	 * 
	 * @param filePath
	 * @return Base64 content
	 */
	public String getBase64FromFile(String filePath) {
		try {
			return Base64.getEncoder().encodeToString(Files.readAllBytes(new File(filePath).toPath()));
		} catch (IOException e) {
			log.error("Could not read file: " + filePath, e);
			return "";
		}
	}
	
}
