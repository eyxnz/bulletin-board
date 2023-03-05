package com.board.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {
    // 게시판 페이지
    @GetMapping
    public String articles(ModelMap map) {
        // TODO: Service 를 개발 후 데이터를 넣어주기로 한다.
        map.addAttribute("articles", List.of());

        return "articles/index";
    }

    // 게시글 상세 페이지
    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        // TODO: Service 를 개발 후 데이터를 넣어주기로 한다.
        map.addAttribute("article", "article");
        map.addAttribute("articleComments", List.of());

        return "articles/detail";
    }
}
