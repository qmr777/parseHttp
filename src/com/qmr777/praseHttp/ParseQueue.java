package com.qmr777.praseHttp;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by qmr777 on 16-5-5.
 *
 */
public class ParseQueue {

    public ParseQueue(String url,String start){
        this.url = url;
        this.start = start;
        stringQueue.add(url);
        pull();
    }
    private String url;
    private String start;

    int count = 0;
    private Queue<String> stringQueue = new LinkedList<>();
    private Set<String> stringSet = new HashSet<>();

    private void pull(){
        while (!stringQueue.isEmpty()){
            parse(stringQueue.poll());
        }
        System.out.println("共抓取到了"+count+"个页面");
    }

    private void parse(String url){
        if(stringSet.contains(url))
            return;

        count++;
        stringSet.add(url);
        Document document = null;
        Elements elements;
        Connection connection = Jsoup.connect(url);
        connection.header("Accept-Encoding", "gzip, deflate");
        connection.header("Connection", "keep-alive");
        connection.header("Host", "blog.csdn.net");
        connection.header("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0");
        connection.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(document!=null) {
            System.out.println("文章标题："+document.getElementsByTag("title").text());
            System.out.println("文章链接："+url);
            elements = document.getElementsByTag("a");
            for(Element element:elements){
                String u = element.getElementsByTag("a").attr("abs:href");
                if(u.startsWith(start)&&!u.contains("#"))
                    stringQueue.add(u);
            }
        }

    }


}
