package com.rodrigo.blog.controller;

import com.rodrigo.blog.model.Post;
import com.rodrigo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("posts")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ModelAndView getPost() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = blogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }
}
