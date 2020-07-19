ordersRefresh();
//商品管理跳转
function goodsWork(){
	window.location.href = 'goodsWork.html';
}
//修改订单状态为已经处理
function ordersRight(event){
	var id = event.id.toString();
	//存读到的数据
	var findId0 = id+"-"+"0";
	var findId4 = id+"-"+"4";
	var ordersOld = new Object();
	//读取表格中的数据
	ordersOld.ordersid = document.getElementById(findId0).innerHTML;
	ordersOld.orderstate = document.getElementById(findId4).innerHTML;
	if(ordersOld.orderstate=="未处理未付款未收货"){
		layer.msg('用户还未付款，不能处理!', {icon: 5});
	}else if(ordersOld.orderstate=="未处理已付款未收货"){
		ordersOld.orderstate = "已处理已付款未收货";
		//提交数据
		$.ajax({
			type: "POST",
			url: "/ordersRight",
			data: ordersOld,
			dataType: "json",
			success: function(data) {
				//传过来的是list  json数据
				//console.log("data:" + data);
				if(data.rightCheck){
					ordersRefresh();
					layer.msg('处理成功!', {icon: 6});
				}
				
			},
			error: function(data, type, err) {
				layer.msg('处理失败!', {icon: 5});
			}
		});
	}else{
		layer.msg('订单无需处理!', {icon: 5});
	}
	
}

//取消订单处理
function ordersWrong(event){
	var id = event.id.toString();
	//存读到的数据
	var findId0 = id+"-"+"0";
	var findId4 = id+"-"+"4";
	var ordersOld = new Object();
	//读取表格中的数据
	ordersOld.ordersid = document.getElementById(findId0).innerHTML;
	ordersOld.orderstate = document.getElementById(findId4).innerHTML;
	if(ordersOld.orderstate=="未处理已付款未收货"){
		layer.msg('还未处理，不能取消!', {icon: 5});
	}else if(ordersOld.orderstate=="已处理已付款未收货"){
		ordersOld.orderstate = "未处理已付款未收货";
		//提交数据
		$.ajax({
			type: "POST",
			url: "/ordersRight",
			data: ordersOld,
			dataType: "json",
			success: function(data) {
				//传过来的是list  json数据
				//console.log("data:" + data);
				if(data.rightCheck){
					ordersRefresh();
					layer.msg('取消成功!', {icon: 6});
				}
				
			},
			error: function(data, type, err) {
				layer.msg('取消失败!', {icon: 5});
			}
		});
	}else{
		layer.msg('当前订单不能取消处理!', {icon: 5});
	}
	
}
/*订单刷新*/
function ordersRefresh(){
	//提交刷新请求
	$.ajax({
		type: "POST",
		url: "../ordersRefresh",
		//data: newGoods,不传数据
		dataType: "json",
		//dataType:String,
		success: function(data) {
			//传过来的是list  json数据
			//console.log("data:" + data);
			showOrdersData(data);
		},
		error: function(data, type, err) {
			layer.msg('刷新失败!', {icon: 5});
		}
	});
}

//数据展示函数
function showOrdersData(data){
	//清空原来数据
	var tb = document.getElementById('ordersTable');
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
      		+"<td id=\""+findId0+"\">"+data[i].ordersid
      		+"</td><td>"+data[i].userid
      		+"</td><td>"+data[i].goodsid
      		+"</td><td>"+data[i].orderdate
      		+"</td><td id=\""+findId4+"\">"+data[i].orderstate
      		+ "</td><td>"+"<button class=\"work-button-orderstable\" id=\""
			+i+"\" onclick=\"ordersRight(this)\">处理</button>"
			+"<button class=\"work-button-orderstable\" id=\""
			+i+"\" onclick=\"ordersWrong(this)\">取消</button>"
      		+"</td>";
      // 追加到table中
      $("#ordersTable").append(str);         
   }
}