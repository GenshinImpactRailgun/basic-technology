package com.logsystem.es.springbootdemo.service.inter;

/**
 * @Author: railgun
 * 2021/10/19 23:12
 * PS: 数据操作接口
 */
public interface IEsService {

    /**
     * 创建索引库
     */
    void createIndexRequest(String index);

    /**
     * 删除索引库
     */
    void deleteIndexRequest(String index);

    /**
     * 更新索引文档
     */
    void updateRequest(String index, String id, Object object);

    /**
     * 新增索引文档
     */
    void insertRequest(String index, String id, Object object);

    /**
     * 删除索引文档
     */
    void deleteRequest(String index, String id);

}
