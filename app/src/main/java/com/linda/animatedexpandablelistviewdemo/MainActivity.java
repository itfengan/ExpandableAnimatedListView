package com.linda.animatedexpandablelistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoulinda on 16/3/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
    }
    public final String TAG = "MainActivity";
    private List<GroupItem> parentList = new ArrayList<>();


    private void setUpView() {
        final AnimatedExpandableListView mListView = (AnimatedExpandableListView) findViewById(R.id.expandableListView);

        for (int i = 0; i < 10; i++) {
            GroupItem groupItem = new GroupItem();
            groupItem.title = "Parent " + i;

            for (int j = 0; j < 5; j++){
                groupItem.childList.add("parent"+i+"child"+j);
            }
            parentList.add(groupItem);
        }

        mListView.setAdapter(new AnimatedListAdapter(this,parentList));
//        mListView.expandGroupWithAnimation(0);

        //默认第一组打开
//        mListView.expandGroupWithAnimation(0);

        //点击分组打开或关闭时添加动画
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e(TAG,"********");
                ImageView imageView = (ImageView) v.findViewById(R.id.iv_group);
                if (mListView.isGroupExpanded(groupPosition)) {
                    imageView.setVisibility(View.GONE);
                }else{
                    imageView.setVisibility(View.VISIBLE);
                }
                if (mListView.isGroupExpanded(groupPosition)){
                    mListView.collapseGroupWithAnimation(groupPosition);
                }else{
                    mListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,groupPosition+"=="+childPosition,Toast.LENGTH_SHORT).show();
                ImageView imageView = (ImageView) v.findViewById(R.id.iv_child);
                if (imageView.getVisibility() == View.GONE) {
                    imageView.setVisibility(View.VISIBLE);
                }else{
                    imageView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }
}
