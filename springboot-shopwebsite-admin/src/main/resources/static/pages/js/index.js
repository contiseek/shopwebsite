


function openBuyPage(event){
	var picId = event.id;
	
	layer.open({
		type: 1,
		title: "",
		area: ["500px", "500px"],
		content: $("buyPageId"),
	});
}
/**/
/*购物车刷新*/



var session;

$.ajax({
	type: "POST",
	url: "../userSession",
	//data: buycardata,
	dataType: "json",
	// dataType:String,
	success: function(data) {
		// checkPass = data.registCheck;
		//console.log("data:" + data);
		if (data.username!="") {
			session=data.username;
			console.log("username:" + session);
			// window.location.href = 'indexTest.html';
		} else {
			session="";
			window.location.href = 'index.html';
		}
	},
	error: function(data, type, err) {
		layer.msg('登录超时!', {
			icon: 5
		});
	}
});



//setTimeout(function(){session=""},10800000);//3小时关闭登录状态


//打开购物车弹窗
function buyCarOpen() {
	buycarRefresh();
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["700px", "500px"],
		content: $("#buyCarBoxId"),
	});
	//
}
	
// 打开订单弹窗
function ordersOpen() {
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["800px", "500px"],
		content: $("#ordersBoxId"),
	});
}
// 打开收获地址弹窗
function addressOpen() {
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["700px", "150px"],
		content: $("#addressBoxId"),
	});
	
	//获取id
	
	var userId;	
	var ordersOld = new Object();
	ordersOld.username=session;
	
	$.ajax({
		type: "POST",
		url: "/getUserId",
		data: ordersOld,
		dataType: "json",
		success: function(data) {
			userId=data.userId;
			console.log("userId: "+userId);
		},
		error: function(data, type, err) {
			layer.msg('失败!', {icon: 5});
		}
	});
	//写入表格
	var ordersOld2 = new Object();
	ordersOld2.userId=userId;
	$.ajax({
		type: "POST",
		url: "/useraddress",
		data: ordersOld2,
		dataType: "json",
		success: function(data) {
			if(data.userid!=null){
				//清空原来数据
				var tb = document.getElementById('addressBoxId');
				//出现Cannot read property 'rows' of null ,输出tb为null, html的table名称多了一个空格
			    var rowNum=tb.rows.length;
			    //i从1开始，避免删除表头
			    for (i=1;i<rowNum;i++)
			    {
			        tb.deleteRow(i);
			        rowNum=rowNum-1;
			        i=i-1;
			    }
				str = "<tr  onmouseover=\"this.style.backgroundColor='#91c7cc';\""
		    	  	+"onmouseout=\"this.style.backgroundColor='#d4e3e5';\">"
		      		+"<td>"+1
		      		+"</td><td>"+张三
		      		+"</td><td>"+1234567891
		      		+"</td><td>"+四川省成都市
		      		+"</td></tr>";
		      // 追加到table中
		      $("#addressBoxId").append(str);
			}
		},
		error: function(data, type, err) {
			layer.msg('失败!', {icon: 5});
		}
	});
	
}
// 打开个人信息弹窗
function userInfoOpen() {
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["700px", "150px"],
		content: $("#userInfoBoxId"),
	});
	//
	var ordersOld = new Object();
	ordersOld.username="userNo1";
	$.ajax({
		type: "POST",
		url: "/userinfo",
		data: ordersOld,
		dataType: "json",
		success: function(data) {
			//传过来的是list  json数据
			//console.log("data:" + data);
			if(data.userid!=null){
				//ordersRefresh();
				//layer.msg(data.userid+data.username+data.password, {icon: 6});
				//清空原来数据
				var tb = document.getElementById('userInfoTable');
				//出现Cannot read property 'rows' of null ,输出tb为null, html的table名称多了一个空格
			    var rowNum=tb.rows.length;
			    //i从1开始，避免删除表头
			    for (i=1;i<rowNum;i++)
			    {
			        tb.deleteRow(i);
			        rowNum=rowNum-1;
			        i=i-1;
			    }
				str = "<tr  onmouseover=\"this.style.backgroundColor='#91c7cc';\""
		    	  	+"onmouseout=\"this.style.backgroundColor='#d4e3e5';\">"
		      		+"<td>"+data.userid
		      		+"</td><td>"+data.username
		      		+"</td><td>"+data.password
		      		+"</td><td><button class=\"userInfoTableButton\">修改密码</button>"
		      		+"<button class=\"userInfoTableButton\">修改用户名</button></td></tr>";
		      // 追加到table中
		      $("#userInfoTable").append(str);
			}
			
		},
		error: function(data, type, err) {
			layer.msg('失败!', {icon: 5});
		}
	});
	
	
}
//个人信息




