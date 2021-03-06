// JavaScript Document

(function($) {
	
	$(function(){
		//$.log("添加事件");
		$("table.fabric_table a").click(function(){
			var colName = ["fabric_name","fabric_amount"];
			table_addrow_onclick("fabric_table",colName,2);
		});
		$("table.fabric_cost_table a").click(function(){
			var colName = ["fabric_cost_name","cost_per_meter","tearpermeter"];
			table_addrow_onclick2("fabric_cost_table",colName,3);
		});		
		$("table.accessory_table a").click(function(){
			var colName = ["accessory_name","accessory_query"];
			table_addrow_onclick("accessory_table",colName,2);
		});
		$("table.accessory_cost_table a").click(function(){
			var colName = ["accessory_cost_name","costperpiece","tearperpiece"];
			table_addrow_onclick2("accessory_cost_table",colName,3);
		});	
		//大货加工
		$("table.produce_table a").click(function(){
			var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
			if(checkNum("produce_table",colName,7)){
				table_addrow_onclick("produce_table",colName,7);
				$("input[name='ask_amount']").val(calculate("produce_table",colName,7));
				for(var i=0;i<$("td.ask_amount").length;i++){
					$("td.ask_amount").eq(i).text(calculate("produce_table",colName,7));
				}
			}
		});
		//样衣加工
		$("table.sample_produce_table a").click(function(){
			var colName = ["sample_produce_color","sample_produce_xs","sample_produce_s","sample_produce_m","sample_produce_l","sample_produce_xl","sample_produce_xxl"];
			if(checkNum("sample_produce_table",colName,7)){
				table_addrow_onclick("sample_produce_table",colName,7);
				$("input[name='sample_amount']").val(calculate("produce_table",colName,7));
			}
		});
		//大货加工确认
		$("table.confirm_produce_table a").click(function(){
			var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
			if(checkNum("confirm_produce_table",colName,7)){
				table_addrow_onclick("confirm_produce_table",colName,7);
				$("input[name='ask_amount']").val(calculate("confirm_produce_table",colName,7));
				for(var i=0;i<$("td.ask_amount").length;i++){
					$("td.ask_amount").eq(i).text(calculate("confirm_produce_table",colName,7));
				}
			}
		});
		$("table.version_table a").click(function(){
			var colName = ["version_size","version_centerBackLength","version_bust","version_waistLine","version_shoulder",
			               "version_buttock","version_hem","version_trousers","version_skirt","version_sleeves"];
			if(checkFloat("version_table",colName,10)){
				table_addrow_onclick("version_table",colName,10);
			}
		});
		
		$("#sample_clothes_picture_button").click(function(){
			$("input[name='sample_clothes_picture']").click();
		});
		
		
		$("#reference_picture_button").click(function(){
			$("input[name='reference_picture']").click();
		});
		
		$("input[name='has_posted_sample_clothes']").change(function(){
			if($('input:radio[name="has_posted_sample_clothes"]:checked').val()=="0"){
				$("input[name='in_post_sample_clothes_time']").attr("disabled","disabled");
				$("select[name='in_post_sample_clothes_type']").attr("disabled","disabled");
				$("input[name='in_post_sample_clothes_number']").attr("disabled","disabled");
			}else{
				$("input[name='in_post_sample_clothes_time']").removeAttr("disabled");
				$("select[name='in_post_sample_clothes_type']").removeAttr("disabled");
				$("input[name='in_post_sample_clothes_number']").removeAttr("disabled");
			}
		});

		$("input[name='is_haoduoyi']").change(function(){
			if($('input:radio[name="is_haoduoyi"]:checked').val()=="1"){
				$("input[name='order_source']").val("");
				$("input[name='order_source']").attr("disabled","disabled");
				//好多衣用户默认选中生产样衣
				$("#is_need_sample_clothes").css("display","none");
				$("#is_need_sample_clothes_tip").removeAttr("style");
				$("#is_need_sample_clothes").html("<input type='radio' name='is_need_sample_clothes' checked='checked' value='1' required='required' onchange = 'change()'/> 是 <input type='radio' name='is_need_sample_clothes' value='0'  onchange ='change()'/> 否");
				change();
			}else{
				$("input[name='order_source']").removeAttr("disabled");
				$("#is_need_sample_clothes").removeAttr("style");
				$("#is_need_sample_clothes_tip").css("display","none");
				change();
 			}
		});
		
		$("input[name='ask_deliver_date']").change(function(){ 
			var date = new Date();
			var input_date = new Date($("input[name='ask_deliver_date']").val());
			if(input_date.valueOf()<date.valueOf()){
				alert("输入日期不能在今天之前");
				$("input[name='ask_deliver_date']").val("");
				return;
			}
			var max = parseInt(Math.abs((input_date - date)/86400000));
			$("input[name='ask_produce_period']").attr("max",""+parseInt(max));
		});
		
		$("input[id='input_day']").change(function(){ 
			var date = new Date();
			var input_date = new Date($("input[id='input_day']").val());
			if((input_date.valueOf()-28800000)>date.valueOf()){
				alert("输入日期不能在今天之后");
				$("input[id='input_day']").val("");
				return;
			}
		});
		
		$("input[name='in_post_sample_clothes_time']").change(function(){ 
			var date = new Date();
			var input_date = new Date($("input[name='in_post_sample_clothes_time']").val());
			if(input_date.valueOf()>date.valueOf()){
				alert("输入日期不能在今天之后");
				$("input[name='in_post_sample_clothes_time']").val("");
				return;
			}

		});
		/*$("input[name='is_need_sample_clothes']").change(function(){
			if($('input:radio[name="is_need_sample_clothes"]:checked').val()=="0"){
				$("input[name='sample_clothes_name']").attr("disabled","disabled");
				$("input[name='sample_clothes_phone']").attr("disabled","disabled");
				$("input[name='sample_clothes_address']").attr("disabled","disabled");
				$("input[name='sample_clothes_remark']").attr("disabled","disabled");
			}else{
				$("input[name='sample_clothes_name']").removeAttr("disabled");
				$("input[name='sample_clothes_phone']").removeAttr("disabled");
				$("input[name='sample_clothes_address']").removeAttr("disabled");
				$("input[name='sample_clothes_remark']").removeAttr("disabled");
			}
		});*/

		init();
		
		
	});

	function table_addrow_onclick(table_name,col_name,col_sum){	
		var content = new Array();
		for (var i = 0; i < col_sum; i++) {
			var col = $("table." + table_name + " tr.addrow input").eq(i).val();
			content[i] = col;
			if (col == "") {
				alert("请把信息填写完整");
				return;
			}
		}

		var item = "";
		//如果是添加大货和样衣数量，需要设置默认值0
		if (table_name == "produce_table" || table_name == "sample_produce_table" || table_name == "confirm_produce_table") {
			for (var j = 0; j < col_sum; j++) {
				//第1列是颜色，不需要置为0
				if(j == 0){
					$("table." + table_name + " tr.addrow input").eq(j).val("");
				}else{
					$("table." + table_name + " tr.addrow input").eq(j).val("0");
				}
				item += "<td class='" + col_name[j] + "'>" + content[j] + "</td>";
			}
		}else{
			for (var j = 0; j < col_sum; j++) {
				$("table." + table_name + " tr.addrow input").eq(j).val("");
				item += "<td class='" + col_name[j] + "'>" + content[j] + "</td>";
			}
		}

		item += "<td><a name='delete' onclick=\"deleteRow(this,'" + table_name + "')\">删除</a></td>";
		item = "<tr>" + item + "</tr>";
		$("table." + table_name + " tr.addrow").after(item);
		
		//如果是大货生产确认，还需要重新计算总金额
		if (table_name == "confirm_produce_table"){
			
//			var addAmount = 0;
//			for (var k = 1; k < col_sum; k ++) {
//				addAmount += parseInt(content[k]);
//			}
			
			calTotalMoney();
			calDiscountMoney();
		}
	}
	
	function table_addrow_onclick2(table_name,col_name,col_sum){
		
		var content = new Array();
		for(var i=0;i<col_sum;i++){
			var col=$("table."+table_name+" tr.addrow input").eq(i).val();
			content[i] = col;
			if(col==""){
				alert("请把信息填写完整");
				return;
			}
		}


		var item = "";
		for(var j=0;j<col_sum;j++){
			if(j==0){
				$("table."+table_name+" tr.addrow input").eq(j).val("");
				item+="<td class='"+col_name[j]+"'>"+
				"<input class=\"span12\" name=  \""+col_name[j]+"\" type=\"text\" value="+content[j]+ " required=\"required\"  readonly=\"readonly\"/>"+"</td>";

			}else{
				$("table."+table_name+" tr.addrow input").eq(j).val("");
				item+="<td class='"+col_name[j]+"'>"+
				"<input class=\"span12\" name= \""+col_name[j]+"\" type=\"text\" value="+content[j]+ " required=\"required\" />"+"</td>";

			}
			
/*			
			$("table."+table_name+" tr.addrow input").eq(j).val("");
			item+="<td class='"+col_name[j]+"'>"+
			"<input class=\"span12\" name="+col_name[j]+" type=\"text\" value="+content[j]+ " required=\"required\"  readonly=\"readonly\"/>"+"</td>";
			*/
		}
		item+="<td><a onclick=\"deleteRow(this,'"+table_name+"')\">删除</a></td>";
		item="<tr>"+item+"</tr>";
		$("table."+table_name+" tr.addrow").after(item);
		
}
	
})(jQuery);

