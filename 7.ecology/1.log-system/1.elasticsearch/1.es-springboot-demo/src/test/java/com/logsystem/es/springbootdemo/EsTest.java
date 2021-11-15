package com.logsystem.es.springbootdemo;

import com.basic.comon.util.GsonUtil;
import com.basic.comon.util.string.UUIDUtils;
import com.logsystem.es.springbootdemo.pojo.dto.Lol;
import com.logsystem.es.springbootdemo.pojo.dto.UserDto;
import com.logsystem.es.springbootdemo.service.impl.LolServiceImpl;
import com.logsystem.es.springbootdemo.util.ElasticsearchUtils;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: railgun
 * 2021/10/19 23:21
 * PS: 单元测试
 */
@SpringBootTest
public class EsTest {

    public static final String INDEX_NAME = "lol";

    @Autowired
    private LolServiceImpl lolService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void test() {
        railgunTest();
    }

    @SneakyThrows
    private void railgunTest() {
        String index = "railgun-index";
        // 初始化建立索引
        ElasticsearchUtils.createIndex(index);

        // List<UserDto> userDtoList = initGetUser();

        // 删除历史文档
        // deleteHistoryDocument(index, userDtoList);

        // 创建新文档
        // createNewDocument(index, userDtoList);

        test4();
    }

    /**
     * railgun
     * 2021/11/14 12:03
     * 精确查询
     */
    @SneakyThrows
    private void test1() {
        // 单个匹配termQuery
        // 不分词查询 参数1： 字段名，参数2：字段查询值，因为不分词，所以汉字只能查询一个字，英语是一个单词.
        // TODO 英文录入的时候，分词出来之后，如果使用不在单词库的英文拼写。会导致英文单词的检索检索不到内容。
        QueryBuilder queryBuilder1 = QueryBuilders.termQuery("username", "mei");

        // 多个匹配
        // 不分词查询，参数1： 字段名，参数2：多个字段查询值,因为不分词，所以汉字只能查询一个字，英语是一个单词.
        QueryBuilder queryBuilder2 = QueryBuilders.termsQuery("username", "Sakura", "Vollerei");

        //分词查询，采用默认的分词器
        // TODO 使用这种方式检索的数据不会有不在单词字典中，而导致检索不到的问题
        QueryBuilder queryBuilder3 = QueryBuilders.matchQuery("username", "Sakura");


        //分词查询，采用默认的分词器
        QueryBuilder queryBuilder4 = QueryBuilders.multiMatchQuery("Sakura", "username", "mei", "kiana");

        //匹配所有文件，相当于就没有设置查询条件
        QueryBuilder queryBuilder5 = QueryBuilders.matchAllQuery();

    }

    /**
     * railgun
     * 2021/11/14 12:03
     * 模糊查询
     */
    @SneakyThrows
    private void test2() {
        //模糊查询常见的5个方法如下
        //1.常用的字符串查询
        //左右模糊
        // 内容不区分大小写，但是需要完全匹配，才能检索到
        QueryStringQueryBuilder queryBuilder1 = QueryBuilders.queryStringQuery("me").field("username");




        //2.常用的用于推荐相似内容的查询
        //如果不指定filedName，则默认全部，常用在相似内容的推荐上
        // TODO 【不同版本对应的 addLikeText 方法不一样，得更正一下】
        // QueryBuilders.moreLikeThisQuery(new String[] {"fieldName"}).addLikeText("pipeidhua");



        //3.前缀查询  如果字段没分词，就匹配整个字段前缀
        // TODO 单词首字母大写会找不到内容
        // TODO 匹配的是单词的前缀，而不是整个文本的前缀【不必要匹配整个单词 也能找到】
        PrefixQueryBuilder queryBuilder2 = QueryBuilders.prefixQuery("username", "kiana");




        //4.fuzzy query:分词模糊查询，通过增加fuzziness模糊属性来查询,如能够匹配hotelName为tel前或后加一个字母的文档，fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
        // 仅限变换一步就能查询到的数据
        FuzzyQueryBuilder queryBuilder3 = QueryBuilders.fuzzyQuery("username", "kian").fuzziness(Fuzziness.ONE);



        //5.wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
        // 前面是 fieldname 后面是带匹配字符的字符串
        WildcardQueryBuilder queryBuilder4 = QueryBuilders.wildcardQuery("username", "ki*");
        // 任意匹配占位符
        WildcardQueryBuilder queryBuilder5 = QueryBuilders.wildcardQuery("username", "ki?n?");


    }

