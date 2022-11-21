package com.atguigu.mybatisplus.pojo;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

/**
 * @author zgl
 * @create 2022-11-2022/11/2-17:58
 */
/*//可以用一个注解代替
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode*/
@Data
@NoArgsConstructor
@AllArgsConstructor
//设置实体类中所对应的表名，还可以配置全局配置
//@TableName("t_user")
public class User {
    /**
     *  Field 'uid' doesn't have a default value  字段“uid”没有默认值
     *  @TableId 将这个属性对应的字段来作为我们当前的主键
     * Unknown column 'id' in 'field list' “字段列表”中的列“id”未知
     * @TableId注解的value属性用于指定主键的字段
     * @TableId注解的type属性用于主键类型是都是雪花算法策略生成的ID还是自己数据库自增的ID
     * 该类型请确保数据库设置了 ID自增
     *
     * mybatis 会默认用id为主键，如果属性名不是id，使用TableId（）来指定
     */
//    @TableId(value = "uid",type = IdType.AUTO)
    @TableId(value = "uid")
    private Long id;
    //表格字段
    @TableField("user_name")
    private String name;

    private int age;

    private SexEnum sex;

    private String email;

    @TableLogic
    private Integer isDeleted;


}
