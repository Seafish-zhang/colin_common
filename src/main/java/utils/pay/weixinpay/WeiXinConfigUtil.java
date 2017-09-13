package utils.pay.weixinpay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 */

public class WeiXinConfigUtil {

	private static Properties properties = null;

	public static String getProperty(String key) {
		if (properties == null) {
			synchronized (WeiXinConfigUtil.class) {
				if (null == properties) {
					try {
						InputStream is = WeiXinConfigUtil.class.getClassLoader()
								.getResourceAsStream("weixin.properties");
						properties = new Properties();
						properties.load(is);
					} catch (IOException e) {
						System.out.println("error");
					}
				}
			}
		}
		return properties.getProperty(key);
	}
}
