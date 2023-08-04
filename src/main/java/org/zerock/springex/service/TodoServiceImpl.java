package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{



    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

//    @Autowired
//    private TodoMapper todoMapper;
//
//    @Autowired
//    private ModelMapper modelMapper;


    @Override
    public void register(TodoDTO todoDTO) {
        log.info("modelMapper object : " + modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class );


        log.info("todoVO object " + todoVO);

        todoMapper.insert(todoVO);


    }


    //페이징 적용한 전체 목록 가져오기
    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList =todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList =voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class)).collect(Collectors.toList());

        int total=todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll().dtoList(dtoList).total(total).pageRequestDTO(pageRequestDTO).build();


        return pageResponseDTO;
    }

//    @Override
//    public List<TodoDTO> getAll() {
//
//        List<TodoDTO> dtoList=todoMapper.selectAll().stream().map( vo ->  modelMapper.map(vo, TodoDTO.class)).collect(Collectors.toList());
//
//        return dtoList;
//    }

    @Override
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO=todoMapper.selectOne(tno);
        TodoDTO  todoDTO = modelMapper.map(todoVO,  TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class );

        todoMapper.update(todoVO);
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }
}
