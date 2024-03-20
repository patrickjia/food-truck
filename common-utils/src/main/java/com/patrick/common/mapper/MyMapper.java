package com.patrick.common.mapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;

/**
 * 定制版 MyBatis Mapper 插件接口，如需其他接口参考官方文档自行添加
 *
 * @author
 * @date 2018/05/27
 */
public interface MyMapper<T>
        extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T>, SelectByExampleMapper<T>, SelectCountByExampleMapper<T> {
}
