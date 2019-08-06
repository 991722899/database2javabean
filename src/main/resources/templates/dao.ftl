<#if data??>
    <#assign className = "${data.table_name?cap_first}DAO">
    <#assign voName = "${data.table_name?cap_first}">

    package ${packageName};
    public class ${className} extends AbstractDAO<${voName}> {

    }
</#if>
