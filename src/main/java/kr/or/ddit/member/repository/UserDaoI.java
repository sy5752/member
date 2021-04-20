package kr.or.ddit.member.repository;

import java.util.List;

import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.model.UserVo;





public interface UserDaoI {
	

	
	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	// 사용자 페이징 조회
	List<UserVo> selectPagingUser(PageVo vo);
	
	// 사용자 전체 수 조회
	int selectAllUserCnt();
	
	List<UserVo> selectIdPagingUser(PageVo vo);
	
	int selectIdAllUserCnt(PageVo vo);
	
	List<UserVo> selectNmPagingUser (PageVo vo);
	
	int selectNmAllUserCnt (PageVo vo);
	
	List<UserVo> selectAlPagingUser (PageVo vo);
	
	int selectAlAllUserCnt (PageVo vo);
	
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	// 사용자 신규 등록
	int registUser(UserVo userVo);
	
	// 사용자 삭제
	int deleteUser(String userid);
	
	//아이디 중복
	int idCheck(String userid);
	
	
}
