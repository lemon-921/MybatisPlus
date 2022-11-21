package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author zgl
 * @create 2022-11-2022/11/20-18:07
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标识为乐观锁的版本号字段
    private Integer version;
}
