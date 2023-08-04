package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {

    @Autowired(required = false)  //(required = false)로 지정하면 해당 객체를 주입 받지 못하더라도 예외가 발생하지 않도록, 인텔리제이에서 경고 발생하지 않도록 예외시 사용해주면 됨
    private TimeMapper timeMapper;

    @Autowired(required = false)
  private TimeMapper2 timeMapper2;


    @Test
    public void testGetTime1(){
        log.info("time is 1 : " + timeMapper.getTime());
    }

    @Test
    public void testGetTime2(){
       log.info("time is 2 : " + timeMapper2.getNow());
    }

}
