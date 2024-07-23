package com.example.softwareproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiboPage {
    private List<WeiboInfo>records;
    private Integer total;
    private Integer size;
    private Integer current;
    private Integer pages;
}
