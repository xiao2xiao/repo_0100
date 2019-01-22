$(function () {
    var c = document.getElementById("myCanvas");
    var width = c.width;//获取前端页面width的值
    var height = c.height;//获取前端页面height的值
    //原始坐标和计算屏幕坐标的转化，实际是y轴的变化，即使用height-y;
    //矩形和桌面坐标的变化，也是y轴的变化，即height-y-矩形本身的高度
    $.ajax({
        url: "/getpoints",
        type: "get",
        dataType: "json",
        success: function (data) {
            if (data.success) {
                getInit(data);
            }
        }
    });

    //初始化
    function getInit(data) {
        var ctx = c.getContext("2d");
        //draw axis
        drawAxis(ctx);
        // draw rectangle
        drawRect(ctx, data.rectangles);
        // draw path
        drawPath(ctx, data.points)
    }

    //draw axis
    function drawAxis(ctx) {
        //绘制X轴
        ctx.beginPath();
        ctx.moveTo(0, height);
        ctx.lineTo(width, height);
        ctx.lineTo(width - 10, height - 10);

        ctx.lineJoin = 'round'; //线的连接处采用圆角
        ctx.lineWidth = 5;
        ctx.strokeStyle = '#0a0';
        ctx.stroke();

        //绘制Y轴
        //必须开始新路径
        ctx.beginPath();
        ctx.moveTo(0, height);
        ctx.lineTo(0, 0);
        ctx.lineTo(10, 10);

        ctx.lineWidth = 5;
        ctx.strokeStyle = '#00f';
        ctx.stroke();
    }

    // draw rectangle
    function drawRect(ctx, data) {
        data.map(function (item, index) {
            ctx.beginPath();
            ctx.fillStyle = "#00FFFF";
            //修改item.y值为height-item.y-item.heigth
            ctx.fillRect(item.x, height - item.y - item.height, item.width, item.height);
            ctx.stroke();
        });
    }

    // draw path
    function drawPath(ctx, data) {
        ctx.beginPath();
        data.map(function (item, index) {
            if (index == 0) {
                ctx.moveTo(item.x, height - item.y);
            }
            ctx.lineTo(item.x, height - item.y);
        });
        ctx.strokeStyle = "#FF0000";
        ctx.stroke();
    }
})