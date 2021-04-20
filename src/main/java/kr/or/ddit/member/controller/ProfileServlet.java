package kr.or.ddit.member.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import kr.or.ddit.member.service.UserServiceI;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);
	
	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image");
		
		// userid ?��?��미터�? ?��?��?��?��
		// userService 객체�? ?��?�� ?��?��?��?�� ?���? ?��?�� ?��름을 ?��?��
		// ?��?�� ?��출력?�� ?��?�� ?��진을 ?��?��?��?�� resp객체?�� outputStream?���? ?��?�� ?��?��
		String userid = req.getParameter("userid");
		
		UserVo userVo= userService.selectUser(userid);
		
		String path ="";
		if(userVo.getRealfilename() == null) {
			path = getServletContext().getRealPath("/profile/unknown.png");
		}
		else {
			path= "d:\\upload\\" + userVo.getRealfilename();			
		}
		
		logger.debug("path : {} ", path);
		
		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream sos =  resp.getOutputStream();
		
		byte[] buff = new byte[512];
		
		while(fis.read(buff) != -1) {
			sos.write(buff);
		}
		
		fis.close();
		sos.close();
	}
}












