package com.dl.code.dao.impl;

import com.dl.code.dao.GoodsDao;
import com.dl.code.entity.Goods;
import com.dl.code.entity.GoodsType;
import com.dl.code.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 14:51
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class GoodsDaoImpl implements GoodsDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<Goods> selectGoodsByPage(int start, int pageSize){
//        String sql = "select t_goods.id,t_goods.goodsName,t_goods.price,t_goods.score,t_goods.deployDate,t_goods.imgPath,t_goods.comment,t_goodstype.typename from t_goods,t_goodstype where t_goods.typeId = t_goodstype.id limit ?,?";
        String sql = "select * from t_goods,t_goodstype where t_goods.typeId = t_goodstype.id limit ?,?";
        Object[] params = {start,pageSize};
        List<Goods> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                Goods goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setGoodsName(resultSet.getString("goodsName"));
                goods.setPrice(resultSet.getDouble("price"));
                goods.setScore(resultSet.getInt("score"));
                goods.setDeployDate(resultSet.getDate("deployDate"));
                goods.setImgPath(resultSet.getString("imgPath"));
                goods.setComment(resultSet.getString("comment"));
                goods.setTypeName(resultSet.getString("typename"));
                goods.setTypeId(resultSet.getInt("typeId"));
                list.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from t_goods";
        Object[] params = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                count = resultSet.getInt("count(1)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int addGoods(Goods goods) {
        String sql = "insert into t_goods(goodsName, price, imgPath, typeId, deployDate, comment) values(?,?,?,?,?,?)";
        Object[] params = {goods.getGoodsName(),goods.getPrice(),goods.getImgPath(),goods.getTypeId(),goods.getDeployDate(),goods.getComment()};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }

    @Override
    public List<GoodsType> selectGoodsType() {
        String sql = "select * from t_goodstype";
        Object[] params = {};
        List<GoodsType> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                GoodsType goodsType = new GoodsType();
                goodsType.setId(resultSet.getInt("id"));
                goodsType.setTypename(resultSet.getString("typename"));
                list.add(goodsType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateGoods(Goods goods) {
        String sql = "update t_goods set goodsName = ?,price = ?," +
                "deployDate = ?,imgPath = ?,comment = ?,typeId = ? where id = ?";
        Object[] params = {goods.getGoodsName(),goods.getPrice(),goods.getDeployDate(),goods.getImgPath(),goods.getComment(),goods.getTypeId(),goods.getId()};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }

    @Override
    public int deleteGoods(int id) {
        String sql = "delete from t_goods where id = ?";
        Object[] params = {id};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }

    @Override
    public int batchDelete(List<Integer> ids) {
        //定义一个StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        //将list集合中的值 加入到 StringBuffer  中
        for (int i = 0; i < ids.size(); i++) {
            if(i==0){
                //第一位前面不能含有 ','
                stringBuffer.append(ids.get(i));
            }else {
                //后面的以 ',' 为分割
                stringBuffer.append(","+ids.get(i));
            }
        }
        //拼接sql语句
        String sql = "delete from t_goods where id in ("+stringBuffer+")";
        Object[] params = {};
        //执行sql语句
        int i = baseDao.setUpdate(sql, params);
        //返回受影响的行数
        return i;
    }

    @Override
    public List<Goods> selectGoodsByCondition(String condition, int start, int pageSize) {
        /*
        经过数据库的测试，下面是正确的
        正确的sql语句：SELECT * FROM t_goods INNER JOIN t_goodstype on t_goods.typeId = t_goodstype.id WHERE 1=1 and t_goods.goodsName LIKE '%小米%' AND t_goods.deployDate = '2019-04-29'
        condition = "and t_goods.goodsName LIKE '%小米%' AND t_goods.deployDate = '2019-04-29'"
        */

        String sql = "select * from t_goods inner join t_goodstype on t_goods.typeId" +
                " = t_goodstype.id where 1=1 "+ condition + " limit ?,?";
        System.out.println(sql);
        Object[] params = {start,pageSize};
        List<Goods> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                Goods goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setGoodsName(resultSet.getString("goodsName"));
                goods.setPrice(resultSet.getDouble("price"));
                goods.setScore(resultSet.getInt("score"));
                goods.setDeployDate(resultSet.getDate("deployDate"));
                goods.setImgPath(resultSet.getString("imgPath"));
                goods.setComment(resultSet.getString("comment"));
                goods.setTypeName(resultSet.getString("typename"));
                goods.setTypeId(resultSet.getInt("typeId"));
                list.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCountInCondition(String condition) {
        String sql = "select count(1) from t_goods inner join t_goodstype on t_goods.typeId" +
                " = t_goodstype.id where 1=1 "+ condition;
        Object[] params = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                count = resultSet.getInt("count(1)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
