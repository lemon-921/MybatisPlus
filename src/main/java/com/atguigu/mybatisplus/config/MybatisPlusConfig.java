package com.atguigu.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zgl
 * @create 2022-11-2022/11/20-16:06
 */
@Configuration
//扫描mapper接口所在的包
@MapperScan("com.atguigu.mybatisplus.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //配置MybatisPlus中的插件
        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
        //PaginationInnerInterceptor(配置数据库类型)
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁内拦截器 OptimisticLockerInnerInterceptor
        //添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
