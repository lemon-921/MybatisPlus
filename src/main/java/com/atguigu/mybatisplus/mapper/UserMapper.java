package com.atguigu.mybatisplus.mapper;
import com.atguigu.mybatisplus.enums.SexEnum;

import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zgl
 * @create 2022-11-2022/11/2-18:03
 */
//标识为持久层插件
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为map集合
      * @param id
     * @return
     */
     Map<String,Object> selectMapById(Long id);

    /**
     * 通过年龄查询用户信息并分页
     * @param page
     * @param age
     * @return
     */
     Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);

    int updateAgeAndSex(@Param("age") int age, @Param("sex") SexEnum sex);
}
