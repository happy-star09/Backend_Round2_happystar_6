package com.example.backend_6.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data; // 一个插件工具，加上它过后IDEA会在底层帮忙生成getUsername()等方法
import java.time.LocalDateTime;

@Data // 让下面的 User 类可以自动生成 get/set 方法(好方便不用我敲代码了)
public class User {

    /*
     * 数据库自动生成的递增数字，前端不传不用校验
     * id是独一无二的，哪怕重名用户也不会是同一个id，可用于区分
     * 虽然从上一道题来看，用户名无法重复，但是如果用户改名，id也仍然是原来那个，系统仍然认识它
     */
    private Long id;

    /*
     * LocalDateTime是一个用来记录具体时间的工具
     * 用来记录注册时间
     */
    private LocalDateTime createdAt;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, max = 16, message = "密码长度需为5-16位")
    private String password;
}

