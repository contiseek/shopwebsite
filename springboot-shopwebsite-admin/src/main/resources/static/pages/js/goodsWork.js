goodsRefresh();
//订单处理跳转
function ordersWork() {
	window.location.href = 'ordersWork.html';
}
//打开添加商品弹窗
function goodsAdd() {
	layer.open({
		type: 1,
		title: "",
		area: ["600px", "270px"],
		content: $("#addgoodsBox"),
	});
}
//库存管理窗口打开
function roomUpdate() {
	//layer.msg('这是最常用的吧');
	//js打开新窗口window.open ("login.html")

	layer.open({
		type: 1,
		title: "",
		area: ["600px", "220px"],
		content: $("#goodsRoomBox"),
	});
	//alert("弹出");
}
//图片展示
function picture(event) {
	var id = event.id.toString();
	//alert("id: "+id+typeof(id));
	var findId = id+"-"+"2";
	var findurl = id+"-"+"4";
	var url = document.getElementById(findurl).innerHTML;
	var img = "<img src="+url+" id='img1' width='300px' height='300px'/>";
	var goodsId = document.getElementById(findId).innerHTML;
	//alert(typeof(goodsId));
	/*layer.tab({
		area: ['385px', '400px'],
		tab: [{
			title: goodsId + "号商品图片",
			content: img
		}]
	});*/
	layer.open({
		  type: 1,
		  title: goodsId, //+ "号商品图片",
		  skin: 'layui-layer-rim', //加上边框
		  area: ['300px', '347px'], //宽高
		  content: img
		});
}

//仓库管理
function goodsRoomPost() {
	//取出输入框中数据，存入对象中
	var goodsRoom = new Object();
	goodsRoom.goodsid = document.goodsRoomForm.GoodsRoomId.value;
	goodsRoom.goodsnumber = document.goodsRoomForm.GoodsRoomNumber.value;
	if ((goodsRoom.goodsid == "") || (goodsRoom.goodsnumber == "")) {
		layer.msg('输入框不可为空', {
			icon: 5
		});
		//console.log("*******************************************");
	} else {
		//提交到后端
		$.ajax({
			type: "POST",
			url: "/roomUpdate",
			data: goodsRoom,
			dataType: "json",
			//dataType:String,
			success: function(data) {
				//传过来的是json数据
				//console.log("data:" + data);
				if (data.roomUpdateCheck) {
					//console.log("data.addGoodsCheck:" + data.addGoodsCheck);
					goodsRefresh();
					layer.msg('更新库存成功！重置可继续更新', {
						icon: 6
					});
					//window.location.href = 'indexTest.html';
				} else {
					layer.msg('更新库存失败，请检查！', {
						icon: 5
					});
				}
			},
			error: function(data, type, err) {
				//layer.msg('ajax连接失败！', {icon: 5});
				layer.msg('更新库存失败，请检查！', {
					icon: 5
				});
			}
		});
	}
}

