<#if data??>
    <#assign className = "${data.table_name?cap_first}Repository">
    <#assign voName = "${data.table_name?cap_first}">
package ${packageName};

    public class ${className} extends BaseRepository<${voName}> {

    }
</#if>
