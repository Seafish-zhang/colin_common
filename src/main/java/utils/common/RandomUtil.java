package utils.common;

import java.util.Random;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import Jjd.mongodb.MongodbManager;

/**
 */

public class RandomUtil {

	public static final String ALL_CHAR = "-_#&$@+-*/%()[]0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String NUMBER_CHAR = "0123456789";

	public static final String SPECIAL_CHAR = "-_#&$@+-*/%()[]";

	public static final String LETTER_NUMBER_CHAR = LETTER_CHAR + NUMBER_CHAR;

	public static final String PROMO_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	//生成优惠码
	public static void main(String[] args) {
		MongoCollection<Document> col = MongodbManager.getInstance().getMongoDatabse().getCollection("promo_code");
		long curTime = System.currentTimeMillis() / 1000;
		for(int count = 0;count < 100;count ++) {
			String code = randomString(PROMO_CHAR, 8);
			Document doc = col.find(Filters.eq("promo_code", code)).first();
			if(doc == null) {
				doc = new Document();
				doc.append("promo_id", MongodbManager.getInstance().getCollectionLongId("promo_code"));
				doc.append("promo_code", code);
				doc.append("promo_price", new Double(10));
				doc.append("limit_price", new Double(10));
				doc.append("promo_type", new Long(0));
				doc.append("user_id", new Long(0));
				doc.append("album_id", new Long(0));
				doc.append("used_time", new Long(0));
				doc.append("proportion", new Long(0));
				doc.append("create_time", curTime);
				doc.append("begin_time", curTime);
				doc.append("end_time", new Long(1503158400));
				col.insertOne(doc);
			} else
				count --;
		}
	}

	/**
	 * 返回一个定长的随机字符串
	 *
	 * @param chars
	 *            模型串
	 * @param length
	 *            随机长度
	 * @return
	 */
	public static String randomString(String chars, int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串字母全部大写
	 *
	 * @param chars
	 *            模型串
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String randomLowerString(String chars, int length) {
		return randomString(chars, length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机字符串字母全部小写
	 *
	 * @param chars
	 *            模型串
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String randomUpperString(String chars, int length) {
		return randomString(chars, length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 *
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String randomZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

}
