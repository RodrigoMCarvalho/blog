package com.rodrigo.blog.controller;

import com.rodrigo.blog.model.Post;
import com.rodrigo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("posts")
    public ModelAndView getPost() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = blogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @GetMapping(value = "posts/{id}")
    public ModelAndView getPostDetails(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = blogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @GetMapping("newpost")
    public String getNewPost() {
        return "postForm";
    }

    @PostMapping("newpost")
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            attr.addFlashAttribute("mensagem","Verifique os campos obrigatórios.");
            return "redirect:/blog/newpost";
        }
        post.setData(LocalDate.now());
        blogService.save(post);
        return "redirect:/blog/posts";
    }
}
