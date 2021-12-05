package br.com.avf.user.protocol;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponse {
    private Integer id;
    private String name;
    private Integer balance;
}
