package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zgl
 * @create 2022-11-2022/11/20-21:30
 */
@SpringBootTest
public class MybatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        //为什么报错 是因为这个字段需要int类型的，而我们传入的是string类型的 就是把sexName传过去了
        //而我们需要的是sex这个类型
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result" + result);
    }
}
