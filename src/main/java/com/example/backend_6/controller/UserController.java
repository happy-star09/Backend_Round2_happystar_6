package com.example.backend_6.controller;

import com.example.backend_6.pojo.User;
import com.example.backend_6.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users") // 地址名字其实不影响程序跑通，但api是约定俗成用来表示后端数据接口的
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(@Valid @RequestBody User user) { // @Valid触发校验
        userService.saveUser(user);
        return "存储成功";
    }

    @GetMapping("/list")
    public List<User> list(@RequestParam(defaultValue = "1") int page, // 设定一个默认值，防止忘带参数导致程序崩溃
                           @RequestParam(defaultValue = "8") int size) { // 这俩换行方便，在一行也行
        return userService.getUsersByPage(page, size);
    }
}
