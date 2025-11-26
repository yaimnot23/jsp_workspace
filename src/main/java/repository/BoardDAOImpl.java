package repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Board;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	// 로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(Board b) {
		// sql.메서드(namespace.id, 파라미터)
		// 메서드 => select, insert, update, delete
		log.info("BoardDAOImpl Test");
		sql.insert("boardMapper.add",b);
		int isOK = sql.insert("boardMapper.add", b);
		
		// insert, update, delete DB 자체의 값이 변경되는 구문
		// 반드시 commit해야함. => transaction(트랜잭션)
		
		if(isOK>0) sql.commit();
		else sql.rollback();
		
		return isOK;
	}

}
