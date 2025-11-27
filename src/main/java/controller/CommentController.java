package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.CommentService;
import service.CommentServiceImpl;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	
	// 비동기 방식의 요청을 처리하는 컨트롤러
	// 데이터를 요청한 곳으로 결과를 보냄
	// RequestDispatcher , destPage , setContentType 필요 없음
	
	
       	private CommentService csv;
       	
    public CommentController() {
    	csv = new CommentServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI(); // /jsp_project/cmt/insert
		String path = uri.substring(uri.lastIndexOf("/") + 1); // insert
		log.info(">>> path : " + path);
		
		switch (path) {
		case "insert":
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get으로 들어오는 요청을 처리 => service 를 호출해 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
