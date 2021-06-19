package com.frame.springmvc.ssmbuild.service;

import com.frame.springmvc.ssmbuild.dao.BooksMapper;
import com.frame.springmvc.ssmbuild.pojo.Books;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/19 10:23
 * PS:
 **/
@Service
public class BooksServiceImpl implements BooksService {

    private final BooksMapper booksMapper;

    public BooksServiceImpl(BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
    }

    @Override
    public int addBook(Books books) {
        return booksMapper.addBook(books);
    }

    @Override
    public int deleteBookById(int id) {
        return booksMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        return booksMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return new Books(booksMapper.queryBookById(id));
    }

    @Override
    public List<Books> queryAllBook() {
        List<Map<String, Object>> listMap = booksMapper.queryAllBook();
        List<Books> list = new ArrayList<>();
        listMap.forEach(item -> {
            list.add(new Books(item));
        });
        return list;
    }
}
