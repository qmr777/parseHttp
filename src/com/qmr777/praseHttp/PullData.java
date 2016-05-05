package com.qmr777.praseHttp;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by qmr777 on 16-5-2.
 *
 */
class PullData {

    static void getData(Rule rule) throws IOException {
        Connection connection = Jsoup.connect(rule.getUrl());
        Document document = null;
        if (rule.judeg()) {
            for (int i = 0; i < rule.getParams().length; i++)
                connection.data(rule.getParams()[i], rule.getValues()[i]);
            if (rule.getRequestMethod() == Rule.GET)
                document = connection.get();
            else
                document = connection.post();
        }
        ArrayList<Element> elements = new ArrayList<>();

        switch (rule.getTagName()) {
            case Rule.CLASS:
                elements = document.getElementsByClass(rule.getTag());
                break;
            case Rule.ID:
                elements.add(document.getElementById(rule.getTag()));
                break;
            case Rule.SELECTION:
                elements = document.getElementsByTag(rule.getTag());
                break;
            default:
                elements = document.getElementsByTag("body");
                break;
        }
        System.out.println("获取到元素的个数是：" + elements.size());

        for (Element e : elements) {
            System.out.println("图片： " + e.getElementsByTag("img").attr("src"));
            System.out.println("链接： " + e.getElementsByTag("a").attr("href"));
            //System.out.println("标题： " + e.getElementsByTag("a").attr("title"));
            //System.out.println("文字： " + e.text());
        }

        System.out.println("\n结束");

    }

}
