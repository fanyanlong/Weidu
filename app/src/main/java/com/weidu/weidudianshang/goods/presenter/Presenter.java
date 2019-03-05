package com.weidu.weidudianshang.goods.presenter;



import com.weidu.weidudianshang.GoodsActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.goods.model.IModel;
import com.weidu.weidudianshang.goods.model.Model;


public class Presenter implements IPresenter {
    GoodsActivity goodsActivity;
    private Model model;

    public Presenter(GoodsActivity goodsActivity) {
        this.goodsActivity=goodsActivity;
        model = new Model();
    }


    @Override
    public void getPre(String commodityId) {
        model.getModel(Api.GOODS_URL+"?commodityId="+commodityId, commodityId, new IModel.SetModel() {
            @Override
            public void getModelData(Object o) {
                goodsActivity.getView(o);
            }
        });
    }

    public void Destroy(){
        if (goodsActivity!=null){
            goodsActivity=null;
            System.gc();
        }
    }
}
