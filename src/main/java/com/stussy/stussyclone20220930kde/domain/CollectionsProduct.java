package com.stussy.stussyclone20220930kde.domain;

import com.stussy.stussyclone20220930kde.dto.CollectionListRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectionsProduct {
    private int id;
    private String pdt_name;
    private int pdt_price;
    private String save_name;
    private int product_total_count;

    public CollectionListRespDto toDto(){
        return CollectionListRespDto.builder()
                .productId(id)
                .productName(pdt_name)
                .productPrice(pdt_price)
                .mainImg(save_name ==null ? "noimage.png " : save_name)
                .productTotalCount(product_total_count)
                .build();
    }


}
