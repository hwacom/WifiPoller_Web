package com.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.wps.Env;

public class FileUtils {

	public static Path getNewestFile() {
		try {
			Path dir = Paths.get(Env.FILE_FOLDER_PATH.concat(Env.FORMAT_YYYYMMDD_NO_SLASH.format(new Date())));

			Optional<Path> lastFilePath = Files.list(dir)
					.filter(f -> !Files.isDirectory(f))
					.max(Comparator.comparingLong(f -> f.toFile().lastModified()));

			if (lastFilePath.isPresent()) {
				return lastFilePath.get();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> loadFile(Path filePath) {
		List<String> retList = null;
		try {
			if (filePath != null) {
				retList = Files.readAllLines(filePath, StandardCharsets.UTF_8);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;
	}
}
