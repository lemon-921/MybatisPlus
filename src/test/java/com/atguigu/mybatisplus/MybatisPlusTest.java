package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zgl
 * @create 2022-11-2022/11/2-18:10
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //通过条件构造器查询一个List集合，若没有条件，则可以设置nuLL为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
//        User user = new User(null, "张三", 23, "zhangsan@atguigu.com",0);
//        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
//        int result = userMapper.insert(user);
//        System.out.println("受影响行数："+result);
//        //1587807106517422082
//        System.out.println("id自动获取："+user.getId());

    }

    @Test
    public void testDeleteById(){
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
/*        int result = userMapper.deleteById(1587807106517422082L);
        System.out.println("受影响行数："+result);*/


        //根据map集合中所设置的条件删除记录
        //DELETE FROM user WHERE name = ? AND age = ?
/*        Map<String, Object> map = new HashMap<>();
        map.put("age", 23);
        map.put("name", "张三");
        int result = userMapper.deleteByMap(map);
        System.out.println("受影响行数："+result);*/

        //通过多个id批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        //id是long类型的
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testUpdateById(){
//        User user = new User(4L, "admin", 22, null,0);
//        //UPDATE user SET name=?, age=? WHERE id=?
//        int result = userMapper.updateById(user);
//        System.out.println("受影响行数："+result);
    }

    @Test
    public void testSelectById(){
        //根据id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
      /*  User user = userMapper.selectById(4L);
        System.out.println(user);*/

        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
