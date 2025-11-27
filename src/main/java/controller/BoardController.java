package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Board;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 로그 객체
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 객체
	private BoardService bsv;
       
    public BoardController() {
        bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 멤버 변수에 있던 destPage, rdp, isOK를 여기로 옮김
		RequestDispatcher rdp = null;
		String destPage = "";
		int isOK = 0;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		
		log.info(">>> path : {}", path);
		
		switch(path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
			
		case "insert":
			try {
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				
				Board b = new Board(title, content, writer); 
				log.info(" >>> b : {}", b);
				
				// DB에 먼저 넣고 나서 이동해야 함
				isOK = bsv.insert(b);
				log.info(" >>> insert : {}", (isOK > 0 ? "등록성공" : "등록실패"));
				
				response.sendRedirect("/brd/list");
				return; // 여기서 메서드 종료
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case "list":
			try {
				List<Board> list = bsv.getList();
				// log.info(" >>> list : {}", list);
				request.setAttribute("list", list);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "detail": case "modify":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				Board board = bsv.getDetail(bno);
				// log.info(" >>> b : {}", board);
				request.setAttribute("b", board);
				destPage = "/board/"+ path + ".jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "update":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				Board b = new Board(bno, title, content);
				log.info(" >>> b : {}", b);
				
				isOK = bsv.update(b);
				log.info(" >>> update : {}", (isOK > 0 ? "수정성공" : "수정실패"));
				
				// 수정 후 상세페이지로 이동
				response.sendRedirect("/brd/detail?bno=" + bno);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		
		}
		
		
		
		// [공통 처리] 
		// insert 처럼 redirect로 이미 떠난 경우는 실행되지 않음 (위에서 return 했기 때문)
		// list, detail, register 처럼 destPage가 있는 경우만 forward 실행
		if(destPage != null && !destPage.isEmpty()) {
            rdp = request.getRequestDispatcher(destPage);
            rdp.forward(request, response);
        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
}
