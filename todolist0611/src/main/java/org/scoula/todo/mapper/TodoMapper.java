package org.scoula.todo.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.todo.domain.TodoDTO;

import java.util.List;

public interface TodoMapper {
    // 짧은건 인라인 작성해도 된다.
    @Select("SELECT * FROM todo ORDER BY id DESC")
    List<TodoDTO> selectAll();

    // DTO 는 계층간 이동시 데이터 전달이 목적
    int insertTodo(TodoDTO todo);

    int updateTodo(Long id);

    int deleteTodo(Long id);
}
