package com.stussy.stussyclone20220930kde.api.admin;

import com.stussy.stussyclone20220930kde.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930kde.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220930kde.dto.CMRespDto;
import com.stussy.stussyclone20220930kde.dto.admin.ProductRegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ProductAdminApi {

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Validated @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult){
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", null));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList(){
        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully", null));

    }
}
