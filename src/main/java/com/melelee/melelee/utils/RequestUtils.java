package com.melelee.melelee.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Request工具类
 *
 * @author mengll
 * @create 2018-11-13 20:39
 **/
public class RequestUtils {

	public static String getOsInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent").toLowerCase();
		String os;
		if (browserDetails.contains("windows")) {
			os = "Windows";
		} else if (browserDetails.contains("mac")) {
			os = "Mac";
		} else if (browserDetails.contains("x11")) {
			os = "Unix";
		} else if (browserDetails.contains("android")) {
			os = "Android";
		} else if (browserDetails.contains("iphone")) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + browserDetails;
		}
		return os;
	}

	public static String getBrowserInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String user = browserDetails.toLowerCase();

		String browser = "";

		//===============Browser===========================
		if (user.contains("edge")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0]
					+ "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0]
						+ "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
			}
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6")) ||
				(user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) ||
				(user.contains("mozilla/4.08")) || (user.contains("mozilla/3"))) {
			browser = "Netscape-?";
		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			String iEVersion = (browserDetails.substring(browserDetails.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser = "IE" + iEVersion.substring(0, iEVersion.length() - 1);
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}
		return browser;
	}
}
