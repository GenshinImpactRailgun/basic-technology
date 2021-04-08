package com.java.Collection;

import com.basic.comon.util.GsonUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: railgun
 * 2021/4/5 14:13
 * PS:集合
 */
public class CollectionDemo {

    /**
     * railgun
     * 2021/4/5 14:15
     * PS:java 常用集合
     */
    @Test
    public void test1() {
        // 画出 java 常用集合架构图
        System.out.println("Collection：List、Set、Queue");
        System.out.println("List：ArrayList、LinkedList、Vector");
        System.out.println("Set：HashSet、TreeSet");
        System.out.println("Queue：Deque");
        System.out.println("上述的里面会有复杂的 Abstract 在里面");
        System.out.println("Map：HashMap、Hashtable、TreeMap、WeakHashMap");
        System.out.println("上述会夹杂复杂的 Abstract 在里面");
    }

    /**
     * railgun
     * 2021/4/5 14:15
     * PS:Collection 和 Collections 区别
     */
    @Test
    public void test2() {
        System.out.println("Collection 是 java 集合接口，提供了对集合对象进行基本操作的通用接口方法。Collection 的意义是为各种具体的集合实现提供最大化的统一操作方式");
        System.out.println("Collections 是集合工具类，提供了一系列操作对象的静态方法，例如：排序、搜索、修改等操作");
    }

    /**
     * railgun
     * 2021/4/5 21:41
     * PS:List、Set、Map 区别
     */
    @Test
    public void test3() {
        System.out.println("List 有序、可重复。");
        System.out.println("Set 无序、不可重复。");
        System.out.println("List、Set 是属于 Collection 那一分支的，和 Map 是不同的分支。");
        System.out.println("Map 元素不可重复。");
    }

    /**
     * railgun
     * 2021/4/5 21:42
     * PS:HashMap、Hashtable 区别
     */
    @Test
    public void test4() {
        System.out.println("HashMap 去掉了 Hashtable 的 contains 方法，但是加上了 containsKey 和 containsValue 方法。");
        System.out.println("HashMap 非线程安全，Hashtable 线程安全。");
        System.out.println("HashMap 允许空键空值，Hashtable 不允许空键空值");
        System.out.println("Hashtable 都是加的 synchronized 锁，运行效率很低");
        Map hashMap = new HashMap();
        hashMap.put(null, null);
        Map hashtable = new Hashtable<>();
        // 如果放入 null 值，编译通过，但是执行报错
        hashtable.put("railgun", "railgun");
    }

    /**
     * railgun
     * 2021/4/5 21:53
     * PS:如何决定使用 HashMap 还是 TreeMap
     */
    @Test
    public void test5() {
        System.out.println("HashMap：适合在 Map 中插入、删除、和定位元素");
        System.out.println("TreeMap：适合以自然顺序，或者是自定义顺序去遍历键");
        System.out.println("HashMap 会比 TreeMap 快一点，哈希表和树的数据结构使然，哈希表的速度会比树结构快，因为哈希值定位快。多使用 HashMap，在有需要排序的时候使用 TreeMap");
        System.out.println("HashMap 非线程安全，TreeMap 非线程安全");
        System.out.println("HashMap 无序，TreeMap 有序");
    }

    /**
     * railgun
     * 2021/4/5 22:12
     * PS:简述 TreeMap
     */
    @Test
    public void test6() {
        System.out.println("是有顺序的");
        System.out.println("通过比较器Comparator和Comparable，实现排序");
        System.out.println("通过红黑树数据结构将定位时间复杂度缩减到 O(logN)");
    }

    /**
     * railgun
     * 2021/4/5 22:57
     * PS:简介 HashMap
     */
    @Test
    public void test7() {
        System.out.println("Map 集合接口的一种具体实现。根据键的 hashCode 确定存储位置，访问速度快。非线程安全的，使用 Collections.synchronizedMap 可以实现线程安全，或者使用 ConcurrentHashMap 实现线程安全。");
        System.out.println("数据结构：数组 + 链表 + 红黑树。");
        System.out.println("通过阈值和负载因子去控制扩容逻辑。");
        System.out.println("确定哈希桶数组数组索引位置：取 key 的 hashCode 值、高位运算、取模运算。");
        System.out.println("put 操作：索引位置为空新增、索引位置有值添加链表节点（超过8改成红黑树）、插入之后的长度大于阈值进行扩容");
        System.out.println("扩容操作：扩容大小为两倍扩容一次、依据负载因子计算出阈值");
        System.out.println("线程安全性，何时会发生死循环？：");
        System.out.println("JDK1.8 与 JDK1.7 性能对比：分散的 hash 值，性能优化在15%左右，不分散的 hash 值，优化程度很高，O(N) 和 O(logN) 的区别");
        Map hashMap = new HashMap(10, 0.75f);
        hashMap = Collections.synchronizedMap(hashMap);
        hashMap.put(1, "railgun");
        GsonUtil.objectSoutJson(hashMap);
    }


