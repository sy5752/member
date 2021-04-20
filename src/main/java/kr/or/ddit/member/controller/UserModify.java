package kr.or.ddit.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import kr.or.ddit.member.service.UserServiceI;
import member.util.FileUtil;

@MultipartConfig
@WebServlet("/userModify")
public class UserModify extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(UserModify.class);

	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userid = req.getParameter("userid");
		
		//UserVo user = userService.selectUser(userid);
		UserVo user = userService.selectUser(userid);
		
		req.setAttribute("user", user);
		
		req.getRequestDispatcher("/member/userModify.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("usernm");
		String pass = req.getParameter("pass");
		
		Part profile = req.getPart("profile");
		
		String filename = "";
		String realfilename = "";
		
		if(profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			
			realfilename = UUID.randomUUID().toString() + fileExtension;
			
			profile.write("d:\\upload\\"+realfilename);
		
		}else {
			filename = req.getParameter(filename);
			realfilename = req.getParameter(realfilename);
			
		}
		if(filename == null) {
			filename = "";
		}
	    if(realfilename == null) {
	    	realfilename = "";
	    }
		
		String alias = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		
		UserVo userVo = new UserVo(userid,usernm, pass, new Date(), alias, addr1, addr2, zipcode, filename, realfilename);
		//UserVo userVo = new UserVo(userid,usernm, pass, reg_dt, alias, addr1, addr2, zipcode, "","");
		
		int updateCnt = userService.modifyUser(userVo);
		if(updateCnt == 1) {
			// 방법 1 // doGet(req, resp);
			// 방법 2
			resp.sendRedirect(req.getContextPath()+"/user?userid=" + userid);
			
		}
		else {
			doGet(req, resp);
		}
	}
	
}
