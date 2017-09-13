package utils.pay.alipay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 */

public class AliPayConfigUtil {

	private static Properties properties = null;

	public static String getProperty(String key) {
		if (properties == null) {
			synchronized (AliPayConfigUtil.class) {
				if (null == properties) {
					try {
						InputStream is = AliPayConfigUtil.class.getClassLoader()
								.getResourceAsStream("alipay.properties");
						properties = new Properties();
						properties.load(is);
					} catch (IOException e) {
					}
				}
			}
		}
		return properties.getProperty(key);
	}
}
