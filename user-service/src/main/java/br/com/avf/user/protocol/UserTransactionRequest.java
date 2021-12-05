package br.com.avf.user.protocol;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTransactionRequest {
    private Integer userId;
    private Integer amount;
}
