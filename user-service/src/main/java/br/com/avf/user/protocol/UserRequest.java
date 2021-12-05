package br.com.avf.user.protocol;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class UserRequest {
    private Long id;
    private String name;
    private BigDecimal balance;
}
