<#if data??>
    <#assign className = "${data.table_name?cap_first}Controller">
    <#assign voName = "${data.table_name?cap_first}">

    package ${packageName};
    public class ${className}{

    }
</#if>
