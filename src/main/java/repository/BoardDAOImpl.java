package repository;

import java.util.List;

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
//		sql.insert("boardMapper.add",b);
		int isOK = sql.insert("boardMapper.add", b);
		
		// insert, update, delete DB 자체의 값이 변경되는 구문
		// 반드시 commit해야함. => transaction(트랜잭션)
		
		if(isOK>0) sql.commit();
		else sql.rollback();
		
		return isOK;
	}
	
	@Override
	public List<Board> getList() {
		List<Board> list = sql.selectList("boardMapper.list");
		
		return list;
	}
	
	@Override
	public Board getDetail(int bno) {
	    return sql.selectOne("boardMapper.detail", bno);
	}

	@Override
	public int update(Board b) {
	    int isOK = sql.update("boardMapper.update", b);
	    
	    if(isOK > 0) sql.commit();
	    else sql.rollback();
	    
	    return isOK;
	}
	
	@Override
	public int remove(int bno) {
	    // boardMapper.xml의 id="remove"를 호출
	    int isOK = sql.delete("boardMapper.remove", bno);
	    
	    // DB 변경 사항이므로 commit 필수
	    if(isOK > 0) sql.commit();
	    else sql.rollback();
	    
	    return isOK;
	}
	

}
