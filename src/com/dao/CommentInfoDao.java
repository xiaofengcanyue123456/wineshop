package com.dao;

import com.model.CommentInfo;

import java.sql.SQLException;
import java.util.List;

public interface CommentInfoDao {

    List<CommentInfo> queryList(int startPage, int limit, String content) throws SQLException;

    /**
     * 获取总的记录数据
     */
    int getCounts(String content) throws SQLException;



    /**
     * 删除
     */
    boolean deleteById(Integer id);


    /**
     * 根据房间id查询评论信息
     */
    List<CommentInfo> queryListByRoomID(Integer roomId) throws SQLException;

    /**
     * 前端评论提交
     */
    boolean saveInfo(CommentInfo info);
}
