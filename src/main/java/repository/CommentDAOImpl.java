package repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.Comment;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS = "commentMapper."; // 네임스페이스 설정

	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(Comment cvo) {
		int isOk = sql.insert(NS + "add", cvo);
		if (isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<Comment> getList(int bno) {
		return sql.selectList(NS + "list", bno);
	}
}