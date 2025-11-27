package repository;

import java.util.List;
import domain.Comment;

public interface CommentDAO {
	int insert(Comment cvo);
	List<Comment> getList(int bno);
	int update(Comment cvo);
    int delete(int cno);
}