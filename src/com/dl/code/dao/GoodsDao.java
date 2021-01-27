package com.dl.code.dao;

import com.dl.code.entity.Goods;
import com.dl.code.entity.GoodsType;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 14:50
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface GoodsDao {
    //分页查询商品
    public List<Goods> selectGoodsByPage(int start,int pageSize);
    //查询记录的总数
    public int selectCount();

    //添加商品
    public int addGoods(Goods goods);
    //查询商品类型
    public List<GoodsType> selectGoodsType();

    //更新商品信息
    public int updateGoods(Goods goods);

    //删除商品信息
    public int deleteGoods(int id);

    //批量删除
    public int batchDelete(List<Integer> ids);

    //条件查询
    public List<Goods> selectGoodsByCondition(String condition,int start,int pageSize);

    //查询条件查询的时候的记录总数
    public int selectCountInCondition(String condition);

}
