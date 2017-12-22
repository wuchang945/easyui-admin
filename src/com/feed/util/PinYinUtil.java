package com.feed.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinYinUtil {

	/**
	 * 获得首字母
	 * @param inputString
	 * @return
	 */
	public static String getFirstCase(String inputString) {

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer("");

		try {
			for (int i = 0; i < input.length; i++) {
				if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							input[i], format);
					output.append(temp[0].charAt(0));
					// output.append(" ");
				} else
					output.append(Character.toString(input[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	/**
	 * 获得拼音
	 * @param inputString
	 * @return
	 */
	public static String getPinYin(String inputString) {

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer("");

		try {
			for (int i = 0; i < input.length; i++) {
				if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							input[i], format);
					output.append(temp[0]);
					// output.append(" ");
				} else
					output.append(Character.toString(input[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public static void main(String[] args) {
		String chs = "我是中国人! I'm Chinese!";
		System.out.println(chs);
		System.out.println(getPinYin(chs));
	}

}