package br.com.avf.user.protocol;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class UserTransactionResponse {
    private Long userId;
    private BigDecimal amount;
    private UserTransactionStatus status;
}
