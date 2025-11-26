package service;

import org.slf4j.Logger;

import repository.BoardDAO;
import repository.BoardDAOImpl;
import org.slf4j.LoggerFactory;

public class BoardServiceImpl implements BoardService {
	
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	//BoardDAO 인터페이스 생성
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

}
