package org.scoula.todo.service;

import org.scoula.todo.domain.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO> selectAll();

    int insertTodo(TodoDTO todo);

    int updateTodo(Long id);

    int deleteTodo(Long id);
}