    @Test
    public void test() {
        String msg = "[{\"tableName\":\"gy_gdxm\",\"fieldName\":\"gy_id\",\"fieldValue\":\"260858\",\"whePrimaryKey\":1,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"xzq_dm\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"kfq_dm\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"bh\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"dz_ba_bh\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"xm_mc\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"tzzt_xz\",\"fieldValue\":\"159\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"hy_fl\",\"fieldValue\":\"K72\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"td_jb\",\"fieldValue\":\"dj1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"zd_bh\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"td_zl\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"gd_zmj\",\"fieldValue\":\"11\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"gy_mj\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"pc_mj\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"xz_mj\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"jt_mj\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"wlyd_mj\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"helpreq\",\"fieldValue\":\"0\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"civic_area\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"td_yt\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"cr_nx\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"JD_SJ\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"je\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"gdzc_tze\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"tz_qd\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"kftz_ze\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"zt_jz_xz\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"fs_jz_xz\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_jz_md\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_jz_md\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_rjl\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_rjl\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_lhl\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_lhl\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_jz_gd\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_jz_gd\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"qt_td_yq\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"DG_SJ\",\"fieldValue\":\"2021-04-14 00:00:00\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"JG_SJ\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"kfjs_qx\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"QD_RQ\",\"fieldValue\":\"2021-04-08 00:00:00\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"pz_jg\",\"fieldValue\":\"苏州市高新区人民政府\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"pz_wh\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"PZ_RQ\",\"fieldValue\":\"2021-03-29 00:00:00\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"BG_DG_SJ\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"GY_GDXM\",\"fieldName\":\"BG_JG_SJ\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":1,\"dateFormat\":\"yyyy-mm-dd hh24:mi:ss\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"sdcy_lb\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"is_yh_gy\",\"fieldValue\":\"0\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"kfly_bz\",\"fieldValue\":\"0\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"wkfly_yy\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"qy_ss_lx\",\"fieldValue\":\"0\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_rjl_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_rjl_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_jz_md_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_jz_md_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_lhl_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_lhl_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"min_jz_xg_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"max_jz_xg_tag\",\"fieldValue\":\"≤\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"proid\",\"fieldValue\":\"260858\",\"whePrimaryKey\":0,\"wheProcessInsId\":1,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"land_user\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"kftzqd\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"cj_price\",\"fieldValue\":\"1\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"remise_income\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"provider_type\",\"fieldValue\":\"2\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"tj_yt\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"zzyd_lx\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"xxcy_ydlx\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"lybzj\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"yd_gf\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"tyxx_dm\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"},{\"tableName\":\"gy_gdxm\",\"fieldName\":\"djje\",\"fieldValue\":\"\",\"whePrimaryKey\":0,\"wheProcessInsId\":0,\"wheFixed\":0,\"fixedValue\":\"\",\"wheDate\":0,\"dateFormat\":\"\"}]";
        List<InteractiveBusinessDTOQQ> businessDTOList = GsonUtil.jsonToList(msg, InteractiveBusinessDTOQQ.class);
        updateData(businessDTOList, "54811422JKIKH40A");
    }

