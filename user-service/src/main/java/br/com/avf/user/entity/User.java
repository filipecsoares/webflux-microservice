package br.com.avf.user.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@ToString
@Table("users")
public class User {
    @Id
    private Long id;
    private String name;
    private BigDecimal balance;
}
