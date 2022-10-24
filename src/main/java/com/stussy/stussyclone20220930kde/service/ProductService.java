package com.stussy.stussyclone20220930kde.service;

import com.stussy.stussyclone20220930kde.dto.CollectionListRespDto;

import java.util.Collection;

import java.util.List;

public interface ProductService {
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception;
}