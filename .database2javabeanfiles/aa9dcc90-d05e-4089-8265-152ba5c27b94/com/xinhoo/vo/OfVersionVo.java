package com.xinhoo.vo;

@Table(name = "ofVersion")
public class OfVersion {
    //
    private String name;
    //
    private String version;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getVersion(){
        return this.version;
    }
    public void setVersion(String version){
        this.version=version;
    }

    @Override
    public String toString(){
        return "OfVersion{" +
	"name='" + name + '\'' +
	"version='" + version + '\'' +
	'}';
    }
}
