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
<script src="aa.js"></script>
</head>
<body>
<hr>
<div class="container">

    <canvas id="myCanvas" width="1000" height="600" style="border:1px solid #d3d3d3;">
        您的浏览器不支持 HTML5 canvas 标签。
    </canvas>
    <script>
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");

        //绘制X轴
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(950, 0);
        ctx.lineTo(950 - 10, 10);

        //ctx.lineJoin = 'miter'; //线的连接处采用尖角
        //ctx.lineJoin = 'bevel'; //线的连接处采用方角
        ctx.lineJoin = 'round'; //线的连接处采用圆角
        ctx.lineWidth = 5;
        ctx.strokeStyle = '#0a0';
        ctx.stroke();

        //绘制Y轴
        ctx.beginPath();  //必须开始新路径
        ctx.moveTo(0, 0);
        ctx.lineTo(0, 590);
        ctx.lineTo(10, 590 - 10);

        ctx.strokeStyle = '#00f';
        ctx.stroke();


        // Red rectangle
        ctx.beginPath();
        ctx.fillStyle = "#FF0000";
        ctx.fillRect(200, 50, 100, 300);
        ctx.stroke();


        // Green rectangle
        ctx.beginPath();
        ctx.fillStyle = "#00FFFF";
        ctx.fillRect(400, 50, 100, 400);
        ctx.stroke();

        // Blue rectangle
        ctx.beginPath();
        ctx.fillStyle = "#00FF00";
        ctx.fillRect(600, 50, 100, 400);
        ctx.stroke();

        // 绘制路劲
        ctx.beginPath();
        ctx.moveTo(20, 20);
        ctx.lineTo(30, 30);
        ctx.lineTo(30, 300);
        ctx.lineTo(100, 350);
        ctx.lineTo(150, 450);
        ctx.lineTo(250, 450);
        ctx.lineTo(350, 450);
        ctx.lineTo(320, 50);
        ctx.lineTo(450, 20);
        ctx.lineTo(500, 20);
        ctx.lineTo(550, 60);
        ctx.lineTo(570, 480);
        ctx.lineTo(610, 480);
        ctx.lineTo(660, 460);
        ctx.lineTo(710, 460);
        ctx.lineTo(710, 300);
        ctx.strokeStyle = "#FF0000";
        ctx.stroke();
    </script>


</div> <!-- /container -->


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>