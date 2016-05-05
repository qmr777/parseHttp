package com.qmr777.praseHttp;

/**
 * Created by qmr777 on 16-5-2.
 * 规则类,自定义获取规则，通过构造方法和Getter设置
 *
 *
 */
public class Rule {

    static final int GET = 0;
    static final int POST = 1;

    static final int ID = 0;
    static final int CLASS = 1;
    static final int SELECTION = 2;

    private String[] params;
    private String[] values;
    private String url;
    private String tag;

    public enum tagName{
        ID,CLASS,SELECTION
        }
    public enum requestMethod{
        GET,POST

    }

    boolean judeg(){
        if(values.length == params.length)
            return true;
        else {
            throw new RuntimeException("参数/值数目不相等！");
        }
    }

    public String toString(){
        if(judeg()){
            if(params.length!=0){
                url+='?';
                for(int i = 0;i<params.length;i++)
                    url = url+params[i]+"="+values[i]+"&&";
                url = url.substring(0, url.length()-2);
            }
        }
        return url;

    }
    String[] getParams() {
        return params;
    }
    public void setParams(String[] params) {
        this.params = params;
    }
    String[] getValues() {
        return values;
    }
    public void setValues(String[] values) {
        this.values = values;
    }
    String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    String getTag() {
        return tag;
    }
    int getTagName() {
        return tagName;
    }
    public void setTagName(int tagName) {
        this.tagName = tagName;
    }
    private int tagName;
    int getRequestMethod() {
        return requestMethod;
    }
    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    private int requestMethod = 0;

    /**
     *
     * @param params 查询参数集
     * @param values 查询值集，和参数一一对应
     * @param url 查询url
     * @param tagName html TAG类型，可以是自定义的Class，唯一的ID，和默认的
     *                TAG(p,div,body,a....等)
     * @param tag tag具体名称
     * @param requestMethod 请求方法，GET或POST
     */

    public Rule(String[] params, String[] values, String url,int tagName, String tag, int requestMethod) {
        super();
        this.params = params;
        this.values = values;
        this.url = url;
        this.requestMethod = requestMethod;
        this.tag = tag;
        this.tagName = tagName;
    }

}
