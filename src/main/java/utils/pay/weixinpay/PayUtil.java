package utils.pay.weixinpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Jjd.utils.aliutils.SignUtil;

import com.alipay.api.AlipayConstants;

/**
 * 微信支付工具类
 */

public class PayUtil {

	/**
	 * weixin获取签名
	 *
	 * @param params
	 * @param paternerKey
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getSign(Map<String, String> params, String paternerKey)
			throws UnsupportedEncodingException {
		return MD5Utils.getMD5(
				createSign(params, false) + "&key=" + paternerKey)
				.toUpperCase();
	}

	/**
	 * 构造签名
	 *
	 * @param params
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String createSign(Map<String, String> params, boolean encode)
			throws UnsupportedEncodingException {
		Set<String> keysSet = params.keySet();
		Object[] keys = keysSet.toArray();
		Arrays.sort(keys);
		StringBuffer temp = new StringBuffer();
		boolean first = true;
		for (Object key : keys) {
			if (key == null || StringUtil.isEmpty(params.get(key))) // 参数为空不参与签名
				continue;
			if (first) {
				first = false;
			} else {
				temp.append("&");
			}
			temp.append(key).append("=");
			Object value = params.get(key);
			String valueStr = "";
			if (null != value) {
				valueStr = value.toString();
			}
			if (encode) {
				temp.append(URLEncoder.encode(valueStr, "UTF-8"));
			} else {
				temp.append(valueStr);
			}
		}
		return temp.toString();
	}

	/**
	 * 创建支付随机字符串
	 *
	 * @return
	 */
	public static String getNonceStr() {
		return RandomUtil.randomString(RandomUtil.LETTER_NUMBER_CHAR, 32);
	}

	/**
	 * 支付时间戳
	 *
	 * @return
	 */
	public static String payTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 返回签名编码拼接url
	 *
	 * @param map
	 * @param isEncode
	 * @return
	 */
	public static String getSignEncodeUrl(Map<String, String> map,
			boolean isEncode) {
		String sign = map.get("sign");
		String encodedSign = "";
		if (CollectionUtil.isNotEmpty(map)) {
			map.remove("sign");
			List<String> keys = new ArrayList<String>(map.keySet());
			// key排序
			Collections.sort(keys);

			StringBuilder authInfo = new StringBuilder();

			boolean first = true;// 是否第一个
			for (String key : keys) {
				if (first) {
					first = false;
				} else {
					authInfo.append("&");
				}
				authInfo.append(key).append("=");
				if (isEncode) {
					try {
						authInfo.append(URLEncoder.encode(map.get(key),
								AlipayConstants.CHARSET_UTF8));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else {
					authInfo.append(map.get(key));
				}
			}

			try {
				encodedSign = authInfo.toString() + "&sign="
						+ URLEncoder.encode(sign, AlipayConstants.CHARSET_UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return encodedSign.replaceAll("\\+", "%20");
	}

	/**
	 * 对支付参数信息进行签名
	 *
	 * @param map
	 *            待签名授权信息
	 *
	 * @return
	 */
	public static String getAliSign(Map<String, String> map, String rsaKey) {
		List<String> keys = new ArrayList<>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		boolean first = true;
		for (String key : keys) {
			if (first) {
				first = false;
			} else {
				authInfo.append("&");
			}
			authInfo.append(key).append("=").append(map.get(key));
		}

		return SignUtil.sign(authInfo.toString(), rsaKey);
	}
}
