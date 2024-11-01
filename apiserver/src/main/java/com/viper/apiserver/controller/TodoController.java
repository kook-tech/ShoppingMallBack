package com.viper.apiserver.controller;


import com.viper.apiserver.dto.PageRequestDTO;
import com.viper.apiserver.dto.PageResponseDTO;
import com.viper.apiserver.dto.TodoDTO;
import com.viper.apiserver.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno){
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){
        log.info("list.........."+ pageRequestDTO);

        return todoService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register( @RequestBody TodoDTO dto){
        log.info("todoDTO : " + dto);

        Long tno = todoService.register(dto);
        //새로 생성된 튜플의 tno 값.

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno")Long tno,
                                      @RequestBody TodoDTO todoDTO){

        todoDTO.setTno(tno);
        //혹시 모르니 pathvariable로 받은 아이와 tno 일치하도록

        todoService.modify(todoDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno){
        todoService.remove(tno);

        return Map.of("RESULT","SUCCESS");
    }
}
