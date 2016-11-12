package workstation.core.test.utils.testclass;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by WangXiaoJia on 2016/6/15.
 */

@XmlRootElement
public class Student {
    private int id;
    private String name;
    private int old;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }


    public Student() {
        super();
    }
}
