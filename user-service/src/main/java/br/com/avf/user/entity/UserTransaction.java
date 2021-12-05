package br.com.avf.user.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class UserTransaction {
    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
