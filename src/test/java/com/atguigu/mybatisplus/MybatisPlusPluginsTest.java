package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.Product;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zgl
 * @create 2022-11-2022/11/20-16:12
 */
@SpringBootTest
    public class MybatisPlusPluginsTest {
    //对使用的组件进行装配
    @Autowired
    private UserMapper userMapper;
    @Autowired  //爆红是因为动态生成的代理实现来，idea匹配不到，要想不报错，就把mapper添加为持久层
    private ProductMapper productMapper;

    @Test
    public void testPage() {
        //先创建一个page对象 他的泛型一定是操作的实体对象
        Page<User> page = new Page<>(1, 5);
        //selectPage 这个方法就是查询分页数据  null是对所有数据的查询
        userMapper.selectPage(page, null);
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());

    }

    @Test
    public void testPageVo() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPageVo(page, 20);
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());

    }

    @Test
    public void testConcurrentUpdate() {
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());
        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());
        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
        if (result2 == 0) {
            //操作失败，请重试
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }

        //最后的结果
        Product p3 = productMapper.selectById(1L);
        //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());
    }

}
