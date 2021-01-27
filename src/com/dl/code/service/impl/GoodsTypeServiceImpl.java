package com.dl.code.service.impl;

import com.dl.code.dao.GoodsTypeDao;
import com.dl.code.dao.impl.GoodsTypeDaoImpl;
import com.dl.code.entity.GoodsType;
import com.dl.code.service.GoodsTypeService;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:21
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {
    private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> getAll(int pageNo,int pageSize) {
        int start = (pageNo-1)*pageSize;
        List<GoodsType> list = goodsTypeDao.selectAll(start,pageSize);
        return list;
    }

    @Override
    public int getDataCount() {
        int dataCount = goodsTypeDao.selectCount();
        return dataCount;
    }

    @Override
    public int addGoodsType(GoodsType goodsType) {
        int i = goodsTypeDao.addGoodsType(goodsType);
        return i;
    }

    @Override
    public int updateGoodsType(GoodsType goodsType) {
        int i = goodsTypeDao.updateGoodsType(goodsType);
        return i;
    }
}
