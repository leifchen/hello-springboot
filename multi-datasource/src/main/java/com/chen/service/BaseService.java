package com.chen.service;

import java.util.List;

/**
 * BaseService
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
public interface BaseService<T> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    boolean insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    boolean update(T entity);

    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    boolean deleteByPrimaryKey(int id);

    /**
     * 删除数据
     * @param entity
     * @return
     */
    boolean delete(T entity);

    /**
     * 根据id查询单个记录
     * @param id
     * @return
     */
    T findByPrimaryKey(int id);

    /**
     * 根据对象查询单个记录
     * @param entity
     * @return
     */
    T findByEntity(T entity);

    /**
     * 根据对象查询多条记录
     * @param entity
     * @return
     */
    List<T> findByListEntity(T entity);

    /**
     * 查询所有记录
     * @return
     */
    List<T> findAll();

    /**
     * 根据对象查询信息
     * @param obj
     * @return
     */
    Object findByObject(Object obj);
}
