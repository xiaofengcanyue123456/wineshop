package com.dao.impl;

import com.util.JdbcUtil;
import com.dao.CheckOutDao;
import com.model.CheckOutRoom;
import com.util.DateUtil;
import com.util.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutDaoImpl implements CheckOutDao {
    DbUtils utils=new DbUtils();

    @Override
    public List<CheckOutRoom> queryList(int startPage, int limit, String content) throws SQLException {
        List<CheckOutRoom> list=new ArrayList<CheckOutRoom>();
        String sql="select  room.id as roomId,rinfo.room_id AS roomId1,rinfo.number,info.id,info.id_card,info.tel,info.start_time,info.state,\n" +
                " info.is_pay,type.type_name ,room.out_time,room.remark as remark2 " +
                " from reserve_info info LEFT JOIN room_type type on type.id=info.room_id " +
                " LEFT JOIN room_info rinfo on rinfo.id=info.room_id " +
                " LEFT JOIN cheak_out_room room on room.reserve_id=info.id  " +
                " where info.state in (1,2)";
        StringBuffer sb=new StringBuffer(sql);
        if(content!=null){
            content="%"+content+"%";
            sb.append(" and id_card like '"+content+"'");
        }
        sb.append(" limit "+startPage+","+limit);
        ResultSet rs= JdbcUtil.querySql(sb.toString());
        String a;
        while(rs.next()){
            String sql2="SELECT\n" +
                    "\troomType .type_name\n" +
                    "FROM\n" +
                    "\troom_type roomType \n" +
                    "WHERE\n" +
                    "\troomType.id ="+rs.getInt("roomId1");
            StringBuffer sb2=new StringBuffer(sql2);
            ResultSet rs2= JdbcUtil.querySql(sb2.toString());
            if(rs2.next())
            {a=rs2.getString("type_name");}
            else a="未知类型";
            CheckOutRoom cinfo=new CheckOutRoom();
            cinfo.setNumber(rs.getString("number"));
            cinfo.setId(rs.getInt("id"));
            cinfo.setId_card(rs.getString("id_card"));
            cinfo.setTel(rs.getString("tel"));
            cinfo.setStart_time(rs.getDate("start_time"));
            cinfo.setState(rs.getInt("state"));
            cinfo.setIs_pay(rs.getInt("is_pay"));
            cinfo.setType_name(a);
            cinfo.setOutTime(rs.getDate("out_time"));
            cinfo.setRemark2(rs.getString("remark2"));
            cinfo.setOutId(rs.getInt("roomId"));

            list.add(cinfo);
        }
        return list;
    }

    @Override
    public int getCounts(String content) throws SQLException {
        String sql="select rinfo.number,info.id,info.id_card,info.tel,info.start_time,info.state,\n" +
                " info.is_pay,type.type_name ,room.out_time,room.remark as remark2 " +
                " from reserve_info info LEFT JOIN room_type type on type.id=info.room_id " +
                " LEFT JOIN room_info rinfo on rinfo.id=info.room_id " +
                " LEFT JOIN cheak_out_room room on room.reserve_id=info.id  " +
                " where info.state in (1,2)";
        StringBuffer sb=new StringBuffer(sql);
        if(content!=null){
            content="%"+content+"%";
            sb.append(" and id_card like '"+content+"'");
        }
        int num=utils.getCount(sql);
        return num;
    }

    @Override
    public boolean saveInfo(CheckOutRoom type) {
        String date= DateUtil.dateChangeString(new Date());
        String sql="insert into cheak_out_room (reserve_id,out_time,author,remark)" +
                " values("+type.getReserveId()+",'"+date+"','"+type.getAuthor()+"'," +
                " '"+type.getRemark2()+"')";
        int num= JdbcUtil.insertOrUpdateOrDeleteInfo(sql);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql="delete from cheak_out_room where id="+id;
        int num= JdbcUtil.insertOrUpdateOrDeleteInfo(sql);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public CheckOutRoom queryByID(Integer id) throws SQLException {
//        String sql="select rinfo.number,info.id,info.id_card,info.tel,info.start_time,info.state,\n" +
//                " info.is_pay,type.type_name ,room.out_time,room.remark as remark2 " +
//                " from reserve_info info LEFT JOIN room_type type on type.id=info.room_id " +
//                " LEFT JOIN room_info rinfo on rinfo.id=info.room_id " +
//                " LEFT JOIN cheak_out_room room on room.reserve_id=info.id  " +
//                " where info.state in (1,2) and info.id="+id;
        String sql="select * from cheak_out_room where id="+id;
        ResultSet rs= JdbcUtil.querySql(sql);
        CheckOutRoom cinfo=new CheckOutRoom();
        if(rs.next()){
            String sj=rs.getDate("out_time").toString();
            cinfo.setTime(sj);
            cinfo.setRemark2(rs.getString("remark"));
//            cinfo.setRemark2(rs.getString("remark"));
        }
//        while(rs.next()){
//            cinfo.setNumber(rs.getString("number"));
//            cinfo.setId(rs.getInt("id"));
//            cinfo.setId_card(rs.getString("id_card"));
//            cinfo.setTel(rs.getString("tel"));
//            cinfo.setStart_time(rs.getDate("start_time"));
//            cinfo.setState(rs.getInt("state"));
//            cinfo.setIs_pay(rs.getInt("is_pay"));
//            cinfo.setType_name(rs.getString("type_name"));
//            cinfo.setOutTime(rs.getDate("out_time"));
//            cinfo.setRemark2(rs.getString("remark2"));
//        }
        return cinfo;
    }
}
