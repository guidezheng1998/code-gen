package com.example.entity;
/**
 * Template 类表示一个模板的实体。
 */
public class Template {

    /** 模板的唯一标识 */
    private String id;

    /** 模板的名称 */
    private String name;

    /** 模板的类型 */
    private String type;

    /** 模板的内容 */
    private String content;

    /**
     * 无参构造方法
     */
    public Template() {
    }

    /**
     * 带参构造方法
     *
     * @param id      模板的唯一标识
     * @param name    模板的名称
     * @param type    模板的类型
     * @param content 模板的内容
     */
    public Template(String id, String name, String type, String content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 返回模板对象的字符串表示形式。
     *
     * @return 模板对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "Template{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}