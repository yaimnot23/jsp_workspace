package service;

import java.util.List;
import domain.Comment;

public interface CommentService {
	int post(Comment cvo);
	List<Comment> getList(int bno);
}