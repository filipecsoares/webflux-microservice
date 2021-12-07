package br.com.avf.product.codec;

import br.com.avf.product.entity.Product;
import br.com.avf.product.protocol.ProductRequest;
import br.com.avf.product.protocol.ProductResponse;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class Codec {

    public static ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(product, response);
        return response;
    }

    public static Product toEntity(ProductRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        return product;
    }
}
