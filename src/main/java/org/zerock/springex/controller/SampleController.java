package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
public class SampleController {

    /*@RequestMapping(value = "/hi",method = RequestMethod.GET)
    public void hi(){
        log.info("nice");
    }*/


    @GetMapping("/nice2")
    public void hi(){
        log.info("nice222");
    }


    @GetMapping("/ex5")  //컨트롤러가 리디렉션 시나리오에 대한 특성을 선택하는 데 사용할 수 있는 모델 인터페이스의 특수화입니다. 리디렉션 속성을 추가하려는 의도는 매우 명시적이므로(즉, 리디렉션 URL에 사용하기 위해) 속성 값은 문자열로 형식화되고 쿼리 문자열에 추가되거나 조직에서 URI 변수로 확장될 수 있도록 그런 방식으로 저장될 수 있습니다.
    public String ex5(RedirectAttributes redirectAttributes){



        // 리디이렉트 할 때 쿼리 스트링( 키 = 밸류 )이 되는 값을 주장, URL에 쿼리 스트링으로 추가
        redirectAttributes.addAttribute("name", "hong");

        //일회용으로만 데이터를 전달하고 삭제되는 값을 지정, URL에는 보이지 않지만 JSP에서는 일회용으로 사용할 있음
        redirectAttributes.addFlashAttribute("result", "nice");

        return "redirect:/ex6";
    }


    @GetMapping("/ex6")
    public void ex(){

    }

    // 스프링 MVC 컨트롤러의 특징
    // 메서드의 파라미터를 기본 자료형이나 객체 자료형을 마음대로 지정
    // 메소드의 리턴타입도 void, String, 객체 등 다양한 타입을 사용할 수 있음
    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info("p1........."+p1);
        log.info("p2........."+p2);
    }



}
