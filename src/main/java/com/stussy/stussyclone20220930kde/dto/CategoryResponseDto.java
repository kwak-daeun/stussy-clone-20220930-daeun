package com.stussy.stussyclone20220930kde.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponseDto {
    //value에 id를 넣어준다. 카테고리 아이디 ex)"1", "2"
    private int id;
    private String name;
}
