package _01_register.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DIdentify {
	
	
	/* 驗證帳號
	* 格式：開頭字母，8-30個字，可包含底線、英文、數字
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkMemberId(String memberId) { 
	String regex = "\\A[a-zA-Z](\\w){7,29}\\z"; 
	return Pattern.matches(regex, memberId); 
	} 
	/* 驗證Email
	* @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表郵件服務商
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkEmail(String email) { 
	String regex = "^[^\\s]+@[^\\s]+\\.[^\\s]+$"; 
	return Pattern.matches(regex, email); 
	} 
	/**
	* 驗證身份證號碼
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkIdCard(String idCard) { 
	String regex = "^[A-Z]{1}[0-9]{9}$"; 
	return Pattern.matches(regex,idCard); 
	} 
	/**
	* 驗證手機號碼
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkMobile(String mobile) { 
	String regex = "^09[0-9]{8}$"; 
	return Pattern.matches(regex,mobile); 
	} 
	/**
	* 驗證整數（正整數和負整數）
	* @param digit 一位或多位0-9之間的整數
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkDigit(String digit) { 
	String regex = "\\-?[1-9]\\d "; 
	return Pattern.matches(regex,digit); 
	} 
	/**
	* 驗證整數和浮點數（正負整數和正負浮點數）
	* @param decimals 一位或多位0-9之間的浮點數，如：1.23，233.30
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkDecimals(String decimals) { 
	String regex = "\\-?[1-9]\\d (\\.\\d )?"; 
	return Pattern.matches(regex,decimals); 
	} 
	/**
	* 驗證空白字元
	* @param blankSpace 空白字元，包括：空格、\t、\n、\r、\f、\x0B
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkBlankSpace(String blankSpace) { 
	String regex = "\\s "; 
	return Pattern.matches(regex,blankSpace); 
	} 
	/**
	* 驗證中文
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkChinese(String chinese) { 
	String regex = "[\\u4e00-\\u9fa5]"; 
	return Pattern.matches(regex,chinese); 
	} 
	/**
	* 驗證英文
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkEnglish(String english) { 
	String regex = "/^[a-zA-Z]+$/"; 
	return Pattern.matches(regex,english); 
	} 
	/**
	* 驗證英文混中文
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkEC(String english) { 
	String regex = "^[\\u4e00-\\u9fa5_a-zA-Z]+$"; 
	return Pattern.matches(regex,english); 
	} 
	/**
	* 驗證日期（年月日）
	* @param birthday 日期，格式：1992-09-03，或1992.09.03
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkBirthday(String birthday) { 
	String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}"; 
	return Pattern.matches(regex,birthday); 
	} 
	/**
	* 驗證URL地址
	* @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkURL(String url) { 
	String regex = "(https?://(w{3}\\.)?)?\\w \\.\\w (\\.[a-zA-Z] )*(:\\d{1,5})?(/\\w*)*(\\??(. =.*)?(&. =.*)?)?"; 
	return Pattern.matches(regex, url); 
	} 
	/**
	* <pre>
	* 獲取網址 URL 的一級域
	* </pre>
	* 
	* @param url
	* @return
	*/
	public static String getDomain(String url) {
	Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
	// 獲取完整的域名
	// Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
	Matcher matcher = p.matcher(url);
	matcher.find();
	return matcher.group();
	}
	/**
	* 匹配中國郵政編碼
	* @param postcode 郵政編碼
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkPostcode(String postcode) { 
	String regex = "[1-9]\\d{5}"; 
	return Pattern.matches(regex, postcode); 
	} 
	/**
	* 匹配IP地址(簡單匹配，格式，如：192.168.1.1，127.0.0.1，沒有匹配IP段的大小)
	* @param ipAddress IPv4標準地址
	* @return 驗證成功返回true，驗證失敗返回false
	*/ 
	public static boolean checkIpAddress(String ipAddress) { 
	String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"; 
	return Pattern.matches(regex, ipAddress); 
	} 
}
