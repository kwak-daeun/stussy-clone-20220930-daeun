package com.stussy.stussyclone20220930kde.repository.admin;

import com.stussy.stussyclone20220930kde.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;
import java.util.List;

@Mapper
public interface ProductManagementRepository {
    public List<ProductCategory> getCategoryList() throws Exception;
}