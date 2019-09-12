package com.service;

import com.bean.XpathBean;
import com.dao.XpathMapper;
import com.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class XpathServiceImpl implements IXpathService {
    @Autowired
    private XpathMapper xpathMapper;



    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();
    public   List<String> startSpider(String url,String id_Name){
        Document document = null;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            String htmlPageStr = httpUtils.getHtmlPageResponse(url);
            document  = Jsoup.parse(htmlPageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  getDom4j(document,id_Name);
    }
    /*
     * 获取Xpath的入口
     *
     */
    public   List<String>  getDom4j(Document document , String id_Name) {
        if(!(id_Name.equals("html"))){
            Elements elements = document.select("[id="+id_Name+"]");
            String first_xpath = "//*[@id=\""+id_Name+"\"]/";
            return getElements(elements,first_xpath);
        }else {
            Elements elements = document.select("html");
            String first_xpath = "/html/";
            return getHtml(elements,first_xpath,id_Name);
        }

    }
    public List<String> getHtml(Elements elements,String xpath,String fileName){//获取节点html,没有名字
        for(int i = 0 ; i < elements.size() ; i++){//判断几个节点
            //取当前节点的信息
            String tag ="" ; ////*[@id="hp-search"]/div[1]/div[1]/label
            //判断有几个这样的节点
            Elements parentElements = elements.get(i).parent().select(elements.get(i).tag().toString());
            if(parentElements.size() > 1){
                for(int j = 0 ; j < elements.get(i).children().size(); j++){
                    if(elements.get(i).child(j).tag().toString().equals("br")){
                        tag  = xpath + elements.get(i).tag()+"["+(i)+"]/";
                    }else{
                        tag  = xpath + elements.get(i).tag()+"["+(i+1)+"]/";
                    }

                }

            }else {
                tag  = xpath + elements.get(i).tag()+"[1]/";
            }
            Elements childrenElements = elements.get(i).children();//获取当 期节点的儿子节点
            if(childrenElements.size() > 0){//判断是否有儿子节点
                //循环调用方法获得子节点
                getHtml(childrenElements,tag,fileName);

            }else {
                //将得到的xpath以"/"进行分组
                if(tag.length()!=0){
                    String[] xpathArray = tag.split("/");
                    String bb = elements.get(0).text();
                    list.add(deleteHtml(bb,2,xpathArray,elements.get(i).parent()));
                }


            }


        }
        return  list;
    }
    public  List<String> getElements(Elements elements,String xpath) {//获取入口id

        for(int i = 0 ; i < elements.size() ; i++){//判断几个节点
            //取当前节点的信息
            String tag="" ; ////*[@id="hp-search"]/div[1]/div[1]/label
            //判断有几个这样的节点
            Elements parentElements = elements.get(i).parent().select(elements.get(i).tag().toString());
            if(parentElements.size() > 1){
                if(elements.get(i).tag().toString().equals("ul")){
                    tag  = xpath + elements.get(i).tag()+"/";
                }else{
                    tag  = xpath + elements.get(i).tag()+"["+(i+1)+"]/";
                }

            }else {
                tag  = xpath + elements.get(i).tag()+"[1]/";
            }
            Elements childrenElements = elements.get(i).children();//获取当 期节点的儿子节点
            if(childrenElements.size() > 0){//判断是否有儿子节点
                //循环调用方法获得子节点
                getElements(childrenElements,tag);

            }else {
                //将得到的xpath以"/"进行分组
                String[] xpathArray = tag.split("/");
                //删除第三个的div,因为包含了id=index-yewutishi的标签
                /*
                 * n 删除元素的位置
                 * xpath 需要操作的数组
                 * elements.get(i) 具体的节点
                 * */
                String bb = "";
                if(elements.get(i).text().equals("")||elements.get(i).text()==null){
                    bb = elements.get(i).attr("value");

                }else {
                    bb = elements.get(i).text();
                }
                if(bb.equals("") || bb == null){
                    if(list1.size()==0){
                        list.add(deleteElement(elements.get(i).text(),3,xpathArray,elements.get(i)));

                    }else {
                        list.add(deleteElement(list1.get(0),3,xpathArray,elements.get(i)));

                    }
                }else{
                    list1.add(0,bb);
                    list.add(deleteElement(bb,3,xpathArray,elements.get(i)));

                }

            }


        }
        return  list;
    }
    public String deleteHtml(String bb,int n , String[] arr,Element element){
        arr[arr.length - 1] = "text()";
        //定义一个新数组
        return deleteElement(bb,n,arr,element);
    }
    public   String deleteElement(String bb,int n , String[] arr,Element element){
        //定义一个新数组
        String[] arrNew = new String[arr.length - 1];
        //删除数组某个元素
        for(int i = n; i < arrNew.length ; i++){
            arr[i] = arr[i+1];
        }
        System.arraycopy(arr,0,arrNew,0,arrNew.length);
        String xpath = "" ;
        for(int m = 0 ; m < arrNew.length; m++){
            //重新拼接xpath生成想要的最终结果
            //System.out.println(m+" "+arrNew[m]);
            xpath = xpath+arrNew[m]+"/";
            // System.out.println(xpath);
        }
        //-1 去掉xpath中最后的/
        String last_xpath =xpath.substring(0,xpath.length()-1);
        //CreateXpathFile.WriteXpath(fileName+".txt",last_xpath);
        XpathBean xpathBean = new XpathBean();
        xpathBean.setName(bb);
        xpathBean.setType(isXpathType(element));
        xpathBean.setXpath(last_xpath);
        //存放到数据
        xpathMapper.insertXpath(xpathBean);
        return last_xpath;
    }
    /**
     * 判断什么类型
     */
    public  String isXpathType(Element element) {
        String text = element.toString();
        if (text.contains("<input")) {
            if(element.attr("type").equals("text")){
                return "文本输入框";
            }
            if(element.attr("type").equals("submit")){
                return "提交按钮";
            }
        } else if (text.contains("onclick=")) {
            return "按钮";
        }else if(element.tagName().equals("img")){
            return "图片";
        }
        return "文本内容";
    }


    //查询结果数据
    public List<XpathBean> getXpathList(){
        return xpathMapper.getXpathList();
    }
}
