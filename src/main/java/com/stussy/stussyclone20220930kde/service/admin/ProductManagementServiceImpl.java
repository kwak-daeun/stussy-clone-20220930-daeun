package com.stussy.stussyclone20220930kde.service.admin;

import com.stussy.stussyclone20220930kde.Exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930kde.Exception.CustomValidationException;
import com.stussy.stussyclone20220930kde.domain.ProductImg;
import com.stussy.stussyclone20220930kde.dto.CategoryResponseDto;
import com.stussy.stussyclone20220930kde.dto.ProductRegisterDtlReqDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductImgReqDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductMstOptionRespDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductSizeOptionRespDto;
import com.stussy.stussyclone20220930kde.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    @Value("${file.path}") //표현식으로 쓰면 yml등록해둔거 전역으로쓰임
    private String filePath;

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

    @Override
    public List<?> getSizeList(int productId) throws Exception {
        List<ProductSizeOptionRespDto> list = new ArrayList<ProductSizeOptionRespDto>();
        productManagementRepository.getSizeList(productId).forEach(size ->{
            list.add(size.toDto());

        });
        return list;
    }

    @Override
    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.findProductColor(productRegisterDtlReqDto.toEntity()) > 0){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
                    throw new CustomValidationException("Duplicated Error", errorMap);

        }
    }


    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if (productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");

        }
    }
    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
            log.info("pdtId >>> " + productImgReqDto.getPdtId());

            if(productImgReqDto.getFiles() ==null) {
                Map<String, String> errorMap = new HashMap<String, String>();
                errorMap.put("error", "이미지를 선택하지 않았습니다.");
                throw new CustomValidationException("Img Error", errorMap);
            }

            List<ProductImg> productImgs = new ArrayList<ProductImg>();

            productImgReqDto.getFiles().forEach(file -> {
                String originName = file.getOriginalFilename();
                String extension =originName.substring(originName.lastIndexOf("."));
                String saveName = UUID.randomUUID().toString().replaceAll("_", "") +extension;

                Path path = Paths.get(filePath + "product/" + saveName);

                File f = new File(filePath + "product");
                if(!f.exists()) { //파일이 존재하지 않으면
                    f.mkdirs(); //makedirectroy s에붙이는건 하위경로 까지 폴더명 만들어주는거
                }

                    try {
                        Files.write(path, file.getBytes());
                    } catch (IOException e) {
                        throw new CustomInternalServerErrorException(e.getMessage());
                    }

                    productImgs.add(ProductImg.builder()
                                    .pdt_id(productImgReqDto.getPdtId())
                                    .origin_name(originName)
                                    .save_name(saveName)
                                    .build());

            });

            productManagementRepository.saveProductImg(productImgs);

    }


}