// 打开用户注册登录弹窗
// 验证码
var num_regist;
function registBoxOpen() {
	
	// 验证码显示
	
	$(function() {
		draw(show_num_regist);
		num_regist = show_num_regist.join("");
		// 验证码点击刷新
		$("#canvas").on('click', function() {
			draw(show_num_regist);
			num_regist = show_num_regist.join("");
			console.log("num_regist: " + num_regist);
		})
	});
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["350px", "280px"],
		content: $("#userRegistBox"),
	});
	// alert("弹出");
}
// 注册

// 注册按钮方法
function userRegistPost() {
	// 传值对象
	var registdata = new Object();
	// 取出输入框的值
	var checkInput = registForm.checkCode.value;
	registdata.username = registForm.username.value;
	registdata.password = registForm.password.value;
	console.log(registdata.username+registdata.password);
	// 验证是否为空
	if ((registdata.username == "") || (registdata.password == "") ||
		(checkInput == "")) {
		layer.msg('输入框不可为空', {
			icon: 5
		});
		// console.log("*******************************************");
	} else {
		// num为当前显示的验证码数值
		if (checkInput == num_regist) {
			var checkPass = false;
			console.log("registdata:"+String(registdata))
			$.ajax({
				type: "POST",
				url: "/userregist",
				data: registdata,
				dataType: "json",
				// dataType:String,
				success: function(data) {
					// checkPass = data.registCheck;
					// console.log("data:" + data);
					if (data.registCheck) {

						// alert("请确认登录");
						// checkPass = data.registCheck;
						layer.msg('注册成功!', {
							icon: 6
						});
						console.log("checkPass:" + checkPass);
						window.location.href = 'index.html';
						// window.location.href = 'indexTest.html';
					} else {
						layer.msg('用户名已存在!', {
							icon: 5
						});
					}
				},
				error: function(data, type, err) {
					layer.msg('注册失败!', {
						icon: 5
					});
				}
			});
		} else {
			layer.msg('验证码错误!', {
				icon: 5
			});
		}
	}
}





var num_login;
function loginBoxOpen() {
	// 验证码
	// 验证码显示
	
	$(function() {
		drawLogin(show_num_login);
		num_login = show_num_login.join("");
		// 验证码点击刷新
		$("#canvas1").on('click', function() {
			drawLogin(show_num_login);
			num_login = show_num_login.join("");
			console.log("num_login: " + num_login);
		})
	});
	// layer.msg('这是最常用的吧');
	// js打开新窗口window.open ("login.html")
	layer.open({
		type: 1,
		title: "",
		area: ["350px", "280px"],
		content: $("#userLoginBox"),
	});
	// alert("弹出");

}
	// 登录
	function userLoginPost() {
		// 传值对象
		var logindata = new Object();
		// 取出输入框的值
		var checkInput = loginForm.checkCode.value;
		logindata.username = loginForm.username.value;
		logindata.password = loginForm.password.value;
		// alert(checkInput + logindata.username + logindata.password);
		// 验证是否为空
		if ((logindata.username == "") || (logindata.password == "") ||
			(checkInput == "")) {
			layer.msg('输入框不可为空', {
				icon: 5
			});
			// console.log("*******************************************");
		} else {
			// num为当前显示的验证码数值
			if (checkInput == num_login) {
				var checkPass = false;
				$.ajax({
					type: "POST",
					url: "/userlogin",
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
							window.location.href = 'index.html';
							// window.location.href = 'indexTest.html';
						} else {
							layer.msg('用户名密码错误!', {
								icon: 5
							});
						}
					},
					error: function(data, type, err) {
						layer.msg('用户名密码错误!', {
							icon: 5
						});
					}
				});
			} else {
				layer.msg('验证码错误!', {
					icon: 5
				});
			}
		}
	}




// 轮播图
layui.use(['carousel', 'form'], function() {
	var carousel = layui.carousel,
		form = layui.form;

	// 常规轮播
	carousel.render({
		elem: '#test1',
		arrow: 'always',
		width: '600px',
		height: '400px'
	});

	// 改变下时间间隔、动画类型、高度
	carousel.render({
		elem: '#test2',
		interval: 900,
		anim: 'fade',
		height: '1200px'
	});
});


//购物车刷新

