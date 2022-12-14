package com.stussy.stussyclone20220930kde.repository;

import com.stussy.stussyclone20220930kde.domain.CollectionsProduct;
import com.stussy.stussyclone20220930kde.domain.PaymentProduct;
import com.stussy.stussyclone20220930kde.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper

public interface ProductRepository {
    public List<CollectionsProduct> getProductList(Map<String, Object> map) throws Exception;

    public Product getProduct(int pdt_id) throws Exception;

    public PaymentProduct getPaymentProduct(int pdtDtlId)  throws Exception;


}
