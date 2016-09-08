package com.wmz.newwidgets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmz.newwidgets.adapter.MyRecyclerViewAdapter;
import com.wmz.newwidgets.adapter.MyStaggeredViewAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyStaggeredViewAdapter myStaggeredViewAdapter;

    private static final int VERTICAL_LIST = 0;
    private static final int HORIZONTAL_LIST = 1;
    private static final int VERTICAL_GRID = 2;
    private static final int HORIZONTAL_GRID = 3;
    private static final int STAGGERED_GRID = 4;

    private static final int SPAN_COUNT = 2;
    private int flag = 0;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefreshlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        flag = getArguments().getInt("flag");
        configRecyclerView();

        //刷新式，指示器旋转后变化的颜色
        swipeRefreshLayout.setColorSchemeColors(R.color.main_blue_light,R.color.main_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void configRecyclerView() {
        switch(flag){
            case VERTICAL_LIST:
                layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_LIST:
                layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                break;
            case VERTICAL_GRID:
                layoutManager = new GridLayoutManager(getActivity(),SPAN_COUNT,GridLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_GRID:
                layoutManager = new GridLayoutManager(getActivity(),SPAN_COUNT,GridLayoutManager.HORIZONTAL,false);
                break;
            case STAGGERED_GRID:
                layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        }

        if(flag!=STAGGERED_GRID){
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
            recyclerView.setAdapter(myRecyclerViewAdapter);
        }else{
            myStaggeredViewAdapter = new MyStaggeredViewAdapter(getActivity());
            recyclerView.setAdapter(myStaggeredViewAdapter);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        int temp =(int)(Math.random()*10);
        if(flag!=STAGGERED_GRID){
        }
    }
}