function checkNum(table_name, col_name, col_sum) {
	var totalNum = 0;
	for (var i = 0; i < col_sum; i++) {
		var col = $("table." + table_name + " tr.addrow input").eq(i).val();
		
		if (isNaN(parseInt(col))) {
			if (i > 0) {
				alert("请正确填写数量");
				return false;
			}
		} else {
			if (i > 0) {
				totalNum += parseInt(col);
			}
		}
	}
	
	if (totalNum > 0){
		return true;
	}else{
		alert("总数必须大于0，请重新填写");
		return false;
	}
}

function checkFloat(table_name,col_name,col_sum){
	for(var i=0;i<col_sum;i++){
		var col=$("table."+table_name+" tr.addrow input").eq(i).val();
		if(isNaN(parseFloat(col))){
			if(i>0){
				alert("请正确填写数量");
				return false;
			}
		}
	}
	return true;
}

function calculate(table_name,col_name,col_sum){
	var sum = 0;
	for(var i = 1; i < col_sum; i++){
		for(var j = 0; j < $("td."+col_name[i]).length; j++){
			sum += parseInt($("td."+col_name[i]).eq(j).text());
		}
	}
	return sum;
}

function calTotalMoney(){
	var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
	var askAmount = calculate("produce_table",colName,7);
	
	var orderInfoQuoteOuterPrice = $("input[name='orderInfoQuoteOuterPrice']").val();
//	var orderInfoOrderAskAmount = $("input[name='orderInfoOrderAskAmount']").val();
//	var newAskAmount = parseInt(orderInfoOrderAskAmount) + addAmount;
//	$("input[name='orderInfoOrderAskAmount']").val(newAskAmount);
	
	var totalMoney = orderInfoQuoteOuterPrice * askAmount;
	$("input[name='totalmoney']").val(totalMoney.toFixed(2));
	$("input[name='sum']").val(totalMoney.toFixed(2));
}

