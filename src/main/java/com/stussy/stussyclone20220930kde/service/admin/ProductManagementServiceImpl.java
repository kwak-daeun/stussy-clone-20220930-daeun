package com.stussy.stussyclone20220930kde.service.admin;

import com.stussy.stussyclone20220930kde.Exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930kde.dto.CategoryResponseDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductMstOptionRespDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930kde.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    public List<CategoryResponseDto> getCategoryList() throws Exception{
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<CategoryResponseDto>(); //xml에서 만들어서 가져온것
        productManagementRepository.getCategoryList().forEach(category -> {
            categoryResponseDtos.add(category.toDto()); //category를 하나하나 dto에 변환시켜 categoryResponseDtos에 만든다,entity형태를 dto로 변환
        });
        return categoryResponseDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) ==0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");

        }

    }
    @Override
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception {
        List<ProductMstOptionRespDto> list = new ArrayList<ProductMstOptionRespDto>();
        productManagementRepository.getProductMstList().forEach(pdtMst -> {
            list.add(pdtMst.toDto());
        });

        return list;
    }
}
