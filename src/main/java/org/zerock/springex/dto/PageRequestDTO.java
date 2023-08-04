package org.zerock.springex.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Log4j2
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Positive
    @Min(value = 1)
    private int page=1;



    @Builder.Default
    @Positive
    @Min(value = 10)
    @Max(value = 100)
    private int size=10;

    private String link;  // 조회 시 가지고 다닐 page 값과 size값

    // t는 title, w는 writer
    private String[] types;

    //  검색시 문자열
    private String keyword;

    //완료 여부
    private boolean finished;

    private LocalDate from;

    private LocalDate to;




    //1페이지의 경우 건너뛸 것이 없고, 2페이지의 경우 10개를 건너뜀
    public int getSkip(){
        log.info("getSkip에서의 page : " + page);
        return (page-1)*10;
    }



    //조회시 페이지 번호가 붙을 때는 page와 size 등을 같이 전달해 주어야만 조회 페이지에 다시 목록으로 이동할 때 기존 페이지를 볼 수 있게 됩니다.
    //간단한 메서드를 작성해서 필요한 링크를 생성할 때 사용
public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);

            builder.append("&size=" + this.size);

            if(finished){
                builder.append("&finished=on");
            }


            if(types != null && types.length > 0){
                for (int i = 0; i < types.length ; i++) {
                    builder.append("&types=" + types[i]);
                }
            }

            if(keyword != null){
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if(from != null){
                builder.append("&from=" + from.toString());
            }

            if(to != null){
                builder.append("&to=" + to.toString());
            }


            link = builder.toString();
        }

            log.info("link 정보  : "  +  link);
        return link;
    }

    public boolean checkType(String type){

        if(types == null || types.length == 0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

}
