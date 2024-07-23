package com.example.softwareproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserInterest {
    //新闻
    private Integer news;
    //娱乐
    private Integer entertainment;
    //游戏
    private Integer game;
    //财经
    private Integer economics;
    //数码
    private Integer digital;
    //生活
    private Integer daily;

    public UserInterest() {
        this.news = 0;
        this.entertainment = 0;
        this.game = 0;
        this.economics = 0;
        this.digital = 0;
        this.daily = 0;
    }
}
