package domain;

public class PagingVO {
	
	// page 처리를 위해 db에서 필요한 객체를 만들어주는 VO
	// select * from board order by bno desc limit #{start}, #{perPageNum}
	// limit 번지, 개수(한 화면에 출력할 게시글 수)
	// 번지는 0번지부터 시작 => 계산해서 리턴
	
	private int pageNo;		// 현재 페이지 번호
	private int qty; // 한 페이지당 출력할 게시글 수
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo <= 0) {
			this.pageNo = 1;
			return;
		}
		this.pageNo = pageNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		if(qty <= 0 || qty > 100) {
			this.qty = 10;
			return;
		}
		this.qty = qty;
	}
	public int getPageStart() {
		return (this.pageNo - 1) * this.qty;
	}
	
	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + "]";
	}

	public void setPageList(int int1) {
		// TODO Auto-generated method stub
		return ;
		
	}

}
