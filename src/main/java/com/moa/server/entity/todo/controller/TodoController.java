package com.moa.server.entity.todo.controller;

import com.moa.server.entity.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


}