//打开修改商品弹窗
function updateGoods(event) {
	var id = event.id.toString();
	layer.open({
		type: 1,
		title: "",
		area: ["600px", "270px"],
		content: $("#updategoodsBox"),
	});
	//定义一个找id的字符串
	var findId = "";
	//存读到的数据
	var goodsOld = new Object();

	for (var i = 0; i < 6; i++) {
		i = i.toString();
		findId = id + "-" + i;
		if (i == "0") {
			//读取td中写的值
			goodsOld.goodsid = document.getElementById(findId).innerHTML;
			//写入input中
			document.updateGoodsForm.updateGoodsId.value = parseInt(goodsOld.goodsid);
		} else if (i == "1") {
			goodsOld.adminid = document.getElementById(findId).innerHTML;
			document.updateGoodsForm.updateGoodsAdminId.value = parseInt(goodsOld.adminid);
		} else if (i == "2") {
			goodsOld.goodsname = document.getElementById(findId).innerHTML;
			document.updateGoodsForm.updateGoodsName.value = goodsOld.goodsname;
		} else if (i == "3") {
			goodsOld.price = document.getElementById(findId).innerHTML;
			document.updateGoodsForm.updateGoodsPrice.value = goodsOld.price;
		} else if (i == "4") {
			goodsOld.picture = document.getElementById(findId).innerHTML;
			document.updateGoodsForm.updateGoodsPicture.value = goodsOld.picture;
		} else {
			goodsOld.goodsnumber = document.getElementById(findId).innerHTML;
			document.updateGoodsForm.updateGoodsNumber.value = parseInt(goodsOld.goodsnumber);
		}
	}
	//layer.msg('goodsOld: '+goodsOld.toString(), {icon: 6});
	/*
	console.log("goodsOld.goodsid: "+goodsOld.goodsid);
	console.log("goodsOld.goodsid: "+typeof(goodsOld.goodsid));
	console.log("goodsOld.adminid: "+goodsOld.adminid);
	console.log("goodsOld.adminid: "+typeof(goodsOld.adminid));
	console.log("goodsOld.goodsname: "+goodsOld.goodsname);
	console.log("goodsOld.goodsname: "+typeof(goodsOld.goodsname));
	console.log("goodsOld.price: "+goodsOld.price);
	console.log("goodsOld.price: "+typeof(goodsOld.price));
	console.log("goodsOld.picture: "+goodsOld.picture);
	console.log("goodsOld.picture: "+typeof(goodsOld.picture));
	console.log("goodsOld.goodsnumber: "+goodsOld.goodsnumber);
	console.log("goodsOld.goodsnumber: "+typeof(goodsOld.goodsnumber));
	*/
}
//修改商品提交
function updateGoodsPost() {
	//获取输入框中数据
	var newGoods = new Object();
	//后端读取的是String类型，前端不用转换
	newGoods.goodsid = updateGoodsForm.updateGoodsId.value;
	newGoods.adminid = updateGoodsForm.updateGoodsAdminId.value;
	newGoods.goodsname = updateGoodsForm.updateGoodsName.value;
	newGoods.price = updateGoodsForm.updateGoodsPrice.value;
	newGoods.picture = updateGoodsForm.updateGoodsPicture.value;
	newGoods.goodsnumber = updateGoodsForm.updateGoodsNumber.value;
	if ((newGoods.goodsid == "") || (newGoods.adminid == "") ||
		(newGoods.goodsname == "") || (newGoods.price == "") || (updateGoods.picture == "") ||
		(newGoods.goodsnumber.value == "")) {
		layer.msg('输入框不可为空', {
			icon: 5
		});
		//console.log("*******************************************");
	} else {
		$.ajax({
			type: "POST",
			url: "/updateGoods",
			data: newGoods,
			dataType: "json",
			//dataType:String,
			success: function(data) {
				//传过来的是json数据
				//console.log("data:" + data);
				if (data.updateGoodsCheck) {
					//console.log("data.updateGoodsCheck:" + data.updateGoodsCheck);
					goodsRefresh();
					layer.msg('商品修改成功！', {
						icon: 6
					});
					//window.location.href = 'indexTest.html';
				} else {
					layer.msg('商品修改失败，请检查！', {
						icon: 5
					});
				}
			},
			error: function(data, type, err) {
				//layer.msg('ajax连接失败！', {icon: 5});
				layer.msg('商品修改失败，请检查！', {
					icon: 5
				});
			}
		});
	}
}


///
//添加商品提交
/*
`goodsid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `goodsname` varchar(128) NOT NULL,
  `price` varchar(20) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `goodsnumber` int(11) NOT NULL,
*/
function addGoodsPost() {
	var newGoods = new Object();
	//后端读取的是String类型，前端不用转换
	newGoods.goodsid = addGoodsForm.addGoodsId.value;
	newGoods.adminid = addGoodsForm.addGoodsAdminId.value;
	newGoods.goodsname = addGoodsForm.addGoodsName.value;
	newGoods.price = addGoodsForm.addGoodsPrice.value;
	newGoods.picture = addGoodsForm.addGoodsPicture.value;
	newGoods.goodsnumber = addGoodsForm.addGoodsNumber.value;
	/*数据类型测试*/
	/*
	console.log("newGoods.goodsid: "+newGoods.goodsid);
	console.log("newGoods.goodsid: "+typeof(newGoods.goodsid));
	console.log("newGoods.adminid: "+newGoods.adminid);
	console.log("newGoods.adminid: "+typeof(newGoods.adminid));
	console.log("newGoods.goodsname: "+newGoods.goodsname);
	console.log("newGoods.goodsname: "+typeof(newGoods.goodsname));
	console.log("newGoods.price: "+newGoods.price);
	console.log("newGoods.price: "+typeof(newGoods.price));
	console.log("newGoods.picture: "+newGoods.picture);
	console.log("newGoods.picture: "+typeof(newGoods.picture));
	console.log("newGoods.goodsnumber: "+newGoods.goodsnumber);
	console.log("newGoods.goodsnumber: "+typeof(newGoods.goodsnumber));
	*/
	//alert("弹窗2是否正常");转换为number类型的newGoods.goodsid==NaN不能判断
	if ((addGoodsForm.addGoodsId.value == "") || (addGoodsForm.addGoodsAdminId.value == "") ||
		(newGoods.goodsname == "") || (newGoods.price == "") || (newGoods.picture == "") ||
		(addGoodsForm.addGoodsNumber.value == "")) {
		layer.msg('输入框不可为空', {
			icon: 5
		});
		//console.log("*******************************************");
	} else {
		$.ajax({
			type: "POST",
			url: "/addGoods",
			data: newGoods,
			dataType: "json",
			//dataType:String,
			success: function(data) {
				//传过来的是json数据
				//console.log("data:" + data);
				if (data.addGoodsCheck) {
					//console.log("data.addGoodsCheck:" + data.addGoodsCheck);
					goodsRefresh();
					layer.msg('商品添加成功！重置可继续添加', {
						icon: 6
					});
					//window.location.href = 'indexTest.html';
				} else {
					layer.msg('商品添加失败，请检查！', {
						icon: 5
					});
				}
			},
			error: function(data, type, err) {
				//layer.msg('ajax连接失败！', {icon: 5});
				layer.msg('商品添加失败，请检查！', {
					icon: 5
				});
			}
		});
	}
}
//商品展示刷新
function goodsRefresh() {
	//提交刷新请求
	$.ajax({
		type: "POST",
		url: "../goodsRefresh",
		//data: newGoods,不传数据
		dataType: "json",
		//dataType:String,
		success: function(data) {
			//传过来的是list  json数据
			//console.log("data:" + data);
			showData(data);
		},
		error: function(data, type, err) {
			layer.msg('刷新失败!', {
				icon: 5
			});
		}
	});
}

