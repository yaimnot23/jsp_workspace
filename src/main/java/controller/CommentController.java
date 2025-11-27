package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Comment;
import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	private CommentService csv;
       	
    public CommentController() {
    	csv = new CommentServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(">>> path : " + path);
		
		switch (path) {
		case "post":
			try {
				// JS에서 보낸 JSON 데이터를 읽기 (StringBuffer 이용)
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info(">>> sb : " + sb.toString());
				
				// JSON 파싱
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				Comment cvo = new Comment(bno, writer, content);
				int isOk = csv.post(cvo);
				log.info(">>> post : " + (isOk > 0 ? "OK" : "FAIL"));
				
				// 결과 전송
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "list":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				List<Comment> list = csv.getList(bno);
				
				// List -> JSON 변환
				JSONArray jsonList = new JSONArray();
				for(Comment c : list) {
					JSONObject json = new JSONObject();
					json.put("cno", c.getCno());
					json.put("bno", c.getBno());
					json.put("writer", c.getWriter());
					json.put("content", c.getContent());
					json.put("regdate", c.getRegdate());
					jsonList.add(json);
				}
				
				PrintWriter out = response.getWriter();
				out.print(jsonList); // JSON 데이터 전송
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify":
            try {
                StringBuffer sb = new StringBuffer();
                String line = "";
                BufferedReader br = request.getReader();
                while((line = br.readLine()) != null) {
                    sb.append(line);
                }
                
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
                
                int cno = Integer.parseInt(jsonObj.get("cno").toString());
                String content = jsonObj.get("content").toString();
                
                Comment cvo = new Comment(cno, content);
                int isOk = csv.modify(cvo);
                log.info(">>> modify result : " + isOk);
                
                PrintWriter out = response.getWriter();
                out.print(isOk);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
            
        case "remove":
            try {
                // 쿼리스트링(?cno=1) 방식으로 삭제 요청을 받음
                int cno = Integer.parseInt(request.getParameter("cno"));
                int isOk = csv.remove(cno);
                log.info(">>> remove result : " + isOk);
                
                PrintWriter out = response.getWriter();
                out.print(isOk);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
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
