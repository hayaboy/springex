package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


//    @RequestMapping("/list")
//    public void list(Model model) {
//        log.info("전체 글 조회   todo list.......");
//
//        model.addAttribute("dtoList", todoService.getAll());
//
//
//    }


    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info("pageRequestDTO 객체 : " + pageRequestDTO);
        log.info("pageRequestDTO 객체의 페이지  : " + pageRequestDTO.getPage());


        log.info("검색 시 들어오는 조건 값 "    +   pageRequestDTO.getPage()+ "," + pageRequestDTO.getSize()+ ","+pageRequestDTO.getLink()+ ","
                + pageRequestDTO.getTypes() + "," +pageRequestDTO.getKeyword()+ "," +pageRequestDTO.isFinished()+ "," +pageRequestDTO.getFrom()+ ","
                +pageRequestDTO.getTo() );





        if(bindingResult.hasErrors()){  //에러시 기본값 page는 1로, size는 10으로  세팅, total도 셋팅
            pageRequestDTO =PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));

    }





    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }


//    @PostMapping ("/register")
//    public String registerPOST(TodoDTO todoDTO, RedirectAttributes redirectAttributes) {
//        log.info("POST todo register.......");
//
//        log.info("todoDTO object  : " + todoDTO);
//
//        return "redirect:/todo/list";
//
//    }


    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST todo register.......");

        if (bindingResult.hasErrors()) {
            log.info(" 데이터 입력 시 유효하지 않은 값 들어옴, 처음 입력화면으로 돌아감 ");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }


        log.info("todoDTO object  : " + todoDTO);


        todoService.register(todoDTO);

        return "redirect:/todo/list";

    }


    // 글 하나 조회
    @GetMapping({"/read", "/modify"})
    public void read(Long tno,PageRequestDTO pageRequestDTO , Model model) {
        TodoDTO todoDTO=todoService.getOne(tno);
        log.info( " todoDTO  객체 " + todoDTO);
        model.addAttribute("dto",todoDTO );

    }


    @PostMapping("/modify")
    public String modify( @Valid TodoDTO todoDTO, PageRequestDTO pageRequestDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){


        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);

        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());
//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/read";
    }



    @PostMapping("/remove")
    public String remove(Long tno,  PageRequestDTO pageRequestDTO  , RedirectAttributes redirectAttributes){
        log.info("-------------remove------------------");
        log.info("tno: " + tno);

        todoService.remove(tno);

        //  리스트로 보낼 시 페이지를 1로 강제
//        redirectAttributes.addAttribute("page", 1);
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());



    // 삭제 처리를 하고 나서 리다이렉트 하는 경로에 getLink()의 결과를 반영하도록 수정
        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }


}