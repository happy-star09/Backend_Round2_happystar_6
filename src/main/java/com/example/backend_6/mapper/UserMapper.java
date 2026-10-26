package com.example.backend_6.mapper;

import com.example.backend_6.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *当启动时扫描到它的时候，MyBatis 框架会自动生成一个实现类
 * 当程序运行调用 UserMapper.insert() 方法时，底层会被 MyBatis 拦截
 *  MyBatis 会帮忙写一堆繁琐的代码实现功能
 */
@Mapper
public interface UserMapper { // interface说明这是一个接口不是类

    /*
     * @Insert 是 Java 注解
     * 写上这个是为了告诉 MyBatis，防止 SQL 字符串被当成普通文本而导致 Java 不去执行它
     * 加上 @Insert，就可以告诉 MyBatis 将这个方法发给数据库去执行
     * 大写的INSERT INTO这一串是 SQL 语言，由 MySQL 数据库读取并执行插入操作
     * VALUES 后面表示具体的值，#{}是预编译占位符
     */
    @Insert("INSERT INTO `user`(username, password) VALUES(#{username}, #{password})")
    void insert(User user);

    // 用户名查询，查重
    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    /* @Select表明是查询方法
     * *是把user表里面的所有列都选出来，不加的话就要手写了
     * LIMIT #{offset}, #{size}是固定分页语法
     * 跳过前offset条，展示后面size条，size是一页的个数
     */
    @Select("SELECT * FROM `user` LIMIT #{offset}, #{size}")

    /*
     * List<User>是装着User的列表
     * @Param()是给数字贴标签让MyBatis能找到对应数字
     * 如果没有，数字就是默认的arg1,arg2
     */
    List<User> selectByPage(@Param("offset") int offset, @Param("size") int size);
}

