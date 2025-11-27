package repository;

import java.util.List;

import domain.Board;

public interface BoardDAO {

	int insert(Board b);

	List<Board> getList();

}