    /**
     * railgun
     * 2021/11/14 12:04
     * 范围查询
     */
    @SneakyThrows
    private void test3() {
        // 闭区间查询
        // 从小到大
        QueryBuilder queryBuilder0 = QueryBuilders.rangeQuery("username").from("kiana").to("bronya");


        // 开区间查询
        // 默认是true，也就是包含
        QueryBuilder queryBuilder1 = QueryBuilders.rangeQuery("username").from("kiana").to("Bronya").includeUpper(false).includeLower(false);


        // 大于
        QueryBuilder queryBuilder2 = QueryBuilders.rangeQuery("username").gt("kiana");


        // 大于等于
        QueryBuilder queryBuilder3 = QueryBuilders.rangeQuery("username").gte("bronya");


        // 小于
        QueryBuilder queryBuilder4 = QueryBuilders.rangeQuery("fieldName").lt("fieldValue");


        // 小于等于
        QueryBuilder queryBuilder5 = QueryBuilders.rangeQuery("fieldName").lte("fieldValue");



    }

    /**
     * railgun
     * 2021/11/14 12:04
     * 组合查询 & 多条件查询 & 布尔查询
     */
    @SneakyThrows
    private void test4() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();


        //文档必须完全匹配条件，相当于and
        QueryBuilders.boolQuery().must();



        //文档必须不匹配条件，相当于not
        QueryBuilders.boolQuery().mustNot();



        //至少满足一个条件，这个文档就符合should，相当于or
        QueryBuilders.boolQuery().should();


