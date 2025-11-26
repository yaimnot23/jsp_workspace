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

import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/brd")
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
