package com.lzh.kanmeitu.bean;

import java.util.List;
import java.util.StringJoiner;

public class SearchResult {

    private List<PicPackage> picPackageList;

    private int count;
    private int totalPage;

    public SearchResult() {
    }

    public SearchResult(List<PicPackage> picPackageList, int count, int totalPage) {
        this.picPackageList = picPackageList;
        this.count = count;
        this.totalPage = totalPage;
    }

    public List<PicPackage> getPicPackageList() {
        return picPackageList;
    }

    public void setPicPackageList(List<PicPackage> picPackageList) {
        this.picPackageList = picPackageList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SearchResult.class.getSimpleName() + "[", "]")
                .add("picPackageList=" + picPackageList)
                .add("count=" + count)
                .add("totalPage=" + totalPage)
                .toString();
    }
}
