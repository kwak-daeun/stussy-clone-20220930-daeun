package com.stussy.stussyclone20220930kde.service.admin;

import com.stussy.stussyclone20220930kde.dto.CategoryResponseDto;
import com.stussy.stussyclone20220930kde.dto.ProductRegisterDtlReqDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductMstOptionRespDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {

    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

    public List<ProductMstOptionRespDto> getProductMstList() throws Exception;

    public List<?> getSizeList(int ProductId) throws Exception;

    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;

    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;


    }

