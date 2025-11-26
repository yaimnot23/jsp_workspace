package controller;

import java.io.IOException;

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

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 로그객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	// RequestDispatcher - jsp에서 받은 요청을 처리한 후 다른 jsp로 응답을 보낼 때 사용하는 객체
	private RequestDispatcher rdp;
	//jsp (목적지)주소를 저장하는 변수
	private String destPage;
	
	// isOK 변수 DB에서 구문값 체크를 의해 저장하는 변수
	private int isOK;
	
	//service 연결 인터페이스
	private BoardService bsv;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        bsv = new BoardServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get, post 등 오는 모든 요청을 service 메서드에서 처리
		log.info("BoardController service method in Test");
		// jsp에서 요청하는 주소를 받는 객체 /brd/register
		String uri = request.getRequestURI();
		log.info(" >>> {}", uri);
		
		//post로 들어오는 객체는 한글깨짐 방지 => 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		
		switch(path) {
		case "register":
			destPage = "/board/register.jsp"; // webapp 기준
			break;
		case "insert":
			try {
				// title, writer, content
				String title = request.getParameter("title"); // name 값으로 추출
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				
				// DB에 등록하기 위한 객체 생성
				Board b = new Board(title, writer, content);
				log.info(" >>> b : {}", b);
				
				//boardService 객체로 해당 객체 전달
				isOK = bsv.insert(b);
				
				//DB에서 저장이 잘 완료되면 1이 리턴, 안되면 0 리턴
				log.info(" >>> insert : {}", (isOK > 0 ? "등록성공" : "등록실패"));
				
				//처리 후 보내야하는 주소
				destPage = "/board/insert.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		}
		
		// 처리가 완료된 만들어진 응답객체를 jsp화면으로 보내기
		rdp = request.getRequestDispatcher(destPage);
		// 요청한 객체를 가지고 destPage로 포워딩
		rdp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get으로 오는 요청에 대한 처리를 하고, HTML 파일을 생성해 response 객체에 담아 전송 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