function deleteRow(a,table){
	//alert($(a).parents('.'+table+' tr').length);
	$(a).parents('.'+table+' tr').remove();
	if(table=="produce_table" || table=="confirm_produce_table"){
		var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
		var askAmount = calculate("produce_table",colName,7);
		$("input[name='ask_amount']").val(askAmount);
		$("td.ask_amount").text(askAmount);
		//如果是大货生产确认，还需要重新计算总金额
		if (table == "confirm_produce_table"){
			calTotalMoney();
			calDiscountMoney();
		}
	}
	
	if(table=="sample_produce_table"){
		var colName = ["sample_produce_color","sample_produce_xs","sample_produce_s","sample_produce_m","sample_produce_l","sample_produce_xl","sample_produce_xxl"];
		var askAmount = calculate("sample_produce_table",colName,7);
		$("input[name='sample_amount']").val(askAmount);
		$("td.sample_amount").text(askAmount);
	}
}


function getTdString(col){
	var tdString="";
	var i=0;
	for(;i<$("td."+col).length-1;i++){
		tdString+=$("td."+col).eq(i).text()+",";
	}
	tdString+=$("td."+col).eq(i).text();
	return tdString;
}


function verify(){
	var ask_amount = jQuery("input[name='ask_amount']").val();
	if(ask_amount == "" || ask_amount <= 0){
		alert("大货总数必须大于0");
		return false;
	}
	
	var is_need_sample = jQuery("input[name='is_need_sample_clothes']:checked").val();
	//如果需要样衣
	if(is_need_sample == 1){
		var sample_amount = jQuery("input[name='sample_amount']").val();
		if(sample_amount == "" || sample_amount <= 0){
			alert("请添加样衣数量");
			return false;
		}
	}
	
	var is_haoduoyi = jQuery("input[name='is_haoduoyi']:checked").val();
	if(is_haoduoyi==0){
		var order_source_name = jQuery("input[name='order_source']").val();
		if(order_source_name=="好多衣"){
			alert("是否好多衣选择为否时，订单来源名称不能用好多衣");
			return false;
		}
		if(order_source_name==""){
			alert("请填写订单来源信息");
			return false;
		}
	}
	
	var is_haoduoyi = jQuery("input[name='ishaoduoyi']").val();
	if(is_haoduoyi==0){
		var order_source_name = jQuery("input[name='order_source']").val();
		if(order_source_name=="好多衣"){
			alert("是否好多衣选择为否时，订单来源名称不能用好多衣");
			return false;
		}
		if(order_source_name==""){
			alert("请填写订单来源信息");
			return false;
		}
	}
	
	
	$("#fabric_name").val(getTdString("fabric_name"));
	$("#fabric_amount").val(getTdString("fabric_amount"));
	$("#accessory_name").val(getTdString("accessory_name"));
	$("#accessory_query").val(getTdString("accessory_query"));
	$("#produce_color").val(getTdString("produce_color"));
	$("#produce_xs").val(getTdString("produce_xs"));
	$("#produce_s").val(getTdString("produce_s"));
	$("#produce_m").val(getTdString("produce_m"));
	$("#produce_l").val(getTdString("produce_l"));
	$("#produce_xl").val(getTdString("produce_xl"));
	$("#produce_xxl").val(getTdString("produce_xxl"));
	$("#sample_produce_color").val(getTdString("sample_produce_color"));
	$("#sample_produce_xs").val(getTdString("sample_produce_xs"));
	$("#sample_produce_s").val(getTdString("sample_produce_s"));
	$("#sample_produce_m").val(getTdString("sample_produce_m"));
	$("#sample_produce_l").val(getTdString("sample_produce_l"));
	$("#sample_produce_xl").val(getTdString("sample_produce_xl"));
	$("#sample_produce_xxl").val(getTdString("sample_produce_xxl"));
	$("#version_size").val(getTdString("version_size"));
	$("#version_centerBackLength").val(getTdString("version_centerBackLength"));
	$("#version_bust").val(getTdString("version_bust"));
	$("#version_waistLine").val(getTdString("version_waistLine"));
	$("#version_shoulder").val(getTdString("version_shoulder"));
	$("#version_buttock").val(getTdString("version_buttock"));
	$("#version_hem").val(getTdString("version_hem"));
	$("#version_trousers").val(getTdString("version_trousers"));
	$("#version_skirt").val(getTdString("version_skirt"));
	$("#version_sleeves").val(getTdString("version_sleeves"));
	
/*	var employee_id=jQuery("input[name='employee_id']").val();
	var order_source=jQuery("input[name='order_source']").val();
	var customer_id=jQuery("input[name='customer_id']").val();
	var style_name=jQuery("input[name='style_name']").val();
	var ask_amount=jQuery("input[name='ask_amount']").val();
	var ask_deliver_date=jQuery("input[name='ask_deliver_date']").val();
	var ask_produce_period=jQuery("input[name='ask_produce_period']").val();
	var hint="employee_id:"+employee_id+"\n";
	hint+="order_source:"+order_source+"\n";
	hint+="customer_id:"+customer_id+"\n";
	hint+="style_name:"+style_name+"\n";
	hint+="ask_amount:"+ask_amount+"\n";
	hint+="ask_deliver_date:"+ask_deliver_date+"\n";
	hint+="ask_produce_period:"+ask_produce_period+"\n";
	alert(hint);*/

	$("button.norepeat").attr("disabled","disabled");
	return true;
}

