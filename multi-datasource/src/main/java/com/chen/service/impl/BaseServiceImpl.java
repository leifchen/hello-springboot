package com.chen.service.impl;

import com.chen.dao.BaseDao;
import com.chen.model.Student;
import com.chen.model.User;
import com.chen.service.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * BaseServiceImpl
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Slf4j
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 获取对应Mapper
     * @return
     */
    protected abstract BaseDao<T> getMapper();

    @Override
    public boolean insert(T entity) {
        boolean flag = false;
        try {
            getMapper().insert(entity);
            flag = true;
        } catch (Exception e) {
            log.error("新增{}失败!原因是:", getClassName(entity), e);
        }
        return flag;
    }


    @Override
    public boolean update(T entity) {
        boolean flag = false;
        try {
            getMapper().update(entity);
            flag = true;
        } catch (Exception e) {
            log.error("更新{}失败!原因是:", getClassName(entity), e);
        }
        return flag;
    }

    @Override
    public boolean deleteByPrimaryKey(int id) {
        boolean flag = false;
        try {
            getMapper().deleteByPrimaryKey(id);
            flag = true;
        } catch (Exception e) {
            log.error("id:" + id + "删除失败!原因是:", e);
        }
        return flag;
    }

    @Override
    public boolean delete(T entity) {
        boolean flag = false;
        try {
            getMapper().delete(entity);
            flag = true;
        } catch (Exception e) {
            log.error("删除{}失败!原因是:", getClassName(entity), e);
        }
        return flag;
    }

    @Override
    public T findByPrimaryKey(int id) {
        T obj = null;
        try {
            obj = getMapper().findByPrimaryKey(id);
        } catch (Exception e) {
            log.error("id:" + id + "查询失败!原因是:", e);
        }
        return obj;
    }

    @Override
    public T findByEntity(T entity) {
        T obj = null;
        try {
            obj = getMapper().findByEntity(entity);
        } catch (Exception e) {
            log.error("查询{}失败!原因是:", getClassName(entity), e);
        }
        return obj;
    }

    @Override
    public List<T> findByListEntity(T entity) {
        List<T> list = null;
        try {
            Page<?> page = PageMethod.startPage(1, 2);
            log.debug("{} 设置第一页数据!", getClassName(entity));
            list = getMapper().findByListEntity(entity);
            log.debug("总共有:{}条数据, 实际返回:{}条数据!", page.getTotal(), list.size());
        } catch (Exception e) {
            log.error("查询" + getClassName(entity) + "失败!原因是:", e);
        }
        return list;
    }

    @Override
    public List<T> findAll() {
        List<T> list = null;
        try {
            list = getMapper().findAll();
        } catch (Exception e) {
            log.error("查询失败!原因是:", e);
        }
        return list;
    }

    @Override
    public Object findByObject(Object obj) {
        Object result = null;
        try {
            result = getMapper().findByObject(obj);
        } catch (Exception e) {
            log.error("查询" + obj + "失败!原因是:", e);
        }
        return result;
    }

    private String getClassName(T t) {
        String str = "";
        if (t instanceof User) {
            str = "User";
        } else if (t instanceof Student) {
            str = "Studnet";
        }
        return str;
    }
}
