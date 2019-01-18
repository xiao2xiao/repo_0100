<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<hr>
<div class="container">

    <div class="row">
        <div class="col-xs-9 col-sm-6">
            <textarea class="form-control" rows="15" id="text_area_old"></textarea>
        </div>
        <div class="col-xs-9 col-sm-6">
            <textarea class="form-control" rows="15" id="text_area_new">
            </textarea>
        </div>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-xs-8 col-sm-5">
    </div>
    <div class="col-xs-2 col-sm-2">
        <button type="button" class="btn btn-primary" id="btn_submit">提交</button>
        <button type="button" class="btn btn-primary" id="btn_clear">清空</button>
    </div>
    <div class="col-xs-8 col-sm-5">
    </div>
</div>


</div> <!-- /container -->


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script>
    $(function () {
        function textareaTo(str) {
            var reg = new RegExp("\n", "g");
            var regSpace = new RegExp(" ", "g");

            str = str.replace(reg, "<br>");
            str = str.replace(regSpace, "&nbsp;");

            return str;
        }

        function toTextarea(str) {
            var reg = new RegExp("<br>", "g");
            var regSpace = new RegExp("&nbsp;", "g");

            str = str.replace(reg, "\n");
            str = str.replace(regSpace, " ");

            return str;
        }

        $('#btn_submit').click(
                function () {
                    var text_area_old = $('#text_area_old').val();
                    $.ajax({
                        url: '/handle',
                        type: 'POST',
                        data: {
                            'text_area_old': textareaTo(text_area_old)
                        },
                        success: function (data) {
                            console.log(toTextarea(data.text_area_new));
                            if (data.success) {
                                $('#text_area_new').html(toTextarea(data.text_area_new));
                            }
                        }
                    });
                });
        $('#btn_clear').click(
                function () {
                    $('#text_area_old').val("");
                    $('#text_area_new').html("");
                });
    });
</script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>