package com.linda.animatedexpandablelistviewdemo;

import java.util.HashSet;

/**
 * 项目名称:expandListview
 * 创建人:zhouxiaohua
 * 创建时间:2017/8/22 22:53
 */

public class SelectModel {
    public int groupId;
    public HashSet<Integer> set  = new HashSet<>();

    @Override
    public String toString() {
        return "SelectModel{" +
                "groupId=" + groupId +
                ", set=" + set +
                '}';
    }

    public SelectModel(int groupId, HashSet<Integer> set) {
        this.groupId = groupId;
        this.set = set;
    }
}