/*
`goodsid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `goodsname` varchar(128) NOT NULL,
  `price` varchar(20) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `goodsnumber` int(11) NOT NULL,
*/
//数据展示函数
function showData(data) {
	//清空原来数据
	var tb = document.getElementById('goodsTable');
	var rowNum = tb.rows.length;
	//i从1开始，避免删除表头
	for (i = 1; i < rowNum; i++) {
		tb.deleteRow(i);
		rowNum = rowNum - 1;
		i = i - 1;
	}

	//展示新数据
	var str = ""; // 定义用于拼接的字符串
	for (var i = 0; i < data.length; i++) {
		// 拼接表格的行和列
		//str = "<tr><td>" + data[i].name + "</td><td>" + data[i].password + "</td></tr>";
		idhead = i.toString();
		//在这里先设好findId0，不能在str中写，在str中不能合并字符串
		findId0 = idhead + "-" + "0";
		findId1 = idhead + "-" + "1";
		findId2 = idhead + "-" + "2";
		findId3 = idhead + "-" + "3";
		findId4 = idhead + "-" + "4";
		findId5 = idhead + "-" + "5";
		//findId = findId.toString();
		str = "<tr  onmouseover=\"this.style.backgroundColor='#91c7cc';\"" +
			"onmouseout=\"this.style.backgroundColor='#d4e3e5';\">" +
			"<td id=\"" + findId0 + "\">" + data[i].goodsid +
			"</td><td id=\"" + findId1 + "\">" + data[i].adminid +
			"</td><td id=\"" + findId2 + "\">" + data[i].goodsname +
			"</td><td id=\"" + findId3 + "\">" + data[i].price +
			"</td><td id=\"" + findId4 + "\">" + data[i].picture +
			"</td><td id=\"" + findId5 + "\">" + data[i].goodsnumber +
			"</td><td>" + "<button class=\"work-button-goodstable\" id=\"" + i + "\" onclick=\"picture(this)\">图片</button>" +
			"<button class=\"work-button-goodstable\" id=\"" + i + "\" onclick=\"updateGoods(this)\">修改</button>" +
			"<button class=\"work-button-goodstable\" id=\"" + i + "\" onclick=\"deleteGoods(this)\">删除</button>" +
			"</td>";
		// 追加到table中
		$("#goodsTable").append(str);
	}
}
//删除商品函数

function deleteGoods(event) {
	layer.alert('', {
			icon: 2,
			title: '删除确认',
			content: '您确定要删除这条记录吗？',
			closeBtn: 1
		},
		function(index) {
			//business logic
			//获取后端数据allgoods
			var deleteGoods = new Object();
			var id = event.id;
			/*console.log(" event: "+event);
			console.log(" id: "+event.id);
			console.log(" idType: "+typeof(event.id));
			*/
			$.ajax({
				type: "POST",
				url: "../goodsRefresh",
				//data: newGoods,不传数据
				dataType: "json",
				//dataType:String,
				success: function(data) {
					//传过来的是list  json数据
					//showData(data);
					deleteGoods.goodsid = data[id].goodsid;
					//console.log("deleteGoods.goodsid: "+deleteGoods.goodsid);
					$.ajax({
						type: "POST",
						//提交数据写/deleteGoods，请求数据写../goodsRefresh
						url: "/deleteGoods",
						data: deleteGoods,
						dataType: "json",
						//dataType:String,
						success: function(data1) {
							//传过来的是list  json数据
							//console.log("data:" + data);
							//showData(data);
							if (data1.deleteCheck) {
								goodsRefresh();
								layer.msg('删除成功！', {
									icon: 6
								});
							} else {
								console.log("data.deleteCheck:" + data.deleteCheck);
								layer.msg('不能删除！', {
									icon: 5
								});
							}
						},
						error: function(data, type, err) {
							//删除有依赖的商品时报错到此
							layer.msg('不能删除!', {
								icon: 5
							});
						}
					});
				},
				error: function(data, type, err) {
					layer.msg('删除失败!', {
						icon: 5
					});
					//layer.msg('ajax连接失败外部！', {icon: 5});
				}
			});
			layer.close(index);

		});

}




