package br.com.avf.product.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class ProductResponse {
    @Id
    private String id;
    private String description;
    private BigDecimal price;

    public ProductResponse(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }
}
