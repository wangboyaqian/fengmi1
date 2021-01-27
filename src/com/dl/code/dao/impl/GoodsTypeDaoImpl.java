package com.dl.code.dao.impl;

import com.dl.code.dao.GoodsTypeDao;
import com.dl.code.entity.GoodsType;
import com.dl.code.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:14
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class GoodsTypeDaoImpl implements GoodsTypeDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<GoodsType> selectAll(int start ,int pageSize) {
        String sql = "select * from t_goodstype limit ?,?";
        Object[] params = {start,pageSize};
        List<GoodsType> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                GoodsType goodsType = new GoodsType();
                goodsType.setId(resultSet.getInt("id"));
                goodsType.setTypename(resultSet.getString("typename"));
                goodsType.setLevel(resultSet.getInt("level"));
                goodsType.setLevel(resultSet.getInt("pid"));
                list.add(goodsType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCount(){
        String sql = "select * from t_goodstype";
        Object[] objects = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, objects);
            resultSet.last();
            count = resultSet.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int addGoodsType(GoodsType goodsType) {
        String sql = "INSERT INTO t_goodstype(typename) VALUES (?)";
        Object[] params = {goodsType.getTypename()};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }

    @Override
    public int updateGoodsType(GoodsType goodsType) {
        String sql = "update t_goodstype set typename = ? where id = ?";
        Object[] params = {goodsType.getTypename(),goodsType.getId()};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }
}
