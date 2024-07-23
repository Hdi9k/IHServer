package com.example.softwareproject.util;

import com.example.softwareproject.pojo.UserInterest;
import com.example.softwareproject.pojo.WeiboInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeiboInfoSorter {
    public static void sortWeiboInfosByUserInterest(List<WeiboInfo> weiboInfos, UserInterest userInterest) {
        // 创建兴趣值与类别的映射
        Map<Integer, Integer> interestMap = new HashMap<>();
        interestMap.put(1, userInterest.getNews());
        interestMap.put(2, userInterest.getEntertainment());
        interestMap.put(3, userInterest.getGame());
        interestMap.put(4, userInterest.getEconomics());
        interestMap.put(5, userInterest.getDigital());
        interestMap.put(6, userInterest.getDaily());

        // 按兴趣值对WeiboInfo列表进行排序
        weiboInfos.sort((weibo1, weibo2) -> {
            int category1 = weibo1.getCategory();
            int category2 = weibo2.getCategory();
            int interest1 = interestMap.getOrDefault(category1, 0);
            int interest2 = interestMap.getOrDefault(category2, 0);
            return Integer.compare(interest2, interest1); // 值越大的排在前面
        });
    }
}
