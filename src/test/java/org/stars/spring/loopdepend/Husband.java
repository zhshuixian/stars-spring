package org.stars.spring.loopdepend;

import org.stars.spring.beans.factory.annotation.Autowired;
import org.stars.spring.stereotype.Component;

/**
 * @author : xian
 */
@Component("husband")
public class Husband {
    @Autowired
    private Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
