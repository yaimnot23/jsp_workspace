package service;

import java.util.List;

import org.slf4j.Logger;

import repository.BoardDAO;
import repository.BoardDAOImpl;
import org.slf4j.LoggerFactory;

import domain.Board;

public class BoardServiceImpl implements BoardService {
	
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	//BoardDAO 인터페이스 생성
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(Board b) {
		// TODO Auto-generated method stub
		log.info("BoardServiceImpl Test");
		return bdao.insert(b);
	}

	@Override
	public List<Board> getList() {
		// TODO Auto-generated method stub
		return bdao.getList();
	}
	
	@Override
	public Board getDetail(int bno) {
		return bdao.getDetail(bno);
	}
}
