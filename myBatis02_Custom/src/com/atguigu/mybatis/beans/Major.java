package com.atguigu.mybatis.beans;

import java.util.List;
import java.util.Set;

/**
 * @Author:Z
 * @Data:2021/10/10 17:47
 * @Description: 专业类
 * @Version:1.0
 */
public class Major {
    private Integer id;
    private String majorName;
    private List<PublicTestBean> beans;

    public Major(){};

    public Major(Integer id, String majorName) {
        this.id = id;
        this.majorName = majorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<PublicTestBean> getBeans() {
        return beans;
    }

    public void setBeans(List<PublicTestBean> beans) {
        this.beans = beans;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", major='" + majorName + '\'' +
                '}';
    }
}
