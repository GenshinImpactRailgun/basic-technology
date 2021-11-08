package com.logsystem.es.springbootdemo.util;

import com.basic.comon.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.HttpStatus;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author railgun
 * 2021/11/7 15:52
 * es 工具类
 */
@Slf4j
public class ElasticsearchUtils {

    /**
     * railgun
     * 2021/11/7 15:59
     * 工具类禁用外部声明
     */
    private ElasticsearchUtils() {
    }

    private static RestHighLevelClient restHighLevelClient;

    /**
     * 2021/11/7 16:03 @railgun rest 字段名称，直接依据字段名称反射注入
     */
    public static final String REST_HIGH_LEVEL_CLIENT_FIELD_NAME = "restHighLevelClient";

    /**
     * 2021/11/7 16:36 @railgun 创建文档的超时时间【如果不指定，默认为 1 分钟】
     */
    private static final String CREATE_DOCUMENT_REQUEST_TIMEOUT = "4s";

    /**
     * 判断索引是否存在
     *
     * @param index 索引名称
     * @return boolean
     * @author railgun
     * 2021/11/7 16:14
     */
    public static boolean existsIndex(String index) {
        try {
            return restHighLevelClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("判断索引（{}）是否存在失败", index);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建索引
     *
     * @param index 索引名称
     * @author railgun
     * 2021/11/7 18:03
     */
    public static void createIndex(String index) {
        if (!existsIndex(index)) {
            try {
                restHighLevelClient.indices().create(new CreateIndexRequest(index), RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文档【兼容新版本，不指定 type 参数】
     *
     * @param index 索引
     * @param id    文档主键
     * @param value 值的内容
     * @return boolean
     * @author railgun
     * 2021/11/7 16:39
     */
    public static <T> boolean createDocument(String index, String id, T value) {
        try {
            // json, XContentType.JSON 固定格式不能改变
            IndexResponse result = restHighLevelClient.index(new IndexRequest(index).id(id).timeout(CREATE_DOCUMENT_REQUEST_TIMEOUT).source(GsonUtil.objectToJson(value), XContentType.JSON), RequestOptions.DEFAULT);
            if (HttpStatus.SC_OK == result.status().getStatus()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文档
     *
     * @param index 索引名称
     * @param id    文档唯一标识
     * @return boolean
     * @author railgun
     * 2021/11/7 18:03
     */
    public static boolean deleteDocument(String index, String id) {
        try {
            DeleteResponse delete = restHighLevelClient.delete(new DeleteRequest(index, id), RequestOptions.DEFAULT);
            if (HttpStatus.SC_OK == delete.status().getStatus()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新文档
     *
     * @param index 索引
     * @param id    文档唯一标识
     * @param value 值
     * @return boolean
     * @author railgun
     * 2021/11/7 18:09
     */
    public static <T> boolean updateDocument(String index, String id, T value) {
        try {
            UpdateResponse update = restHighLevelClient.update(new UpdateRequest(index, id).doc(GsonUtil.objectToJson(value), XContentType.JSON), RequestOptions.DEFAULT);
            if (HttpStatus.SC_OK == update.status().getStatus()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文档
     *
     * @param index 索引
     * @param id    文档唯一索引
     * @param clazz 返回对象的类型
     * @return T
     * @author railgun
     * 2021/11/7 18:23
     */
    public static <T> T getDocument(String index, String id, Class<T> clazz) {
        try {
            GetResponse getResponse = restHighLevelClient.get(new GetRequest(index, id), RequestOptions.DEFAULT);
            return GsonUtil.jsonToObject(GsonUtil.objectToJson(getResponse.getSource()), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 依据 key value 模糊查询
     *
     * @param index 索引
     * @param key   键
     * @param value 值
     * @param clazz 泛型类类型
     * @return java.util.List<T>
     * @author railgun
     * 2021/11/8 0:33
     */
    public static <T> List<T> searchFuzzy(String index, String key, String value, Class<T> clazz) {
        try {
            List<T> list = new ArrayList<>();
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            searchSourceBuilder.query(new BoolQueryBuilder().must(QueryBuilders.matchPhraseQuery(key, value))).sort("timestamp", SortOrder.ASC);
            searchRequest.source(searchSourceBuilder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            if (ArrayUtils.isNotEmpty(hits)) {
                Arrays.asList(hits).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz)));
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
