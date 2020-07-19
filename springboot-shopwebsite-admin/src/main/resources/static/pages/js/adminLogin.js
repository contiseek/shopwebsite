//验证码显示
var num;
$(function () {
    draw(show_num);
    num = show_num.join("");
    // 验证码点击刷新
    $("#canvas").on('click', function () {
        draw(show_num);
        num = show_num.join("");
        console.log("num: " + num);
    })
});

// 登录按钮方法
function check() {
	//传值对象
	var logindata = new Object();
	// 取出输入框的值
	var checkInput = adminLoginForm.checkCode.value;
	logindata.username = adminLoginForm.username.value;
	logindata.password = adminLoginForm.password.value;
	// 验证是否为空
	if ((logindata.username == "") || (logindata.password == "") 
			|| (checkInput == "")) {
		layer.msg('输入框不可为空', {
			icon: 5
		});
		// console.log("*******************************************");
	}else{
		//num为当前显示的验证码数值
		if (checkInput == num) {
			var checkPass = false;
			$.ajax({
				type: "POST",
				url: "/adminlogin",
				data: logindata,
				dataType: "json",
				// dataType:String,
				success: function(data) {
					checkPass = data.loginCheck;
					// console.log("data:" + data);
					if (data.loginCheck) {
						
						// alert("请确认登录");
						// checkPass = data.loginCheck;
						console.log("checkPass:" + checkPass);
						window.location.href = 'goodsWork.html';
						// window.location.href = 'indexTest.html';
					} else {
						layer.msg('用户名密码错误!', {icon: 5});
					}
				},
				error: function(data, type, err) {
					layer.msg('用户名密码错误!', {icon: 5});
				}
			});
		} else {
			layer.msg('验证码错误!', {icon: 5});
		}
	}
}

// 验证码方法
var show_num = [];
function draw(show_num) {
    var canvas_width = $('#canvas').width();
    var canvas_height = $('#canvas').height();
    var canvas = document.getElementById("canvas");// 获取到canvas的对象，演员
    var context = canvas.getContext("2d");// 获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    // var sCode =
	// "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var sCode = "1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;// 获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);// 获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;// 产生0~30之间的随机弧度
        var txt = aCode[j];// 得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;// 文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;// 文字在canvas上的y坐标
        context.font = "bold 30px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { // 验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { // 验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

function randomColor() {// 得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}
