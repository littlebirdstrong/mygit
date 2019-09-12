package com.dao;

import com.bean.XpathBean;

import java.util.List;

public interface XpathMapper {
    public void insertXpath(XpathBean xpath);
    public List<XpathBean> getXpathList();
}
