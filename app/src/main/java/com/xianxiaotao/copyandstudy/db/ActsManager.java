package com.xianxiaotao.copyandstudy.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 对数据模型Acts的管理
 * 单例模式
 *
 * Created by xianxiaotao on 17/4/24.
 */
public class ActsManager {

    private static ActsManager instance = new ActsManager();
    private List<Acts> mActs;

    private ActsManager() {
        mActs = new ArrayList<>();
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.ExplosionFieldActivity", "粒子破碎效果", "https://github.com/tyrantgit/ExplosionField", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.CustomLoadingActivity", "常用的加载动画", "frameLoading、customLoading", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.AVLoadingIndicatorViewActivity", "漂亮的加载动画", "https://github.com/81813780/AVLoadingIndicatorView", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.CircularProgressButtonActivity", "挺赞的带进度动效Button", "https://github.com/dmytrodanylyk/circular-progress-button", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.SVProgressHUDActivity", "仿 IOS SVProgressHUD 控件库", "https://github.com/saiwu-bigkoo/Android-SVProgressHUD", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.PathTextViewActivity", "PathEffectTextView写字效果", "支持字符：字母、数字、空格、点号、-", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.TimelyTextViewActivity", "两个不同的数字之间无缝切换效果", "https://github.com/adnan-SM/TimelyTextView", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.HeartLayoutActivity", "HeartLayout", "类似吹泡泡效果", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.BlurImageActivity", "毛玻璃效果", "https://github.com/wingjay/BlurImageView", 0));
        mActs.add(new Acts("com.xianxiaotao.copyandstudy.activity.PercentageBarActivity", "自定义动态柱形图", "created by zhangqi", 0));


        mActs.add(new Acts("com.xxtking.demo.fragment.DynamicFragmentsActivity", "Dynamic Fragments", "Building a Dynamic UI with Fragments for phone and tablet", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 2));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 0));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 2));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 0));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 0));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 0));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 0));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 1));
        mActs.add(new Acts("com.xxtking.demo.geoquiz.QuizActivity", "Geo Quiz", "from the book called \"Android Programming The Big Nerd Ranch Guide\"", 2));
    }

    public static ActsManager getInstance() {
        return instance;
    }

    public List<Acts> getAll() {
        return mActs;
    }

    public List<Acts> getActs(int type) {
        if (type < 0 || type > 2)
            throw new IllegalArgumentException("");

        List<Acts> temp = new ArrayList<>();
        for (Acts obj : mActs) {
            if (obj.getType() == type)
                temp.add(obj);
        }

        return temp;
    }

    public Acts getActs(String className) {
        for (Acts obj : mActs) {
            if (obj.getClassName().equals(className))
                return obj;
        }

        return null;
    }
}
