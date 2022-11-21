package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.minidev.json.writer.UpdaterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zgl
 * @create 2022-11-2022/11/3-23:17
 */
@SpringBootTest
public class MybatisPlusWapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test01() {
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为nuLL的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted
        // FROM t_user WHERE is_deleted=0
        // AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        //==> Parameters: %a%(String), 20(Integer), 30(Integer)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test02() {
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        //SELECT uid AS id,user_name AS name,age,email,is_deleted
        //FROM t_user WHERE is_deleted=0
        //ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test03() {
        //删除email为空的用户
        //DELETE FROM t_user WHERE (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //条件构造器也可以构建删除语句的条件
        int result = userMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND age > ? OR
        //email IS NULL)
        queryWrapper
                .like("user_name", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE
        // (username LIKE ? AND (age > ? OR email IS NULL))
        //lambda表达式内的逻辑优先运算
        //在SQL语句中优先执行小括号里面的内容
        queryWrapper
                .like("username", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }


    @Test
    public void test09() {
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isBlank(username)) {
            //isBlank判断某个字符串是否不为空，不为null，不为空白符
            queryWrapper.like("user_name", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::print);
    }

    @Test
    public void test10() {
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::print);
    }

    @Test
    public void test11() {
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isBlank(username),User::getName,username)
                .ge(ageBegin != null,User::getAge,ageBegin)
                .le(ageEnd != null,User::getAge,ageEnd);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::print);

    }
}
