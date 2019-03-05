package com.weidu.weidudianshang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weidu.weidudianshang.adapter.SearchAdapter;
import com.weidu.weidudianshang.bean.SearchBean;
import com.weidu.weidudianshang.search.presenter.HomeSearchPresenter;
import com.weidu.weidudianshang.search.view.IHomeSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements IHomeSearchView {

    @BindView(R.id.mBack)
    Button mBack;
    @BindView(R.id.mSearch_content)
    EditText mSearchContent;
    @BindView(R.id.mSearch_two)
    TextView mSearchTwo;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.noSearch)
    LinearLayout noSearch;
    private HomeSearchPresenter homeSearchPresenter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        homeSearchPresenter = new HomeSearchPresenter(this);
        Intent intent = getIntent();
        String names = intent.getStringExtra("names");
        //String panduan = intent.getStringExtra("panduan");
        if (names.equals("")){
            Toast.makeText(SearchActivity.this, "不能为空..", Toast.LENGTH_SHORT).show();
        }else {
            homeSearchPresenter.getModelData(names, "1", "7");
        }
        mSearchTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mSearchContent.getText().toString();
                if (name.length() != 0) {
                    homeSearchPresenter.getModelData(name, "1", "7");
                } else {
                    Toast.makeText(SearchActivity.this, "不能为空..", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getViewData(Object o) {
        SearchBean searchBean = (SearchBean) o;
        List<SearchBean.ResultBean> result = searchBean.getResult();
        SearchAdapter searchAdapter = new SearchAdapter(this, searchBean);
        mRecyclerView.setAdapter(searchAdapter);
        if (result.isEmpty()){
            noSearch.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else{
            noSearch.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

    }
}
