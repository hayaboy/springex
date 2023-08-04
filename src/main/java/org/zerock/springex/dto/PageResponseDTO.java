package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.event.MenuDragMouseListener;
import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;  // 1이면
    private int size;
    private int total;

    //시작 페이지 번호      this.end - 9
    private int start;


    //끝 페이지 번호       (int)(Math.ceil(this.page / 10.0))*10
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;



    //PageResponseDTO는 여러 정보를 생성자를 이용해서 받아서 처리하는 것이 안전, 예를 들면 PageRequestDTO의 page, size, TodoDTo의 목록 데이터와 전체 데이터의 개수도 필요

    @Builder(builderMethodName = "withAll")  //builderMethodName 속성은 빌더 메서드의 이름(withAll)을 사용자가 원하는 대로 지정할 수 있도록 합니다.
    public PageResponseDTO(PageRequestDTO pageRequestDTO,List<E> dtoList, int total ){
        this.page =pageRequestDTO.getPage();
        this.size= pageRequestDTO.getSize();
        this.total=total;  //total도 그전에 PageRequestDTO에서 셋팅되서 넘어옴, 현재는 1280
        this.dtoList=dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0))*10;  //마지막 페이지  // 페이지가 1~10이면  end는 10, 11~20 이면 20...

        this.start = this.end - 9;   //시작 페이지    //페이지가 1이면 start가 10-9 이므로 1이됨

        //마지막 페이지의 경우 다시 전체 개수(total)를 고려해야 함
        // 만일 10개씩(size) 보여주는 경우 전체 개수(total)가 75라면 마지막 페이지는 10이 아니라 8이 되어야 함

        int last = (int)(Math.ceil(   (total /  (double)size) )); // total이 1280이고 size가 10이면 last는 128이 나옴



        // 10 > 1280  거짓 이므로 end(10)가 나감
        this.end = end > last ? last : end;  // 마지막 페이지(end)는 앞에서 구한 last 값보다 작은 경우에 last 값이 end가 되어야만 함


        // 이전 페이지의 존재여부는 시작 페이지(start)가 1이라면 무조건 true가 되어야 함
        // 다음(next)은 마지막 페이지(end)와  페이지당 개수(size)를 곱한 값보다 전체 개수(total)가 더 많은지를 보고 판단해야 함
        this.prev = this.start > 1;   // 페이지가 1이면 start가 1이므로 1>1  false이 됨
        this.next = total >  this.end * this.size;   //  1280 > 10 * 10  next는 참

    }
}
