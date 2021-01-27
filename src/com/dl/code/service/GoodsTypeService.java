package com.dl.code.service;

import com.dl.code.entity.GoodsType;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:21
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface GoodsTypeService {
    public List<GoodsType> getAll(int pageNo,int pageSize);
    public int getDataCount();
    public int addGoodsType(GoodsType goodsType);
    public int updateGoodsType(GoodsType goodsType);
}
