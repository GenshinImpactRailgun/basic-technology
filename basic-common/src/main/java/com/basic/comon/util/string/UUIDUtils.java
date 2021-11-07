package com.basic.comon.util.string;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/***
 * UUID
 * @author 杨红杰
 * @date 2018/11/30
 */
@Slf4j
public class UUIDUtils {

    private UUIDUtils() {}

    /**
     * 16位编号：10位日期，3位ip，1位文件计数，2位计数器，
     *
     * @return
     */
    public static String generate() {
        return getLngTime() + IDENTIFIER + getCount();
    }

    public static String generate32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static AtomicInteger counter = new AtomicInteger(0);
    private static final String IDENTIFIER = getIP() + getFileNum();

    /**
     * 2位的自增的计数器（36进制)
     *
     * @return
     */
    private static String getCount() {
        counter.compareAndSet(36 * 36, 0);
        return formatString(getHex36String(counter.incrementAndGet()), 2);
    }

    /**
     * 1位,文件计数
     *
     * @return
     */
    private static String getFileNum() {
        return formatString(String.valueOf(getInstanceSeq()), 1);
    }

    private static int bytestoInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = ((result << 8) - -128) + bytes[i];
        }
        return result;
    }

    private static byte[] getInetAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (ni.isLoopback() || ni.isVirtual() || ni.isPointToPoint() || !ni.isUp()) {
                    continue;
                }
                String name = ni.getDisplayName().toLowerCase();
                if (name.contains("convnet") || name.contains("vmnet")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    byte[] addr = addresses.nextElement().getAddress();
                    if (addr.length == 4) {
                        return addr;
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Error to get ip address");
        }
        return null;
    }

    /**
     * 用2位数来表示IP，这里面可以存在隐患,ip的最后一个部分最好不要一样
     *
     * @return
     */
    private static String getIP() {
        int ip = 0;
        try {
            ip = bytestoInt(getInetAddress());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return formatString(getHex36String(ip), 3);
    }

    private static byte getInstanceSeq() {
        Preferences prefs = Preferences.userRoot().node("egov");
        int seq = prefs.getInt("uuid-seq", 0);
        if (seq > 36) {
            seq = 0;
        }
        prefs.putInt("uuid-seq", ++seq);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            log.warn("Error to save uuid-seq");
        }
        return (byte) seq;
    }

    /**
     * 将十进制转换为36进制
     *
     * @return
     */
    private static String getHex36String(int value) {
        return Integer.toString(value, 36).toUpperCase();
    }

    /**
     * 字符串取位，不够的左边补充0，操作了取右边的部分
     *
     * @param str
     * @param length
     * @return
     */
    private static String formatString(String str, int length) {
        if (str.length() == length) {
            return str;
        } else if (str.length() > length) {
            return str.substring(str.length() - length);
        } else {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < length - str.length(); i++) {
                buf.append("0");
            }
            return buf.append(str).toString();
        }
    }

    /**
     * 用10位来表示一个到毫秒的时间
     * 年  月  日时  分   秒  毫秒
     * Z  F  Z   Z  FF  FF ZZ
     *
     * @return
     */
    private static String getLngTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millSecond = calendar.get(Calendar.MILLISECOND);
        return formatString(getHex36String(year), 1) + getHex36String(month)
                + getHex36String(day) + getHex36String(hour)
                + formatString(String.valueOf(minute), 2) + formatString(String.valueOf(second), 2)
                + formatString(getHex36String(millSecond), 2);
    }

}
