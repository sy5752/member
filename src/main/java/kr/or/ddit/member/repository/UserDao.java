package kr.or.ddit.member.repository;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.db.MybatisUtil;
import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;


@Repository("userDao")
public class UserDao implements UserDaoI{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<UserVo> selectAllUser() {

		return template.selectList("users.selectAllUser");
	}

	@Override
	public UserVo selectUser(String userid) {

		return template.selectOne("users.selectUser", userid);
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {

		return template.selectList("users.selectPagingUser", vo);
	}

	@Override
	public int selectAllUserCnt() {

		return template.selectOne("users.selectAllUserCnt");
	}

	@Override
	public int modifyUser(UserVo userVo) {

		return template.update("users.modifyUser", userVo);
		
	}

	@Override
	public int registUser(UserVo userVo) {
		
		return  template.insert("users.registUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {
		
		return template.delete("users.deleteUser", userid);
	}

	@Override
	public List<UserVo> selectIdPagingUser(PageVo vo) {
		
		return template.selectList("users.selectIdPagingUser", vo);
	}

	@Override
	public int selectIdAllUserCnt(PageVo vo) {
		
		return template.selectOne("users.selectIdAllUserCnt", vo);
	}
	@Override
	public List<UserVo> selectNmPagingUser(PageVo vo) {
		
		return template.selectList("users.selectNmPagingUser", vo);
	}

	@Override
	public int selectNmAllUserCnt(PageVo vo) {
		
		return template.selectOne("users.selectNmAllUserCnt", vo);
	}

	@Override
	public List<UserVo> selectAlPagingUser(PageVo vo) {
		
		return template.selectList("users.selectAlPagingUser", vo);
	}

	@Override
	public int selectAlAllUserCnt(PageVo vo) {
		
		return template.selectOne("users.selectAlAllUserCnt", vo);
	}

	//아이디 중복 체크
	@Override
	public int idCheck(String userid) {
		
		return template.selectOne("users.idCheck", userid);
	}






	
}
