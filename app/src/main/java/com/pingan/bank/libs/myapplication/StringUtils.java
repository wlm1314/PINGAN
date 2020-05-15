package com.pingan.bank.libs.myapplication;

import java.math.BigDecimal;

import android.text.TextUtils;

/**
 * 文字工具类
 * 
 * 
 */
public class StringUtils {

	/**
	 * 格式化字符串<br />
	 * 主要用于金额格式化，制定小数点位数<br />
	 * 整数部分可以根据需要决定是否增加逗号分割
	 * 
	 * @param str
	 *            原始字符串，不包含小数点
	 * @param decimals
	 *            小数点后位置
	 * @param bl
	 *            整数部分是否需要,分割
	 * @return String
	 */
	public static String formatAmount(String str, int decimals, boolean bl) {
		if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)
				|| "0".equalsIgnoreCase(str)) {
			str = "0000000000";
		}
		if (str.length() < decimals) {
			str = "00000000" + str;
		}
		if (str.contains(",")) {
			str = str.replaceAll(",", "");
		}
		boolean negative = str.startsWith("-");
		if (negative) {
			str = str.replaceAll("-", "");
		}
		if (str.contains(".")) {
			str = str.replaceAll("\\.", "");
		}
		StringBuilder sb = new StringBuilder(str);
		if (sb.length() < decimals) {
			return str;
		}
		BigDecimal bigDecimal = new BigDecimal(sb.insert(
				sb.length() - decimals, ".").toString());
		sb.delete(0, sb.length());
		sb.append(bigDecimal.toPlainString());
		sb.reverse();
		StringBuilder sb2 = new StringBuilder();
		if (decimals > 0) {
			sb2.append(sb.subSequence(0, decimals + 1));
			sb.delete(0, decimals + 1);
		}
		if (bl) {
			for (int i = 0; i < (((sb.length() % 3) == 0) ? sb.length() / 3
					: sb.length() / 3 + 1); i++) {
				sb2.append(
						sb.subSequence(i * 3,
								((i + 1) * 3 > sb.length()) ? sb.length()
										: (i + 1) * 3)).append(",");
			}
		}
		sb2.deleteCharAt(sb2.length() - 1);
		if (negative) {
			return sb2.reverse().insert(0, "-").toString();
		}
		return sb2.reverse().toString();
	}

	/**
	 * 适用于以元为单位的金额
	 * 
	 * @param amt
	 *            以元为单位的金额,可包含小数点,不含小数点则会附加decimals位小数
	 * @param decimals
	 *            小数位数
	 * @param bl
	 *            整数部分是否需要用“,”分割
	 * @return String
	 */
	public static String formatAmt(String amt, int decimals, boolean comma) {
		int n = 0;
		if (TextUtils.isEmpty(amt) || "null".equals(amt)) {
			amt = "00000000000";
		}
		if (amt.endsWith(".")) {
			amt = amt + "0";
		}
		if (amt.contains(".")) {
			n = amt.split("\\.")[1].length();
		}
		while (n < decimals) {
			amt += "0";
			n++;
		}
		while (n > decimals) {
			amt = amt.substring(0, amt.length() - 1);
			n--;
		}
		return StringUtils.formatAmount(amt, decimals, comma);
	}
}
