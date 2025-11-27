package service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.Comment;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(Comment cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public List<Comment> getList(int bno) {
		return cdao.getList(bno);
	}
}