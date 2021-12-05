package br.com.avf.user.protocol;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTransactionResponse {
    private Integer userId;
    private Integer amount;
    private UserTransactionStatus status;
}
