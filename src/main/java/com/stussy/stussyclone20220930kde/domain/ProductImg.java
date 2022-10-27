package com.stussy.stussyclone20220930kde.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductImg {
    private int id;
    private int pdt_id;
    private String origin_name;
    private String save_name;
}
