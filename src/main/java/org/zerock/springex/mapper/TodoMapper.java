package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();


    // 글 추가
    void insert(TodoVO todoVO);


    // 글 전체 조회
    List<TodoVO> selectAll();

    // 글 하나 조회

    TodoVO selectOne(Long tno);

    void update(TodoVO todoVO);

    void delete(Long tno);


    // 페이징이 적용된 글 목록 조회
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

}
