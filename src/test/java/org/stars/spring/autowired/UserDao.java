package org.stars.spring.autowired;

import org.stars.spring.beans.IUserDao;
import org.stars.spring.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xian
 */
@Component
public class UserDao implements IUserDao {
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("1001", "豆瓣酱 10 元");
        map.put("1002", "沙拉酱 12 元");
        map.put("1003", "番茄酱 11 元");
    }

    @Override
    public String queryUserName(String username) {
        return map.get(username);
    }
}
