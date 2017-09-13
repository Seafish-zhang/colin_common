package utils.pay.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;

/**
 *
 * alipay支付
 *
 */

public class AliUtil {

	public static final String ALIPAY_APPID = AliPayConfigUtil
			.getProperty("appid"); // appid

	public static String APP_PRIVATE_KEY = AliPayConfigUtil
			.getProperty("private_key"); // app支付私钥

	public static String ALIPAY_PUBLIC_KEY = AliPayConfigUtil
			.getProperty("public_key"); // 支付宝公钥

	// static {
	// try {
	// InputStream privateIs = AliUtil.class.getResourceAsStream("");
	// APP_PRIVATE_KEY = IOUtils.toString(privateIs, "UTF-8");
	// InputStream publicIs = AliUtil.class.getResourceAsStream("");
	// ALIPAY_PUBLIC_KEY = IOUtils.toString(publicIs, "UTF-8");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// 统一收单交易创建接口
	private static AlipayClient alipayClient = null;

	public static AlipayClient getAlipayClient() {
		if (alipayClient == null) {
			synchronized (AliUtil.class) {
				if (null == alipayClient) {
					alipayClient = new DefaultAlipayClient(
							"https://openapi.alipay.com/gateway.do",
							ALIPAY_APPID, APP_PRIVATE_KEY,
							AlipayConstants.FORMAT_JSON,
							AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY);
				}
			}
		}
		return alipayClient;
	}
}
