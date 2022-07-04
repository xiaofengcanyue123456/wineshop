package com.service;

import com.model.RoomType;
import com.util.PageBean;

import java.sql.SQLException;

public interface TypeInfoService {
    /**
     * 分页查询
     */
    PageBean getPage(String page,String limit,String content) throws SQLException;

    /**
     * 增加类型
     */
    boolean saveInfo(RoomType type);

    /**
     * 删除
     */
    boolean deleteById(Integer id);

    /**
     * 根据id查询对象
     */
    RoomType queryByID(Integer id) throws SQLException;

    /**
     * 修改提交操作
     */
    boolean updateInfo(RoomType type);
}
