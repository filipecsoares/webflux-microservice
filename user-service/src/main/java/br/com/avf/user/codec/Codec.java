package br.com.avf.user.codec;

import br.com.avf.user.entity.User;
import br.com.avf.user.entity.UserTransaction;
import br.com.avf.user.protocol.*;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@UtilityClass
public class Codec {
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public static UserTransactionResponse toResponse(UserTransactionRequest request, UserTransactionStatus status) {
        UserTransactionResponse response = new UserTransactionResponse();
        response.setUserId(request.getUserId());
        response.setAmount(request.getAmount());
        response.setStatus(status);
        return response;
    }

    public static User toEntity(UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public static UserTransaction toEntity(UserTransactionRequest request) {
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setUserId(request.getUserId());
        userTransaction.setAmount(request.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }
}
