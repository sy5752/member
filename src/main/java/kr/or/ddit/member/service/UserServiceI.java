package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;



public interface UserServiceI {

	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	//사용자 페이징 조회
	Map<String, Object> selectPagingUser(PageVo pageVo);
	
	Map<String, Object> selectIdPagingUser(PageVo pageVo);
	
	Map<String, Object> selectNmPagingUser(PageVo pageVo);
	
	Map<String, Object> selectAlPagingUser(PageVo pageVo);
	
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	// 사용자 신규 등록
	int registUser(UserVo userVo);
	
	//사용자 삭제
	int deleteUser(String userid);
	
	//아이디 중복
	int idCheck(String userid);
	
}
