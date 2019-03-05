package com.weidu.weidudianshang.search.presenter;

import com.weidu.weidudianshang.SearchActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.search.model.HomeSearchModel;
import com.weidu.weidudianshang.search.model.IHomeSearchModel;

public class HomeSearchPresenter implements IHomeSearchPresenter{
    SearchActivity searchActivity;
    private final HomeSearchModel homeSearchModel;

    public HomeSearchPresenter(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
        homeSearchModel = new HomeSearchModel();
    }

    @Override
    public void getModelData(String keyword, String page, String count) {
        homeSearchModel.setModelData(Api.SEARCH_URL, keyword, page, count, new IHomeSearchModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                searchActivity.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }
}
