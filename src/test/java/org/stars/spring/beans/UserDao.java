package org.stars.spring.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xian
 */
public class UserDao {
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("1001", "小先哥哥");
        map.put("1002", "大仙");
        map.put("1003", "烦了 毁灭吧");
    }

    public String queryUserName(String uId) {
        return map.getOrDefault(uId, "笨猪，没有这个 uid");
    }
}
