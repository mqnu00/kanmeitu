package com.lzh.kanmeitu.bean;


public class PicPackage {

    private String name;
    private String preview;
    private String url;

    public PicPackage() {
    }

    public PicPackage(String name, String preview, String url) {
        this.name = name;
        this.preview = preview;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