function change(){
	
	var is_need_sample = jQuery("input[name='is_need_sample_clothes']:checked").val();
	if(is_need_sample == 0){
		$("#sampletable *").attr("disabled","disabled");
		//$('a[name="delete"]').trigger("click");
		$('#sampletable a[name="delete"]').trigger("click");
		//$("#sample_clothes_picture").attr("disabled","disabled");
	}else{
		$("#sampletable *").removeAttr("disabled");
		//$("#sample_clothes_picture").removeAttr("disabled");
	}
}

function init(){
//	$("#fabric_name").val("name1,name2,name3");
//	$("#fabric_amount").val("11,12,13");
//	$("#accessory_name").val("name1,name2,name3");
//	$("#accessory_query").val("name1,name2,name3");
//	$("#produce_color").val("红,黄,绿");
//	$("#produce_xs").val("1,2,3");
//	$("#produce_s").val("1,2,3");
//	$("#produce_m").val("1,2,3");
//	$("#produce_l").val("1,2,3");
//	$("#produce_xl").val("1,2,3");
//	$("#produce_xxl").val("1,2,3");
//	$("#sample_produce_color").val("红,黄,绿");
//	$("#sample_produce_xs").val("1,1,2");
//	$("#sample_produce_s").val("1,1,2");
//	$("#sample_produce_m").val("1,1,2");
//	$("#sample_produce_l").val("1,1,2");
//	$("#sample_produce_xl").val("1,1,2");
//	$("#sample_produce_xxl").val("1,1,2");
//	$("#version_size").val("21,22,23");
//	$("#version_centerBackLength").val("21,22,23");
//	$("#version_bust").val("21,22,23");
//	$("#version_waistLine").val("21,22,23");
//	$("#version_shoulder").val("21,22,23");
//	$("#version_buttock").val("21,22,23");
//	$("#version_hem").val("21,22,23");
//	$("#version_trousers").val("21,22,23");
//	$("#version_skirt").val("21,22,23");
//	$("#version_sleeves").val("21,22,23");
//	
//	$("input[name='tear_per_meter']").val("1");
//	$("input[name='cost_per_meter']").val("2");
//	$("input[name='fabric_price']").val("3");
//	
//	$("input[name='tear_per_piece']").val("4");
//	$("input[name='cost_per_piece']").val("5");
//	$("input[name='accessory_price']").val("6");
//	
//	
//	$("input[name='cut_cost']").val("0");
//	$("input[name='manage_cost']").val("0");
//	$("input[name='nail_cost']").val("0");
//	$("input[name='ironing_cost']").val("0");
//	
//	
//	$("input[name='swing_cost']").val("0");
//	$("input[name='package_cost']").val("0");
//	$("input[name='other_cost']").val("0");
//	$("input[name='design_cost']").val("0");
//	
//	
//	
//	$("input[name='style_name']").val("");
//	$("input[name='order_source']").val("");
//	$("input[name='ask_amount']").val("20");
//	$("input[name='ask_produce_period']").val("10");
//	$("input[name='ask_deliver_date']").val("");
//
//	
//	
//	
//	$("input[name='in_post_sample_clothes_time']").val();
//	$("input[name='in_post_sample_clothes_type']").val("顺丰");
//	$("input[name='in_post_sample_clothes_number']").val("");
//	
//	$("input[name='sample_clothes_time']").val("");
//	$("input[name='sample_clothes_type']").val("申通");
//	$("input[name='sample_clothes_number']").val("");
//	$("input[name='sample_clothes_name']").val("");
//	$("input[name='sample_clothes_phone']").val("");
//	$("input[name='sample_clothes_address']").val("");
//	$("input[name='sample_clothes_remark']").val("");
//	
	if($('input:radio[name="has_posted_sample_clothes"]:checked').val()=="0"){
		$("input[name='in_post_sample_clothes_time']").attr("disabled","disabled");
		$("select[name='in_post_sample_clothes_type']").attr("disabled","disabled");
		$("input[name='in_post_sample_clothes_number']").attr("disabled","disabled");
	}
	$("input[name='is_haoduoyi']").change(function(){
		if($('input:radio[name="is_haoduoyi"]:checked').val()=="1"){
			$("input[name='order_source']").attr("disabled","disabled");
		}
	});
	
	if($("input[name='is_need_sample_clothes']:checked").val()=="0"){
		$(".sample_produce_table *").attr("disabled","disabled");
	}
	if($('input[name="order_source"]').val() =="好多衣"){
		$('input[name="order_source"]').attr("readonly","readonly");
	}
	
	/*if($('input:radio[name="is_need_sample_clothes"]:checked').val()=="0"){
		$("input[name='sample_clothes_name']").attr("disabled","disabled");
		$("input[name='sample_clothes_phone']").attr("disabled","disabled");
		$("input[name='sample_clothes_address']").attr("disabled","disabled");
		$("input[name='sample_clothes_remark']").attr("disabled","disabled");
	}*/
	var date = new Date();
	var month = date.getMonth()>8?date.getMonth()+1:"0"+(date.getMonth()+1);
	var day = date.getDate()>9?date.getDate():"0"+date.getDate();
	/*if(date.getMonth() < 10){
		month = "0"+(date.getMonth()+1);
	}else{
		month = date.getMonth()+1;
	}
	if(date.getDate() < 10){
		day = "0"+date.getDate();
	}else{
		day = date.getDate();
	}*/
	var hour = date.getHours()>9?date.getHours():"0"+date.getHours();
	var minute = date.getMinutes()>9?date.getMinutes():"0"+date.getMinutes();
	var second = date.getSeconds()>9?date.getSeconds():"0"+date.getSeconds();
	$("#input_day").val(date.getFullYear()+"/"+month+"/"+day+" "+hour+":"+minute+":"+second);
	//$("input[name='in_post_sample_clothes_time']").val(date.getFullYear()+"/"+month+"/"+day+" "+hour+":"+minute+":"+second);
	//$("#input_day").attr("readonly","readonly");
	//$("input[name='in_post_sample_clothes_time']").attr("readonly","readonly");
}