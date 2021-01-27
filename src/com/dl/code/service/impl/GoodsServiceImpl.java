package com.dl.code.service.impl;

import com.dl.code.dao.GoodsDao;
import com.dl.code.dao.impl.GoodsDaoImpl;
import com.dl.code.entity.Goods;
import com.dl.code.entity.GoodsType;
import com.dl.code.service.GoodsService;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 14:51
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Goods> getGoodsByPage(int pageNo, int pageSize) {
        int start = (pageNo-1)*pageSize;
        List<Goods> goodsList = goodsDao.selectGoodsByPage(start, pageSize);
        return goodsList;
    }

    @Override
    public int getDataCount() {
        int dataCount = goodsDao.selectCount();
        return dataCount;
    }

    @Override
    public int addGoods(Goods goods) {
        int i = goodsDao.addGoods(goods);
        return i;
    }

    @Override
    public List<GoodsType> getAllGoodsType() {
        List<GoodsType> goodsTypeList = goodsDao.selectGoodsType();
        return goodsTypeList;
    }

    @Override
    public int updateGoods(Goods goods) {
        int i = goodsDao.updateGoods(goods);
        return i;
    }

    @Override
    public int deleteGoods(int id) {
        int i = goodsDao.deleteGoods(id);
        return i;
    }

    @Override
    public int batchDelete(List<Integer> list) {
        int i = goodsDao.batchDelete(list);
        return i;
    }

    @Override
    public List<Goods> getAllGoodsByCondition(String condition, int pageNo, int pageSize) {
        int start = (pageNo-1)*pageSize;
        List<Goods> list = goodsDao.selectGoodsByCondition(condition, start, pageSize);
        return list;
    }

    @Override
    public int getDataCountInCondition(String condition) {
        int dataCount = goodsDao.selectCountInCondition(condition);
        return dataCount;
    }

}
