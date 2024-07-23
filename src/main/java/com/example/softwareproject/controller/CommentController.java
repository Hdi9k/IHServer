package com.example.softwareproject.controller;

import com.example.softwareproject.pojo.Comment;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weibo/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public Result getComment(Long weiboId){
        return Result.success(commentService.getComment(weiboId));
    }
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment, HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return commentService.addComment(comment.getWeiboId(), userId, comment.getText());
    }
}
