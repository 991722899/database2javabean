<#if data??>
package ${packageName};

public class ${data.table_name?cap_first} {
<#list data.list as item>
    <#if conf.comment>
    //${item.column_comment!}
    </#if>
    private ${item.column_dataType} ${item.column_name?uncap_first} extends BaseModel;
</#list>

    <#assign toS = "\"${data.table_name?cap_first}{\" +\n\t">
<#list data.list as item>
    <#if conf.get>
    public ${item.column_dataType} get${item.column_name?cap_first}(){
        return this.${item.column_name};
    }
    </#if>
    <#if conf.set>
    public void set${item.column_name?cap_first}(${item.column_dataType} ${item.column_name}){
        this.${item.column_name}=${item.column_name};
    }
        <#assign toS = toS+"\"${item.column_name}='\" + ${item.column_name} + '\\'' +\n\t">
    </#if>
</#list>
    <#assign toS = toS+"'}'+super.toString();">

<#if conf.tostring>
    @Override
    public String toString(){
        return ${toS}
    }
</#if>
}
</#if>
