package com.java.designpattern.observer.test;

import com.java.designpattern.observer.dto.NoticeMessageDto;
import com.java.designpattern.observer.service.impl.Student;
import com.java.designpattern.observer.service.impl.Teacher;
import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/9/27
 * PS: 观察者测试
 */
public class ObserverTest {

    @Test
    public void test1() {
        Teacher teacher = new Teacher();
        NoticeMessageDto noticeMessageDto = new NoticeMessageDto().setTopic("关于举办国庆活动的通知").
                setContent("学校将于 9 月 30 日晚上举报国庆活动，包含歌唱、舞蹈、小品等。希望大家能够玩得开心");

        Student student = new Student(teacher);
        Student student1 = new Student(teacher);
        Student student2 = new Student(teacher);

        // 通知所有学生
        teacher.notifyObservers();

    }

}
