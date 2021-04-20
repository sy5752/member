package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.repository.UserDao;

public class UserDaoTest extends UserDao {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Before
	public void setup() {
		// userDao = new UserDao();

		// 테스트에서 사용할 신규 사용자 추가
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "대덕", "대전 중구 중앙로 76", "4층",
				"34940", "brown.png", "uuid-generated-filename.png");

		userDao.registUser(userVo);

		// 신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userDao.deleteUser("ddit_n");

	}

	@After
	public void tearDodwn() {
		userDao.deleteUser("testUser");
	}
	
	@Test
	public void selectAllUserTest() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.selectAllUser();

		/*** Then ***/
		assertEquals(21, userList.size());
	}

	@Test
	public void selectUserTest() {
		/*** Given ***/
		String userid = "brown";

		/*** When ***/
		UserVo user = userDao.selectUser(userid);

		/*** Then ***/
		assertNotNull(user);
		assertEquals("브라운", user.getUsernm());
	}

	@Test
	public void selectPagingUserTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(2, 5);

		/*** When ***/
		// List<UserVo> userList = userDao.selectPagingUser(page, pagesize);
		List<UserVo> userList = userDao.selectPagingUser(pageVo);

		/*** Then ***/
		assertEquals(5, userList.size());
	}

	@Test
	public void selectAllUserCntTest() {
		/*** Given ***/

		/*** When ***/
		int userCnt = userDao.selectAllUserCnt();

		/*** Then ***/
		assertEquals(17, userCnt);
	}

	@Test
	public void modifyUserTest() {
		/*** Given ***/

		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(), "媛쒕컻�썝_m", "���쟾�떆 以묎뎄 以묒븰濡� 76", "4痢� ���뜒�씤�옱媛쒕컻�썝",
				"34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int updateCnt = userDao.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCnt);
	}

	@Test
	public void registUserTest() {
		/*** Given ***/

		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(), "媛쒕컻�썝_m", "���쟾�떆 以묎뎄 以묒븰濡� 76", "4痢� ���뜒�씤�옱媛쒕컻�썝",
				"34940", "brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int insertCnt = userDao.registUser(userVo);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}

	@Test
	public void deleteUserTest() {
		/*** Given ***/
		String userid = "testUser";

		/*** When ***/
		int deleteCnt = userDao.deleteUser(userid);

		/*** Then ***/
		assertEquals(1, deleteCnt);
	}

}
