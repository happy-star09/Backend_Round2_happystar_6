package com.example.backend_6.service;

import com.example.backend_6.mapper.UserMapper;
import com.example.backend_6.pojo.User;
import com.example.backend_6.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 组件扫描机制，此注解是@Component的派生注解，将当前类标记为业务逻辑层的Bean
public class UserService {
    @Autowired // 依赖注入
    private UserMapper userMapper;

    // 存数据
    public void saveUser(User user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在"); // 插入前先检查是否已存在
        }
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        userMapper.insert(user); // 入库前用 Md5Util 加密，避免明文存储
    }

    // 分页查询
    public List<User> getUsersByPage(int page, int size) {
        if (page < 1) page = 1;
        if (size < 1) size = 8;

        int offset = (page - 1) * size;
        return userMapper.selectByPage(offset, size);
    }
}