    private void updateData(List<InteractiveBusinessDTOQQ> businessDTOList, String proid) {
        for (InteractiveBusinessDTOQQ item : businessDTOList) {
            item.setTableName(item.getTableName().trim().toUpperCase());
            item.setFieldName(item.getFieldName().trim().toUpperCase());
        }
        try {
            Set<String> tableSet = new HashSet();
            for (InteractiveBusinessDTOQQ itemBus : businessDTOList) {
                tableSet.add(itemBus.getTableName());
            }
            for (String itemTable : tableSet) {
                // 分表执行
                List<String> nameList = new ArrayList();
                List<String> valueList = new ArrayList();

                List<InteractiveBusinessDTOQQ> tableBusinessDTOList = new ArrayList<InteractiveBusinessDTOQQ>();
                for (InteractiveBusinessDTOQQ interactiveBusinessDTO : businessDTOList) {
                    if (interactiveBusinessDTO.getTableName().equals(itemTable)) {
                        tableBusinessDTOList.add(interactiveBusinessDTO);
                    }
                }
                String proidName = "";
                for (InteractiveBusinessDTOQQ dto : tableBusinessDTOList) {
                    if (null != dto.getWhePrimaryKey() && 1 == dto.getWhePrimaryKey()) {
                        // 主键不行进行处理
                    } else {
                        nameList.add(dto.getFieldName());
                        String value = dto.getFieldValue();
                        if (null != dto.getWheProcessInsId() && 1 == dto.getWheProcessInsId()) {
                            proidName = dto.getFieldName();
                            value = "'" + proid + "'";
                        } else if (null != dto.getWheDate() && 1 == dto.getWheDate()) {
                            String dateFormat = "yyyy-mm-dd hh24:mi:ss";
                            value = "to_date('" + value + "', '" + dateFormat + "')";
                        } else if (null != dto.getWheFixed() && 1 == dto.getWheFixed()) {
                            value = "'" + dto.getFixedValue() + "'";
                        } else {
                            value = "'" + value + "'";
                        }
                        valueList.add(value);
                    }
                }
                String tempStr = "";
                for (int i = 0; i < nameList.size(); i++) {
                    if (i == nameList.size() - 1) {
                        tempStr += " " + nameList.get(i) + " = " + valueList.get(i);
                    } else {
                        tempStr += " " + nameList.get(i) + " = " + valueList.get(i) + ",";
                    }
                }
                String updateSql = "update " + itemTable + " set " + tempStr + " where " + proidName + " = '" + proid + "'";
                System.out.println(updateSql);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void filterSame(List<InteractiveBusinessDTOQQ> businessDTOList) {
        for (InteractiveBusinessDTOQQ item : businessDTOList) {
            item.setTableName(item.getTableName().trim());
            item.setFieldName(item.getFieldName().trim());
        }
        Set<String> set = new HashSet();
        for (InteractiveBusinessDTOQQ item : businessDTOList) {
            set.add(item.getTableName().toUpperCase());
        }
        for (String s : set) {
            List<String> fieldNameList = new ArrayList<String>();
            for (int i = 0; i < businessDTOList.size(); i++) {
                InteractiveBusinessDTOQQ item = businessDTOList.get(i);
                if (s.toUpperCase().equals(item.getTableName().toUpperCase())) {
                    if (fieldNameList.contains(item.getFieldName().toUpperCase())) {
                        System.out.println(item.getFieldName());
                        businessDTOList.remove(item);
                        i--;
                    } else {
                        fieldNameList.add(item.getFieldName().toUpperCase());
                    }
                }
            }
        }
    }

    @Data
    public class InteractiveBusinessDTOQQ {

        /**
         * 2021/2/2 17:20 @railgun apollo 配置中心的数据库连接的名称
         */
        private String apolloDataBaseName;

        /**
         * 2021/2/2 14:07 @railgun 数据库表名称
         */
        private String tableName;

        /**
         * 2021/2/2 14:09 @railgun 字段名称
         */
        private String fieldName;

        /**
         * 2021/2/2 14:09 @railgun 字段值
         */
        private String fieldValue;

        /**
         * 2021/2/2 14:10 @railgun 是否是主键【0、1】【不是、是】
         */
        private Integer whePrimaryKey;

        /**
         * 2021/2/2 14:11 @railgun 是否是流程关联字段【0、1】【不是、是】
         */
        private Integer wheProcessInsId;

        /**
         * 2021/2/2 14:14 @railgun 是否是固定值【0、1】【不是、是】
         */
        private Integer wheFixed;

        /**
         * 2021/2/2 14:14 @railgun 固定值是多少
         */
        private String fixedValue;

        /**
         * 2021/2/2 19:38 @railgun 是否是日期 【0、1】【不是、是】
         */
        private Integer wheDate;

        /**
         * 2021/2/2 20:05 @railgun 日期格式
         */
        private String dateFormat;

    }

}
