package com.example.softwareproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiboInfo {
    //微博ID
    private Long weiboId;
    //⽤⼾Id
    private Long userId;
    //⽤⼾名称
    private String username;
    //⽤⼾头像
    private String avatar;
    //微博标题
    private String title;
    //视频地址
    private String videoUrl;
    //视频封⾯
    private String poster;
    //图⽚地址
    private List<String> images;
    // 新增字段，用于存储JSON格式的images
    private String imagesJson;
    //点赞数
    private Integer likeCount;
    //是否已点过赞
    private Boolean likeFlag;
    //评论数
    private Integer commentCount;
    //类别 0无 1新闻 2娱乐 3游戏 4财经 5数码 6生活 7广告
    private Integer category;
    //创建时间
    private String createTime;

    @Override
    public String toString() {
        return "WeiboInfo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", poster='" + poster + '\'' +
                ", images=" + images +
                ", category=" + category +
                '}';
    }
}