function buycarRefresh(){
	//提交刷新请求
	var baycarOld = new Object();
	baycarOld.username=session;
	console.log("baycarOld.username"+baycarOld.username);
	console.log("session"+session);
	
	$.ajax({
		type: "POST",
		url: "/buycar",
		data: baycarOld,
		dataType: "json",
		//dataType:String,
		success: function(data) {
			//传过来的是list  json数据
			console.log("data:" + data);
			showbuycarData(data);
		},
		error: function(data, type, err) {
			layer.msg('刷新失败!', {icon: 5});
		}
	});
}

//数据展示函数
function showbuycarData(data){
	//清空原来数据
	var tb = document.getElementById('buycarTable');
	//出现Cannot read property 'rows' of null ,输出tb为null, html的table名称多了一个空格
    var rowNum=tb.rows.length;
    //i从1开始，避免删除表头
    for (i=1;i<rowNum;i++)
    {
        tb.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    } 
    
    //展示新数据
	var str = "";// 定义用于拼接的字符串
	for (var i = 0; i < data.length; i++) {
      // 拼接表格的行和列
      //str = "<tr><td>" + data[i].name + "</td><td>" + data[i].password + "</td></tr>";
		   var idhead = i.toString();
		  //在这里先设好findId0，不能在str中写，在str中不能合并字符串
			findId0 = idhead+"-"+"0";
			findId4 = idhead+"-"+"4";
	      str = "<tr  onmouseover=\"this.style.backgroundColor='#91c7cc';\""
    	  	+"onmouseout=\"this.style.backgroundColor='#d4e3e5';\">"
      		+"<td id=\""+findId0+"\">"+data[i].id
      		+"</td><td>"+data[i].goodsid
      		+"</td><td>"+data[i].goodsname
      		+"</td><td>"+data[i].price
      		+ "</td><td>"+"<button class=\"buyCarTableButton\" id=\""
			+i+"\" onclick=\"bucarpic(this)\">图片</button>"
			+"<button class=\"buyCarTableButton\" id=\""
			+i+"\" onclick=\"buycardelete(this)\">删除</button>"
			+"<button class=\"buyCarTableButton\" id=\""
			+i+"\" onclick=\"buyallget(this)\">结算</button>"
      		+"</td>";
      // 追加到table中
      $("#buycarTable").append(str);         
   }
}








// 验证码方法
var show_num_regist = [];

function draw(show_num_regist) {
	var canvas_width = $('#canvas').width();
	var canvas_height = $('#canvas').height();
	var canvas = document.getElementById("canvas"); // 获取到canvas的对象，演员
	var context = canvas.getContext("2d"); // 获取到canvas画图的环境，演员表演的舞台
	canvas.width = canvas_width;
	canvas.height = canvas_height;
	// var sCode =
	// "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
	var sCode = "1,2,3,4,5,6,7,8,9,0";
	var aCode = sCode.split(",");
	var aLength = aCode.length; // 获取到数组的长度

	for (var i = 0; i <= 3; i++) {
		var j = Math.floor(Math.random() * aLength); // 获取到随机的索引值
		var deg = Math.random() * 30 * Math.PI / 180; // 产生0~30之间的随机弧度
		var txt = aCode[j]; // 得到随机的一个内容
		show_num_regist[i] = txt.toLowerCase();
		var x = 10 + i * 20; // 文字在canvas上的x坐标
		var y = 20 + Math.random() * 8; // 文字在canvas上的y坐标
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
var show_num_login = [];

function drawLogin(show_num_login) {
	var canvas_width = $('#canvas1').width();
	var canvas_height = $('#canvas1').height();
	var canvas = document.getElementById("canvas1"); // 获取到canvas的对象，演员
	var context = canvas.getContext("2d"); // 获取到canvas画图的环境，演员表演的舞台
	canvas.width = canvas_width;
	canvas.height = canvas_height;
	// var sCode =
	// "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
	var sCode = "1,2,3,4,5,6,7,8,9,0";
	var aCode = sCode.split(",");
	var aLength = aCode.length; // 获取到数组的长度

	for (var i = 0; i <= 3; i++) {
		var j = Math.floor(Math.random() * aLength); // 获取到随机的索引值
		var deg = Math.random() * 30 * Math.PI / 180; // 产生0~30之间的随机弧度
		var txt = aCode[j]; // 得到随机的一个内容
		show_num_login[i] = txt.toLowerCase();
		var x = 10 + i * 20; // 文字在canvas上的x坐标
		var y = 20 + Math.random() * 8; // 文字在canvas上的y坐标
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

function randomColor() { // 得到随机的颜色值
	var r = Math.floor(Math.random() * 256);
	var g = Math.floor(Math.random() * 256);
	var b = Math.floor(Math.random() * 256);
	return "rgb(" + r + "," + g + "," + b + ")";
}
