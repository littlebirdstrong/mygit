package com.controller;

import com.bean.XpathBean;
import com.service.IXpathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/xpath")
public class XpathController {
    @Autowired
    private  IXpathService iXpathService;
    @RequestMapping("/getURL")
    public String getXpath(HttpServletRequest request, Model model){

        String URL = request.getParameter("url");
        System.out.println(URL);
        String id = request.getParameter("id");
        iXpathService.startSpider(URL,id);
        System.out.println("爬取结束");
        return "index";
    }
    @RequestMapping("/getXpathList")
    public String getXpathList(HttpServletRequest httpServletRequest, Model model){
        List<XpathBean> list = iXpathService.getXpathList();
        model.addAttribute("list",list);
        return "success";
    }

}
