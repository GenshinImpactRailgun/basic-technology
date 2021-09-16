package com.basic.comon.constant;

/**
 * @Author: railgun
 * 2021/9/11 11:22
 * PS:
 */
public class KafkaConstant {

    private KafkaConstant() {
    }

    /**
     * 2021/9/11 11:23 @railgun topic
     */
    public static final String TEST_TOPIC = "my-topic";

    /**
     * 2021/9/13 9:15 @railgun 指定发送分区的 key
     */
    public static final String APPOINT_SEND_PARTITION_KEY = "OtakuTechnologySaveTheWorld";

}
