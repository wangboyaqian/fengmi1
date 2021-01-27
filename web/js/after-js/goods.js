$(function (){
    $(".add-button").click(function (){
        window.location.href = "http://localhost:8080/fengmi/goodsServlet?method=getAllGoodsType";
    });

    $(".delete-button").click(function (){
        //定义一个数组长度
        var length = 0;
        //计算数组的长度
        $(".checkInput:checkbox[name=checkInput]:checked").each(function (i){
            length = i+1;
        });
        //创建一个数组
        var array = new Array(length);
        //数组赋值 目的是将选中的id赋值给数组
        $(".checkInput:checkbox[name=checkInput]:checked").each(function (i){
           array[i] = $(this).val();
        });
        //跳转到指定的页面 并将数组作为参数传递到后台
        window.location.href = "http://localhost:8080/fengmi/goodsServlet?method=batchDelete&&array="+array;
    });
})
