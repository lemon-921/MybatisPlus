package com.atguigu.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author zgl
 * @create 2022-11-2022/11/20-21:26
 */
@Getter
public enum SexEnum {
    //枚举是以逗号分隔的
    MALE(1,"男"),
    FEMALE(2,"女");

    @EnumValue  //将注解所标识的属性的值存储到数据库中
    // 只设置这个注解还是不行，还在在配置文件扫描通用枚举  type-enums-package
    private Integer sex;
    private String sexName;
    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

}