        // 模糊匹配 bronya 并且 在 a - s 区间内
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "ki*");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("username").from("kiana").to("Bronya").includeUpper(false).includeLower(false);
        queryBuilder.must(wildcardQueryBuilder);
        queryBuilder.should(rangeQueryBuilder);



        // 搜索源生成器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        // 搜索请求
        SearchRequest searchRequest = new SearchRequest().indices("railgun-index").source(searchSourceBuilder);
        // 搜索响应
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    }

    /**
     * railgun
     * 2021/11/13 17:00
     * 滚动查询依据 scrollId 继续往下查询
     */
    private <T> List<T> searchScroll(String index, String key, String value, Class<T> clazz, int page, int size, String scrollId) {
        List<T> list = new ArrayList<>();
        try {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueSeconds(30));
            SearchResponse response = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = response.getScrollId();
            System.out.println("scrollId：" + scrollId);
            if (ArrayUtils.isNotEmpty(response.getHits().getHits())) {
                Arrays.asList(response.getHits().getHits()).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * railgun
     * 2021/11/13 16:48
     * 滚动查询
     */
    private <T> List<T> searchScroll(String index, String key, String value, Class<T> clazz, int page, int size) {
        List<T> list = new ArrayList<>();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
        searchSourceBuilder.size(size);

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            String scrollId = response.getScrollId();
            System.out.println("【scrollId：" + scrollId + "】");
            if (ArrayUtils.isNotEmpty(response.getHits().getHits())) {
                Arrays.asList(response.getHits().getHits()).forEach(item -> {
                    list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz));
                });
            }

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueSeconds(30));
            response = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = response.getScrollId();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * railgun
     * 2021/11/13 16:06
     * 依据字段名称、字段内容全匹配查询
     */
    private <T> List<T> search(String index, String key, String value, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        // 直接构造模糊查询构造器
        QueryBuilder matchQueryBuilderOther = QueryBuilders.matchQuery(key, value).fuzziness(Fuzziness.ZERO);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilderOther);

        SearchRequest searchRequestOther = new SearchRequest();
        searchRequestOther.indices(index);
        searchRequestOther.source(searchSourceBuilder);

        try {
            SearchResponse response = restHighLevelClient.search(searchRequestOther, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            if (ArrayUtils.isNotEmpty(hits)) {
                Arrays.asList(hits).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * railgun
     * 2021/11/13 16:06
     * 依据字段名称、字段内容模糊匹配查询最大两次变更次数
     */
    private <T> List<T> searchFuzzy(String index, String key, String value, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        // 直接构造模糊查询构造器
        QueryBuilder matchQueryBuilderOther = QueryBuilders.matchQuery(key, value).fuzziness(Fuzziness.TWO);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilderOther);

        SearchRequest searchRequestOther = new SearchRequest();
        searchRequestOther.indices(index);
        searchRequestOther.source(searchSourceBuilder);

        try {
            SearchResponse response = restHighLevelClient.search(searchRequestOther, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            if (ArrayUtils.isNotEmpty(hits)) {
                Arrays.asList(hits).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * railgun
     * 2021/11/13 16:06
     * 依据字段名称、字段内容模糊匹配查询最大两次变更次数【命中部分使用高亮标出】
     */
    private <T> List<T> searchFuzzyHighlight(String index, String key, String value, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        // 构造高亮查询构造器
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签前缀
        highlightBuilder.preTags("<font color='red'>");
        //设置标签后缀
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field(key);
        // highlightTitle.highlighterType("unified");
        // highlightTitle.highlighterType("plain");
        // highlightTitle.highlighterType("fvh");
        highlightBuilder.field(highlightTitle);

        // 模糊匹配构造器
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(key, value).fuzziness(Fuzziness.TWO);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(queryBuilder);

        SearchRequest searchRequestOther = new SearchRequest();
        searchRequestOther.indices(index);
        searchRequestOther.source(searchSourceBuilder);

        try {
            SearchResponse response = restHighLevelClient.search(searchRequestOther, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            if (ArrayUtils.isNotEmpty(hits)) {
                Arrays.asList(hits).forEach(item -> list.add(GsonUtil.jsonToObject(GsonUtil.objectToJson(item.getSourceAsMap()), clazz)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * railgun
     * 2021/11/13 16:03
     * 创建新文档
     */
    private void createNewDocument(String index, List<UserDto> userDtoList) {
        if (CollectionUtils.isNotEmpty(userDtoList)) {
            userDtoList.forEach(item -> ElasticsearchUtils.createDocument(index, item.getId(), item));
        }
    }

    /**
     * railgun
     * 2021/11/13 15:54
     * 删除历史文档
     */
    private void deleteHistoryDocument(String index, List<UserDto> userDtoList) {
        if (CollectionUtils.isNotEmpty(userDtoList)) {
            userDtoList.forEach(item -> ElasticsearchUtils.deleteDocument(index, item.getId()));
        }
    }

    public List<UserDto> initGetUser() {
        List<String> username = Arrays.asList("Kiana Kaslana", "Bronya Zaychik", "Yae Sakura", "Raiden Mei", "Seele Vollerei");
        List<String> alias = Arrays.asList("琪亚娜·卡斯兰娜", "布洛妮娅·扎伊切克", "八重樱", "雷电芽衣", "希儿·芙乐艾");
        List<UserDto> userDtoList = new ArrayList<>();
        // 组装 1000 个对象
        for (int i = 0; i < 1000; i++) {
            UserDto userDto = new UserDto();
            userDto.setId(UUIDUtils.generate());
            userDto.setUsername(username.get(i % 5));
            userDto.setAlias(alias.get(i % 5));
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Test
    public void createIndex() {
        lolService.createIndexRequest(INDEX_NAME);
    }

    @Test
    public void deleteIndex() {
        lolService.deleteIndexRequest(INDEX_NAME);
    }


    @Test
    public void insertTest() {
        List<Lol> list = new ArrayList<>();
        list.add(Lol.builder().id(1L).name("德玛西亚之力").realName("盖伦").desc("作为一名自豪而高贵的勇士，盖伦将自己当做无畏先锋中的普通一员参与战斗。他既受到同袍手足的爱戴，也受到敌人对手的尊敬--尤其作为尊贵的冕卫家族的子嗣，他被委以重任，守卫德玛西亚的疆土和理想。他身披抵御魔法的重甲，手持阔剑，时刻准备着用正义的钢铁风暴在战场上正面迎战一切操纵魔法的狂人。").build());
        list.add(Lol.builder().id(2L).name("疾风剑豪").realName("亚索(快乐风男)").desc("亚索是一个百折不屈的艾欧尼亚人，也是一名身手敏捷的御风剑客。这位生性自负的年轻人，被误认为杀害长老的凶手--由于无法证明自己的清白，他出于自卫而杀死了自己的哥哥。虽然长老死亡的真相已然大白，亚索还是无法原谅自己的所作所为。他在家园的土地上流浪，只有疾风指引着他的剑刃。").build());
        list.add(Lol.builder().id(3L).name("魂锁典狱长").realName("锤石").desc("暴虐又狡猾的锤石是一个来自暗影岛的亡灵，野心勃勃、不知疲倦。他曾经是无数奥秘的看守，寻找着超越生死的力量，而现在他则使用自己独创的钻心痛苦缓慢地折磨并击溃其他人，以此作为自己存在下去的手段。被他迫害的人需要承受远超死亡的痛苦，因为锤石会让他们的灵魂也饱尝剧痛，将他们的灵魂囚禁在自己的灯笼中，经受永世的折磨。").build());
        list.add(Lol.builder().id(4L).name("圣枪游侠").realName("卢锡安").desc("曾担光明哨兵的卢锡安是一位冷酷的死灵猎人。他用一对圣物手枪无情地追猎并灭绝不死亡灵。为亡妻复仇的意念吞噬了他，让他无止无休。除非消灭锤石，那个手握她灵魂的恶鬼。冷酷而且决绝的卢锡安不允许任何东西挡住自己的复仇之路。如果有什么人或者什么东西愚蠢到敢挑衅他的原则，就必将接受压倒性的神圣枪火狂轰滥炸。").build());
        list.add(Lol.builder().id(5L).name("法外狂徒格雷福斯").realName("格雷福斯").desc("马尔科姆.格雷福斯是有名的佣兵、赌徒和窃贼，凡是他到过的城邦或帝国，都在通缉悬赏他的人头。虽然他脾气暴躁，但却非常讲究黑道的义气，他的双管散弹枪“命运”就经常用来纠正背信弃义之事。几年前他和老搭档崔斯特.菲特冰释前嫌，如今二人一同在比尔吉沃特的地下黑道纷争中再次如鱼得水。").build());
        list.add(Lol.builder().id(6L).name("光辉女郎").realName("拉克丝").desc("拉克珊娜.冕卫出身自德玛西亚，一个将魔法视为禁忌的封闭国度。只要一提起魔法，人们总是带着恐惧和怀疑。所以拥有折光之力的她，在童年的成长过程中始终担心被人发现进而遭到放逐，一直强迫自己隐瞒力量，以此保住家族的贵族地位。虽然如此，拉克丝的乐观和顽强让她学会拥抱自己独特的天赋，现在的她正在秘密地运用自己的能力为祖国效力。").build());
        list.add(Lol.builder().id(7L).name("发条魔灵").realName("奥莉安娜").desc("奥莉安娜曾是一个拥有血肉之躯的好奇女孩，而现在则是全身上下部由发条和齿轮构成的科技奇观。祖安下层地区的一次事故间接导致了她身染重病，日渐衰竭的身体必须替换为精密的人造器官，一个接一个，直到全身上下再也没有人类的肉体。她给自己制作了一枚奇妙的黄铜球体，既是伙伴，也是保镖。如今她已经可以自由地探索壮观的皮尔特沃夫，以及更遥远的地方。").build());

        lolService.insertBach(INDEX_NAME, list);
    }

    @Test
    public void updateTest() {
        Lol lol = Lol.builder().id(6L).name("殇之木乃伊").realName("阿木木").desc("或许阿木木是英雄联盟世界里最古老的保卫者英雄之一，他对加入联盟前的生活仍一无所知。阿木木唯一记得的是自己在Shuima沙漠的一座金字塔内独自醒来。他全身缠着裹尸布，感受不到自己的心跳。此外，他感到一股强大而莫名的悲伤；他知道他失去了亲人，虽然他已不记得他们是谁。阿木木跪下来，在绷带内哭泣。不论做什么，似乎他都无法阻止眼泪或悲伤。最后他站起来在这个世界上游荡，并进入了联盟").build();
        lolService.updateRequest(INDEX_NAME, lol.getId().toString(), lol);
    }

    @Test
    public void deleteTest() {
        lolService.deleteRequest(INDEX_NAME, "1");
    }

    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<Lol> personList = lolService.searchList(INDEX_NAME);
        System.out.println(personList);
    }

}
