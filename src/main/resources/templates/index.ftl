<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生成配置</title>
    <link  rel="stylesheet" type="text/css" href="base.css"/>
    <link  rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
</head>
<body style="margin: 10px;">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>数据库配置</legend>
</fieldset>
<form class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <label class="layui-form-label">主机地址</label>
        <div class="layui-input-block">
            <input type="text" name="ip" autocomplete="off" placeholder="请输入IP" value="localhost" class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">端口</label>
        <div class="layui-input-block">
            <input type="text" name="port" autocomplete="off" placeholder="请输入端口" value="1433" class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数据库名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" autocomplete="off" placeholder="请输数据库名称" value="codDB " class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="user_name" autocomplete="off" placeholder="请输用户名" value="sa" class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" name="user_pwd" autocomplete="off" placeholder="请输密码" value="1209" class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数据库类型</label>
        <div class="layui-input-inline">
            <select name="db_type" lay-filter="db_type">
                <option value="mysqlHandler">mysql</option>
                <option value="oracleHandler">oracle</option>
                <option value="sqlServerHandler">sqlServer</option>
            </select>
        </div>
    </div>
<hr>
    <div class="layui-form-item">
        <label class="layui-form-label">表名前缀</label>
        <div class="layui-input-block">
            <input type="text" name="replaceDBPre" autocomplete="off" placeholder="请输入表前缀" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">过滤列名</label>
        <div class="layui-input-block">
            <input type="text" name="filterField" autocomplete="off" placeholder="多个用,号隔开" value="id,createtime,create_user_id,create_user_name,updatetime,update_user_id,update_user_name,status,remarks" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block">
                <input type="checkbox" name="models" value="vo"  checked="checked" title="vo">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">包名</label>
            <div class="layui-input-inline">
                <input type="text" name="packageName" autocomplete="off"  value="com.xinhoo.vo" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <input type="checkbox" name="tostring" value="1" title="toString"  checked="checked">
            <input type="checkbox" name="comment" value="1" title="comment"  checked="checked">
            <input type="checkbox" name="get" value="1" title="get"  checked="checked">
            <input type="checkbox" name="set" value="1" title="set"  checked="checked">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block">
                <input type="checkbox" name="models" value="dao" title="dao"  checked="checked">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">包名</label>
            <div class="layui-input-inline">
                <input type="text" name="packageName" autocomplete="off" value="com.xinhoo.dao" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block">
                <input type="checkbox" name="models" value="controller" title="controller"  checked="checked">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">包名</label>
            <div class="layui-input-inline">
                <input type="text" name="packageName" autocomplete="off" value="com.xinhoo.controller" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block">
                <input type="checkbox" name="models" value="service" title="service"  checked="checked">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">包名</label>
            <div class="layui-input-inline">
                <input type="text" name="packageName" autocomplete="off" value="com.xinhoo.service" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block">
                <input type="checkbox" name="models" value="mapper" title="mapper"  checked="checked">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">包名</label>
            <div class="layui-input-inline">
                <input type="text" name="packageName" autocomplete="off" value="com.xinhoo.mapper" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item" id="tables-view"></div>

    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="test">测试连接</button>
        <button class="layui-btn" lay-submit="" lay-filter="loadTables">加载表</button>
        <button class="layui-btn" lay-submit="" lay-filter="export">生成导出</button>
    </div>
</form>
<script id="tablesTpl" type="text/html">
    <label class="layui-form-label">选择表</label>
    <div class="layui-input-block">
        {{#  layui.each(d.data, function(index, item){ }}
            <input type="checkbox" name="tableNames" value="{{item}}" title="{{item}}">
        {{#  }); }}
    </div>
</script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript">

    function parseParams(data) {
        try {
            var tempArr = [];
            for (var i in data) {
                var key = encodeURIComponent(i);
                var value = encodeURIComponent(data[i]);
                tempArr.push(key + '=' + value);
            }
            var urlParamsStr = tempArr.join('&');
            return urlParamsStr;
        } catch (err) {
            return '';
        }
    }

    layui.use(['form','jquery','laytpl'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,$=layui.jquery
            ,laytpl = layui.laytpl;



        form.on('submit(test)', function(data){
            $.ajax({
                type: "POST",
                url: "./test",
                data: data.field,
                success: function(data){
                    if(data && data.code=="0"){
                        layer.alert("连接成功");
                    }else{
                        layer.alert(data.msg);
                    }
                },
                error:function(e){
                  layer.alert(e.responseText);
                },
                dataType: "json"
            });
            return false;
        });

        form.on('submit(loadTables)', function(data){
            $.ajax({
                type: "POST",
                url: "./getTableNames",
                data: data.field,
                success: function(data){
                    if(data && data.code=="0"){
                        laytpl(tablesTpl.innerHTML).render({"data":data.data}, function(html){
                            document.getElementById('tables-view').innerHTML = html;
                            form.render();
                        });
                    }else{
                        layer.alert(data.msg);
                    }
                },
                error:function(e){
                    layer.alert(e.responseText);
                },
            });
            return false;
        });

        form.on('submit(export)', function(data){
            var tables = new Array();
            $("#tables-view div[class='layui-unselect layui-form-checkbox layui-form-checked']").each(function () {
                tables.push($(this).children("span").text());
            });

            if(tables.length==0){
                layer.alert("请先加载表并选择要导出的表");
                return false;
            }
            var data = data.field;
            data.tableNames = tables.join(",");
            //获取所有的模块和包名
            var packageName = new Array();
            $("input[name='models']:checked").each(function(item){
                packageName.push($(this).siblings("div").find("span").text()+"-"+$(this).parent("div").parent("div").siblings("div").find("input[name='packageName']").val());
            });
            data.packageName = packageName.join(",");

            window.location.href="./export?"+parseParams(data);
            return false;
        });

        $("[name='db_type']").val('sqlServerHandler');
        $("[name='models']").val('vo');
        form.render();


    });
</script>
</body>
</html>
