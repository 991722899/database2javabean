package com.xinhoo.database2javabean.model;

/**
 * @ClassName: JavaBeanConfig
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 16:40
 */
public class JavaBeanConfig {
    private String replaceDBPre;
    private String [] filterField = new String[]{};
    private boolean comment;
    private boolean tostring;
    private boolean get;
    private boolean set;
    private String[]packageName;


    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isTostring() {
        return tostring;
    }

    public void setTostring(boolean tostring) {
        this.tostring = tostring;
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }

    public String[] getPackageName() {
        return packageName;
    }

    public void setPackageName(String[] packageName) {
        this.packageName = packageName;
    }

    public String getReplaceDBPre() {
        return replaceDBPre;
    }

    public void setReplaceDBPre(String replaceDBPre) {
        this.replaceDBPre = replaceDBPre;
    }

    public String[] getFilterField() {
        return filterField;
    }

    public void setFilterField(String[] filterField) {
        this.filterField = filterField;
    }
}
