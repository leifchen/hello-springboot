package com.chen.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * BaseDao
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
public interface BaseDao<T> {

    /**
     * 单条新增插入数据
     * @param entity
     * @return
     * @throws SQLException
     */
    void insert(T entity) throws SQLException;

    /**
     * 批量新增据插入数据
     * @param entityList
     * @return
     * @throws SQLException
     */
    int insertBatch(List<T> entityList) throws SQLException;

    /**
     * 更新数据
     * @param entity
     * @return
     * @throws SQLException
     */
    void update(T entity) throws SQLException;

    /**
     * 根据ID删除数据
     * @param id
     * @throws SQLException
     */
    void deleteByPrimaryKey(int id) throws SQLException;

    /**
     * 删除数据
     * @param entity
     * @return
     * @throws SQLException
     */
    void delete(T entity) throws SQLException;

    /**
     * 根据id查询单个记录
     * @param id
     * @return
     * @throws SQLException
     */
    T findByPrimaryKey(int id);

    /**
     * 根据对象查询单个记录
     * @param entity
     * @return
     * @throws SQLException
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
     * @throws SQLException
     */
    Object findByObject(Object obj);
}
