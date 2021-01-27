package com.dl.code.service;

import com.dl.code.entity.Goods;
import com.dl.code.entity.GoodsType;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 14:51
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface GoodsService {
    public List<Goods> getGoodsByPage(int pageNo,int pageSize);
    public int getDataCount();
    public int addGoods(Goods goods);
    public List<GoodsType> getAllGoodsType();
    public int updateGoods(Goods goods);
    public int deleteGoods(int id);
    public int batchDelete(List<Integer> list);
    public List<Goods> getAllGoodsByCondition(String condition,int pageNo,int pageSize);
    public int getDataCountInCondition(String condition);
}
