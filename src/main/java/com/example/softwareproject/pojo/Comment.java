package com.example.softwareproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    //微博ID
    private Long weiboId;
    //⽤⼾Id
    private Long userId;
    //⽤⼾名称
    private String username;
    //⽤⼾头像
    private String avatar;
    //用户评论
    private String text;
}
