package com.stussy.stussyclone20220930kde.service.admin;

import com.stussy.stussyclone20220930kde.dto.CategoryResponseDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930kde.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    public List<CategoryResponseDto> getCategoryList() throws Exception{

        return null;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {

    }
}
