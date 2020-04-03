package net.jaredible.reporter.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

public class PropertiesUtils {

	public static Properties convert(MultipartFile file) {
		Properties result = null;

		try (InputStream input = file.getInputStream()) {
			Properties prop = new Properties();
			prop.load(input);
			result = prop;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Properties[] convert(MultipartFile[] files) {
		List<Properties> result = new ArrayList<Properties>();

		for (MultipartFile file : files) {
			Properties prop = convert(file);
			result.add(prop);
		}

		return result.toArray(new Properties[result.size()]);
	}

}
