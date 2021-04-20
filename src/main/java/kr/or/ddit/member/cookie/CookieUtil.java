package kr.or.ddit.member.cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CookieUtil {
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(CookieUtil.class);


	

	/**  
	 * Method : getCookieValue 
	 * ?‘?„±? : PC-05 
	 * ë³?ê²½ì´? ¥ :  
	 * @param cookieStr
	 * @param cookieName
	 * @return  
	 * Method ?„¤ëª? :  cookieStr?—?„œ cookieName?— ?•´?‹¹?•˜?Š” ì¿ í‚¤ê°’ì„ ì¡°íšŒ
	 */ 
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	//cookieStr : "userid=brown; rememberme=Y; test=testcookie"
	//cookieName : userid, rememberme
	//return : brown, Y
	public static String getCookieValue(String cookieStr, String cookieName) {
		
		//userid=brown; rememberme=Y; test=testcookie
		String[] cookies = cookieStr.split("; ");
		String[] name = new String[cookies.length];
		//cookies[0] = userid=brown
		//cookies[1] = rememberme=Y
		//cookies[2] = test=testcookie
		
//		for(int i=0; i<=cookies.length; i++) {
//			logger.debug("cookies : {}",cookies );
		
		for(String cookieString : cookies){
			logger.debug(cookieString);
			// cookieString : ì¿ í‚¤?´ë¦?=ì¿ í‚¤ê°?
			
			String[] cookie = cookieString.split("=");
			//cookie[0] = ì¿ í‚¤?´ë¦?
			//cookie[1] = ì¿ í‚¤ê°?
			
			//ì°¾ê³ ??•˜?Š” ì¿ í‚¤ ?´ë¦„ì¸ì§? ?™•?¸
			if(cookie[0].equals(cookieName)) {
				return cookie[1];
			}
			
		}
		
		return "";
	}
}
