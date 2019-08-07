<#if data??>
<#assign className = "${data.table_name?cap_first}DAO">
<#assign voName = "${data.table_name?cap_first}">
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.${className}" >

    <insert id="save">
        insert into ${data.table_name}
        (
        <#list data.list as item>
            ${item.column_name}<#if item_has_next>,</#if>
        </#list>
       )
        values(
            <#list data.list as item>
                ${r'#{'}${item.column_name}${r'}'}<#if item_has_next>,</#if>
            </#list>
        )
    </insert>

    <update id="edit">
        update ${data.table_name}
        <set>
        <#list data.list as item>
        <#if item.column_dataType=="String">
            <if test="${item.column_name} != null and ${item.column_name} != ''">
                ${item.column_name}=${r'#{'}${item.column_name}${r'}'},
            </if>
        <#else>
            <if test="${item.column_name} != null">
                ${item.column_name}=${r'#{'}${item.column_name}${r'}'},
            </if>
        </#if>
        </#list>
        </set>
        where id = ${r'#{'}id${r'}'}
    </update>

    <select id="findById" resultType="${voName}">
        select * from ${data.table_name} where id = ${r'#{'}id${r'}'}
    </select>

    <select id="findByModel" resultType="${voName}">
        select * from ${data.table_name} where 1=1
        <#list data.list as item>
        <#if item.column_dataType=="String">
            <if test="${item.column_name} != null and ${item.column_name} != ''">
                and ${item.column_name}=${r'#{'}${item.column_name}${r'}'}
            </if>
        <#else>
            <if test="${item.column_name} != null">
                and ${item.column_name}=${r'#{'}${item.column_name}${r'}'}
            </if>
        </#if>
        </#list>
    </select>
</#if>