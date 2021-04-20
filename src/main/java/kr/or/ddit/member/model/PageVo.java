package kr.or.ddit.member.model;

public class PageVo {
	private int page;
	private int pageSize;
	private String keyword;
		
	public PageVo(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
