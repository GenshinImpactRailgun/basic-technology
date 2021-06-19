package com.frame.springmvc.ssmbuild.controller;

import com.frame.springmvc.ssmbuild.pojo.Books;
import com.frame.springmvc.ssmbuild.service.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/19 11:04
 * PS: 书籍控制层
 **/
@Controller
@RequestMapping(value = "books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    /**
     * railgun
     * 2021/6/19 11:19
     * PS: 获取所有书籍
     **/
    @GetMapping(value = "all-books")
    public String list(Model model) {
        List<Books> list = booksService.queryAllBook();
        model.addAttribute("list", list);
        return "all-books";
    }

    /**
     * railgun
     * 2021/6/19 12:57
     * PS: 添加书籍
     **/
    @GetMapping(value = "add-books")
    public String addBooksGet() {
        return "add-books";
    }

    @ResponseBody
    @PostMapping(value = "add-books")
    public Map<String, String> addBooksPost(Books books) {
        booksService.addBook(books);
        Map<String, String> result = new HashMap<>(4);
        result.put("code", "0");
        result.put("msg", "操作成功");
        return result;
    }

    /**
     * railgun
     * 2021/6/19 13:02
     * PS: 更新书籍
     **/
    @GetMapping(value = "update-books")
    public String updateBooks(Model model, int id) {
        Books books = booksService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book", books);
        return "update-books";
    }

    @ResponseBody
    @PostMapping(value = "update-books")
    public Map<String, String> updateBooksPost(Books books) {
        booksService.updateBook(books);
        Map<String, String> result = new HashMap<>(4);
        result.put("code", "0");
        result.put("msg", "操作成功");
        return result;
    }

    /**
     * railgun
     * 2021/6/19 13:03
     * PS: 删除书籍
     **/
    @GetMapping(value = "del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        booksService.deleteBookById(id);
        return "all-books";
    }

}
