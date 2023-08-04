package org.zerock.springex.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;


// DTO를 VO로 변환하거나 VO를 DTO로 변환해야 하는 작업이 빈번하므로 이를 처리하기 위해 ModelMapper를 스프링의 빈으로 등록해서 처리
//@Configuration은 해당 클래스가 스프링 빈(Bean)에 대한 설정을 하는 클래스임을 명시
@Configuration  //클래스가 하나 이상의 @Bean 메소드를 선언하고 런타임에 해당 빈에 대한 빈 정의 및 서비스 요청을 생성하기 위해 Spring 컨테이너에 의해 처리될 수 있음을 나타냅니다.
public class ModelMapperConfig {


     // modelMapper 설정
    @Bean  //메서드가 Spring 컨테이너에서 관리할 빈을 생성함을 나타냅니다.
    public ModelMapper getMapper(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }
}
