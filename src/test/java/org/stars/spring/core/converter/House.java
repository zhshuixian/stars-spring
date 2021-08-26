package org.stars.spring.core.converter;

import java.util.Date;

/**
 * @author : xian
 */
public class House {
    private String name;

    private Date buildDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", buildDate=" + buildDate +
                '}';
    }
}
