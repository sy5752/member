package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;

public class UserServiceTest  {

	@Resource(name = "userService")
	private UserService userService;

	@Before
	public void setup() {
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "���뜒", "���쟾 以묎뎄 以묒븰濡� 76", "4痢�",
				"34940", "brown.png", "uuid-generated-filename.png");

		userService.registUser(userVo);

		userService.deleteUser("ddit_n");
	}

	@After
	public void tearDodwn() {
		userService.deleteUser("testUser");
	}

	@Test
	public void selectAllUserTest() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userService.selectAllUser();

		/*** Then ***/
		assertEquals(17, userList.size());
	}

	@Test
	public void selectUserTest() {
		/*** Given ***/
		String userid = "brown";

		/*** When ***/
		UserVo user = userService.selectUser(userid);

		/*** Then ***/
		assertNotNull(user);
		assertEquals("브라운", user.getUsernm());
	}

	@Test
	public void selectUserNotExsistTest() {
		/*** Given ***/
		String userid = "brownNotexists";

		/*** When ***/
		UserVo user = userService.selectUser(userid);

		/*** Then ***/
		assertNull(user);
	}

	@Test
	public void selectPagingUserTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(2, 5);

		/*** When ***/
		// List<UserVo> userList = userDao.selectPagingUser(page, pagesize);
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		List<UserVo> userList = (List<UserVo>) map.get("userList");
		int userCnt = (int) map.get("userCnt");

		/*** Then ***/
		assertEquals(5, userList.size());
		assertEquals(17, userCnt);
	}

	@Test
	public void modifyUserTest() {
		/*** Given ***/

		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원",
				"34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userService.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}

	@Test
	public void registUserTest() {
		/*** Given ***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(),"개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원",
				"34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int insertCnt = userService.registUser(userVo);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}

}
