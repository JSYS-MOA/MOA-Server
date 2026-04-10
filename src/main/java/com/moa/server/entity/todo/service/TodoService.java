package com.moa.server.entity.todo.service;

import com.moa.server.entity.todo.TodoRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//todo
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;

    private final TodoRepository todoRepository;

    // 개인 할 일(To-do) 등록, 완료 체크, 수정 및 삭제
}
