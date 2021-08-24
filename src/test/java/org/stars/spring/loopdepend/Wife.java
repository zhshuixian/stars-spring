package org.stars.spring.loopdepend;

import org.stars.spring.beans.factory.annotation.Autowired;
import org.stars.spring.beans.factory.annotation.Qualifier;
import org.stars.spring.stereotype.Component;

/**
 * @author : xian
 */
@Component("wife")
public class Wife {
    @Autowired
    private Husband husband;
    
    @Autowired
    @Qualifier("org.stars.spring.loopdepend.HusbandMother")
    private IMother mother;

    public String queryHusband() {
        return "Wife.husband `s Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
