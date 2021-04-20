package kr.or.ddit.member.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import member.util.FileUtil;


@RequestMapping("member")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("login")
	public String login() {
		
		return "member/login";
	}
	@RequestMapping("userController")
	public String userController(String userid, String pwd, HttpSession session ) {
		 
		UserVo user = userService.selectUser(userid);
		if (user !=null && pwd.equals(user.getPass())) {
			
			session.setAttribute("S_USER", user);
			return "redirect:/member/pagingUser";
		
		}
		else {
			
			return "member/login";

		}
		
		
	}
	
	
	@RequestMapping("pagingUser")
	public String pagingUser(String page, String pageSize, String searchType, String keyword, Model model) {
		
		String ps = "";
		
		int page1 = page == null ? 1 : Integer.parseInt(page);
		int pageSize1 = pageSize == null || pageSize.equals("") ? 5 : Integer.parseInt(pageSize);
		
		if(pageSize!=null) {
			ps = pageSize;
		}
		
		if(searchType==null) {
			searchType = "";
		}
		
		if(keyword==null) {
			keyword = "";
		}
		
		PageVo pageVo = new PageVo(page1, pageSize1);
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
		int pagination = (int)Math.ceil((double) userCnt / pageSize1); 
		
		model.addAttribute("ps", ps);
		model.addAttribute("List", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "member/memberList";
	 	
	}
	
	@RequestMapping("user")
	public String userDetail(String userid, Model model) {
	
		UserVo user = userService.selectUser(userid);
		
		model.addAttribute("user", user);
		
		return "member/user";
	}
	
	@RequestMapping(path = "registUser", method = RequestMethod.GET)
	public String registUser() {
		return "member/memberRegist";
	}
	@RequestMapping(path = "registUser", method = RequestMethod.POST)
	public String registUser(UserVo userVo, MultipartFile profile, RedirectAttributes ra) {
	
		
		
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename("");
			userVo.setRealfilename("");
		}else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "D:\\upload\\"+ UUID.randomUUID().toString()+fileExtension;
				//						safsafsjiafjapasfjsapf    +  .jpg		
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File(realFileName));
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		int insertCnt = 0;
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt = 0;
			e.printStackTrace();
		}

		if (insertCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/member/user";
		} else {
			return "member/memberRegist";
		}
		
	}
	@RequestMapping(path = "userModify", method = RequestMethod.GET)
	public String userModify(String userid, Model model) {
		
		UserVo user = userService.selectUser(userid);
		
		model.addAttribute("user", user);
		
		return "member/userModify";
	}
	@RequestMapping(path = "userModify", method = RequestMethod.POST)
	public String userModify(UserVo userVo, Model model, MultipartFile profile) {
		
		UserVo vo = userService.selectUser(userVo.getUserid());
		System.out.println(vo);
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename(vo.getFilename());
			userVo.setRealfilename(vo.getRealfilename());
			
			if(userVo.getFilename()==null) {
				userVo.setFilename("");
			}
			if(userVo.getRealfilename()==null) {
				userVo.setRealfilename("");
			}
			
		}else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "D:/upload/"+UUID.randomUUID().toString()+fileExtension;
				//						safsafsjiafjapasfjsapf    +  .jpg		
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File(realFileName));
			} catch (IllegalStateException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		int updateCnt = userService.modifyUser(userVo);
		if (updateCnt == 1) {
			// ��� 1 // doGet(req, resp); // ��� 2
			return "redirect:/member/user?userid=" + userVo.getUserid();
		}else {
			model.addAttribute("user", userVo);
			return "member/userModify";
		}
	}
	
	@RequestMapping(path = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(String userid, Model model) {
		// model.addAttribute("delete",userService.deleteUser(userid));

		int deleteCnt = 0;

		try {

			deleteCnt = userService.deleteUser(userid);

		} catch (Exception e) {
			deleteCnt = -1;
		}

		if (deleteCnt == 1) {
			return "redirect:/member/pagingUser";

		} else {
			return "redirect:/member/user?userid=" + userid;

		}

	}

	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		
		
		UserVo userVo= userService.selectUser(userid);
		
		String path ="";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}
		else {
			path= userVo.getRealfilename();			
		}		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos =  resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
			
		}catch(Exception e) {	
			e.printStackTrace();
		}
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(path="idCheck", method = RequestMethod.POST)
	public int idCheck(String userid) {
		int insertCnt = userService.idCheck(userid);
		return insertCnt;
	}
}
