package com.linda.animatedexpandablelistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by zhoulinda on 16/3/15.
 */
public class SecondActivity extends Activity {
    private EListAdapter mEListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setUpView();
    }
    public final String TAG = "MainActivity";
    ArrayList<Group> groups = new ArrayList<>();
    ArrayList<SelectModel> mSelectModels = new ArrayList<>();
    private void setUpView() {
        final AnimatedExpandableListView mListView = (AnimatedExpandableListView) findViewById(R.id.expandableListView);
        for (int i = 0; i < 2; i++) {
            mSelectModels.add(new SelectModel(-1,new HashSet<Integer>()));
            Group groupItem = new Group(""+i,"地区"+i);
            for (int j = 0; j < 5; j++){
                groupItem.addChildrenItem(new Child(""+i,""+j,groupItem.getTitle()+j));
            }
            groups.add(groupItem);
        }
        mEListAdapter = new EListAdapter(this, groups);

        mListView.setAdapter(mEListAdapter);
        //默认第一组打开
//        mListView.expandGroupWithAnimation(0);

        //点击分组打开或关闭时添加动画
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
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
                mSelectModels.get(groupPosition).set.add(childPosition);
                Toast.makeText(SecondActivity.this,groupPosition+"=="+childPosition,Toast.LENGTH_SHORT).show();
//                Log.e("fengan", "onChildClick:== "+groups.toString() );
                Group group = groups.get(groupPosition);
                Child childItem = group.getChildItem(childPosition);
                childItem.setChecked(!childItem.getChecked());
                int checkCount = 0;
                for (int i = 0; i < group.getChildrenCount(); i++) {
                    if ( group.getChildItem(i).getChecked()==true) {
                        checkCount++;
                    }
                }
                if (checkCount == group.getChildrenCount()) {
                    group.setChecked(true);
                }else{
                    group.setChecked(false);
                }
                mEListAdapter.notifyDataSetChanged();
                Log.e("fengan", "onChildClick:== "+groups.toString() );
                return false;
            }
        });
    }
}
