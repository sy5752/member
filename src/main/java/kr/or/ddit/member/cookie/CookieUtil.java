package kr.or.ddit.member.cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CookieUtil {
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(CookieUtil.class);


	

	/**  
	 * Method : getCookieValue 
	 * ??±? : PC-05 
	 * λ³?κ²½μ΄? ₯ :  
	 * @param cookieStr
	 * @param cookieName
	 * @return  
	 * Method ?€λͺ? :  cookieStr?? cookieName? ?΄?Ή?? μΏ ν€κ°μ μ‘°ν
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
			// cookieString : μΏ ν€?΄λ¦?=μΏ ν€κ°?
			
			String[] cookie = cookieString.split("=");
			//cookie[0] = μΏ ν€?΄λ¦?
			//cookie[1] = μΏ ν€κ°?
			
			//μ°Ύκ³ ??? μΏ ν€ ?΄λ¦μΈμ§? ??Έ
			if(cookie[0].equals(cookieName)) {
				return cookie[1];
			}
			
		}
		
		return "";
	}
}
