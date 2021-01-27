package com.dl.code.test;

import com.dl.code.dao.impl.GoodsDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/20 -- 19:09
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        GoodsDaoImpl goodsDao = new GoodsDaoImpl();
        int i = goodsDao.batchDelete(list);
    }
}
