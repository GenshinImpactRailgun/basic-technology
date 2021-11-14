package com.logsystem.es.springbootdemo.util;

import com.basic.comon.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
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
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
     * 2021/11/14 20:19 @railgun 没有任何字符串
     */
    private static final String NONE_STR = "_none_";

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
     * 删除索引
     *
     * @param index 索引
     * @author railgun
     * 2021/11/14 20:09
     */
    public void deleteIndex(String index) {
        if (existsIndex(index)) {
            try {
                restHighLevelClient.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 依据 id 判断文档是否存在
     *
     * @param index 索引
     * @param id    文档唯一标识
     * @return boolean
     * @author railgun
     * 2021/11/14 20:17
     */
    public boolean existsDocumentById(String index, String id) {
        try {
            return restHighLevelClient.exists(new GetRequest(index, id).fetchSourceContext(new FetchSourceContext(false)).storedFields(NONE_STR), RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建文档
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
            IndexResponse result = restHighLevelClient.index(new IndexRequest(index).id(id).timeout(CREATE_DOCUMENT_REQUEST_TIMEOUT).source(GsonUtil.objectToJson(value), XContentType.JSON), RequestOptions.DEFAULT);
            if (HttpStatus.SC_CREATED == result.status().getStatus()) {
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
            return GsonUtil.jsonToObject(GsonUtil.objectToJson(getResponse.getSourceAsMap()), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量创建文档 list
     *
     * @param index 索引
     * @param list  文档 list 内容
     * @return boolean
     * @author railgun
     * 2021/11/14 20:40
     */
    public boolean bulkCreateDocumentByList(String index, List<?> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            BulkRequest request = new BulkRequest();
            list.forEach(item -> request.add(new IndexRequest(index).source(GsonUtil.objectToJson(item), XContentType.JSON)));
            try {
                BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
                return response.hasFailures();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 根据经纬度查询范围查找 location
     *
     * @param index     索引
     * @param longitude 经度
     * @param latitude  维度
     * @param distance  距离 km
     * @return org.elasticsearch.action.search.SearchResponse
     * @author railgun
     * 2021/11/14 20:42
     */
    public <T> List<T> geoDistanceQuery(String index, double longitude, double latitude, String distance, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        // 拼接条件
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 以某点为中心，搜索指定范围【location 为录入对象内部私有类】
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(latitude, longitude);
        // 查询单位：km
        distanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        queryBuilder.filter(distanceQueryBuilder);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (ArrayUtils.isNotEmpty(hits)) {
                Arrays.asList(hits).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item), clazz)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 高亮结果集
     *
     * @param response           响应
     * @param highlightFieldName 高亮字段名
     * @return java.util.List<T>
     * @author railgun
     * 2021/11/14 21:02
     */
    public <T> List<T> setSearchResponse(SearchResponse response, String highlightFieldName, Class<T> clazz) {
        //解析结果
        List<T> list = new ArrayList<>();
        SearchHit[] hits = response.getHits().getHits();
        if (ArrayUtils.isNotEmpty(hits)) {
            Arrays.asList(hits).forEach(item -> {
                Map<String, HighlightField> highlightFields = item.getHighlightFields();
                HighlightField field = highlightFields.get(highlightFieldName);
                Map<String, Object> sourceAsMap = item.getSourceAsMap();
                if (null != field) {
                    Text[] fragments = field.getFragments();
                    StringBuilder str = new StringBuilder();
                    Arrays.asList(fragments).forEach(str::append);
                    sourceAsMap.put(highlightFieldName, str.toString());
                }
                list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(sourceAsMap), clazz));
            });
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param index          索引
     * @param query          查询条件
     * @param size           大小
     * @param from           从哪来开始
     * @param fields         字段
     * @param sortField      排序字段
     * @param highlightField 高亮字段
     * @param clazz          类泛型
     * @return java.util.List<T>
     * @author railgun
     * 2021/11/14 21:24
     */
    public <T> List<T> searchListData(String index,
                                      SearchSourceBuilder query,
                                      Integer size,
                                      Integer from,
                                      String fields,
                                      String sortField,
                                      String highlightField, Class<T> clazz) throws IOException {
        SearchRequest request = new SearchRequest(index);
        if (StringUtils.isNotEmpty(fields)) {
            // 只查询特定字段。如果需要查询所有字段则不设置该项
            query.fetchSource(new FetchSourceContext(true, fields.split(","), Strings.EMPTY_ARRAY));
        }
        from = from <= 0 ? 0 : from * size;
        // 设置确定结果要从哪个索引开始搜索的from选项，默认为0
        query.from(from);
        query.size(size);
        if (StringUtils.isNotEmpty(sortField)) {
            // 排序字段，注意如果proposal_no是text类型会默认带有keyword性质，需要拼接.keyword
            query.sort(sortField + ".keyword", SortOrder.ASC);
        }
        // 高亮
        HighlightBuilder highlight = new HighlightBuilder();
        highlight.field(highlightField);
        // 关闭多个高亮
        highlight.requireFieldMatch(false);
        highlight.preTags("<span style='color:red'>");
        highlight.postTags("</span>");
        query.highlighter(highlight);
        // 不返回源数据。只有条数之类的数据。
        request.source(query);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        log.error("==" + response.getHits().getTotalHits());
        return 200 == response.status().getStatus() ? setSearchResponse(response, highlightField, clazz) : new ArrayList<>();
    }
}
