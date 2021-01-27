package com.dl.code.dao;

import com.dl.code.entity.GoodsType;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:14
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface GoodsTypeDao {
    //查询全部商品类型 -- 分页
    public List<GoodsType> selectAll(int start,int pageSize);

    //查询总记录数
    public int selectCount();

    //添加商品类型
    public int addGoodsType(GoodsType goodsType);

    public int updateGoodsType(GoodsType goodsType);
}
