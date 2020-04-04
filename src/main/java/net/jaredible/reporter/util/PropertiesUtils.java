package net.jaredible.reporter.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
			Properties props = convert(file);
			if (props != null) {
				result.add(props);
			}
		}

		return result.toArray(new Properties[result.size()]);
	}

	public static String remove(Properties properties, String key) {
		String value = properties.getProperty(key);
		properties.remove(key);
		return value;
	}

	public static void print(Properties properties) {
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
		}
	}

}
