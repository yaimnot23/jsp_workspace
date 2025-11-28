package handler;

public class PagingHandler {
	// DB/서버에서는 필요하지 않지만 다른쪽에서(화면/처리가) 필요한 경우
	// DB 에서 필요한 VO => domain
	// 데이터 전달용 DTO => domain
	// 그 외 모든 처리는 handler
	
	private int startPage;		// 시작 페이지 번호
	private int endPage;		// 끝 페이지 번호
	private boolean prev;		// 이전 페이지 존재 여부
	private boolean next;		// 다음 페이지 존재 여부
	private int total;			// 전체 게시글 수

}
