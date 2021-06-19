package com.frame.springmvc.ssmbuild.service;

import com.frame.springmvc.ssmbuild.pojo.Books;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/19 10:22
 * PS:
 **/
public interface BooksService {
    /**
     * PS: 增加
     *
     * @param books 书籍对象
     * @Author: railgun
     * @return: int
     * 2021/6/19 10:14
     **/
    int addBook(Books books);

    /**
     * PS: 根据id删除一个Book
     *
     * @param id 主键
     * @Author: railgun
     * @return: int
     * 2021/6/19 10:14
     **/
    int deleteBookById(int id);

    /**
     * PS: 更新Book
     *
     * @param books 书籍对象
     * @Author: railgun
     * @return: int
     * 2021/6/19 10:14
     **/
    int updateBook(Books books);

    /**
     * PS: 根据id查询,返回一个Book
     *
     * @param id 主键
     * @Author: railgun
     * @return: com.frame.springmvc.ssmbuild.pojo.Books
     * 2021/6/19 10:14
     **/
    Books queryBookById(int id);

    /**
     * PS: 查询全部Book,返回list集合
     *
     * @Author: railgun
     * @return: List<Books>
     * 2021/6/19 10:15
     **/
    List<Books> queryAllBook();
}
