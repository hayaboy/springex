package org.zerock.springex.mapper;

import org.apache.ibatis.annotations.Select;


// 매퍼 인터페이스
public interface TimeMapper {

    @Select("select now()")
    String getTime();
}
