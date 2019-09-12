package com.service;

import com.bean.XpathBean;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public interface IXpathService {
    public List<String> startSpider(String url, String id_Name);
    public List<XpathBean> getXpathList();

}
