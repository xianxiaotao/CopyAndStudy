package com.xianxiaotao.copyandstudy.db;

/**
 * 数据模型：保存各个Demo的入口Activity
 *
 * Created by xianxiaotao on 17/4/24.
 */
public class Acts {

    public static final int TYPE_TEST = 0;
    public static final int TYPE_GOOGLE = 1;
    public static final int TYPE_SHOW = 2;

    private String className;   // 类名
    private String title;       // 标题
    private String desc;        // 描述
    private int type;           // 类型

    public Acts(String className, String title, String desc, int type) {
        this.className = className;
        this.title = title;
        this.desc = desc;
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }
}
