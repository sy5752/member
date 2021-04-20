package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.repository.UserDao;
import kr.or.ddit.member.repository.UserDaoI;


@Service("userService")
public class UserService implements UserServiceI {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public List<UserVo> selectAllUser() {
		
		return userDao.selectAllUser();
		}

	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVo> userList = userDao.selectPagingUser(pageVo);
		int userCnt = userDao.selectAllUserCnt();
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}


	@Override
	public int modifyUser(UserVo userVo) {
		
		return userDao.modifyUser(userVo);
	}



	@Override
	public int registUser(UserVo userVo) {
		return userDao.registUser(userVo);
	}


	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}



	@Override
	public Map<String, Object> selectIdPagingUser(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVo> userList = userDao.selectIdPagingUser(pageVo);
		int userCnt = userDao.selectIdAllUserCnt(pageVo);
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}



	@Override
	public Map<String, Object> selectNmPagingUser(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVo> userList = userDao.selectNmPagingUser(pageVo);
		int userCnt = userDao.selectNmAllUserCnt(pageVo);
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}



	@Override
	public Map<String, Object> selectAlPagingUser(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVo> userList = userDao.selectAlPagingUser(pageVo);
		
		int userCnt = userDao.selectAlAllUserCnt(pageVo);
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}

	@Override
	public int idCheck(String userid) {
		return userDao.idCheck(userid)  ;
	}

	
	
}

