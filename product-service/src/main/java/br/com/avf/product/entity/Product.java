package br.com.avf.product.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@ToString
public class Product {
    @Id
    private String id;
    private String description;
    private BigDecimal price;
}
