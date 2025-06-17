package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
    // ORDER BY 는 인덱스 컬럼으로 수행해야 오버헤드 적음
    // @Select("SELECT * FROM tbl_board ORDER BY no DESC")
    // public List<BoardVO> getList();
    // MyBatis 는 table 컬럼명과 VO 필드명이 같은 것에 대해 맵핑한다.
    // setNo()
    // setTitle()
    // 이런 동작으로 맵핑이 작동
    // -> VO에 Setter 가 반드시 있어야 함.


    // 복잡한 쿼리는 xml로 진행한다.
    // 리턴 타입 List 는 selectList
    public List<BoardVO> getList();

    // 리턴타입 BoardVO 하나는 selectOne
    public BoardVO get(Long id);

    public void create(BoardVO boardVO);

    public int update(BoardVO board);

    public int delete(Long no);
}
