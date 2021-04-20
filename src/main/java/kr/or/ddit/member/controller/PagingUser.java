package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import kr.or.ddit.member.service.UserServiceI;


@WebServlet("/pagingUser")
public class PagingUser extends HttpServlet{
	// private UserServiceI service = new UserService();
	private UserServiceI userService = new UserService();
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				

		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pageSize");
		
		String searchType = req.getParameter("searchType");
		
		String keyword = req.getParameter("keyword");
		
		String ps = "";
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = pageSizeParam == null || pageSizeParam.equals("") ? 5 : Integer.parseInt(pageSizeParam);
		
		if(pageSizeParam!=null) {
			ps = pageSizeParam;
		}
		
		if(searchType==null) {
			searchType = "";
		}
		
		if(keyword==null) {
			keyword = "";
		}
		
		PageVo pageVo = new PageVo(page, pageSize);
		pageVo.setKeyword(keyword);
		
		
		Map<String, Object> map = null;
		
		if(searchType.equals("i")) {
			map = userService.selectIdPagingUser(pageVo);
		}else if(searchType.equals("n")) {
			map = userService.selectNmPagingUser(pageVo);
		}else if(searchType.equals("a")) {
			map = userService.selectAlPagingUser(pageVo);
		}else {
			map = userService.selectPagingUser(pageVo);
		}
		
		
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		
		System.out.println(userList);
		int userCnt = (int)map.get("userCnt");
		int pagination = (int)Math.ceil((double) userCnt / pageSize); 
		
		req.setAttribute("ps", ps);
	 	req.setAttribute("List", userList); 
	 	req.setAttribute("pagination", pagination); 
	 	req.setAttribute("pageVo", pageVo);
	 	req.setAttribute("searchType", searchType);
	 	req.setAttribute("keyword", keyword);
	 	
	 	
		req.getRequestDispatcher("/member/memberList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
