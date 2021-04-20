package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import kr.or.ddit.member.service.UserServiceI;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserServiceI userService = new UserService();
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String userid = req.getParameter("userid");
		String pass = req.getParameter("pwd");
		
		
		UserVo user = userService.selectUser(userid);
		if (user !=null && pass.equals(user.getPass())) {
			HttpSession session = req.getSession();
			session.setAttribute("S_USER", user);
//			req.getRequestDispatcher("/member/memberList.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/pagingUser");
		
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");

		}

	}

}
