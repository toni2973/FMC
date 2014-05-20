// JavaScript Document

(function($) {
	
	$(function(){
		//$.log("添加事件");
		$("table.fabric_table a").click(function(){
			var colName = ["fabric_name","fabric_amount","tear_per_meter","cost_per_meter"];
			table_addrow_onclick("fabric_table",colName,4);
		});

		$("table.accessory_table a").click(function(){
			var colName = ["accessory_name","accessory_query","tear_per_piece","cost_per_piece"];
			table_addrow_onclick("accessory_table",colName,4);
		});
		
		$("table.produce_table a").click(function(){
			var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
			if(checkNum("produce_table",colName,7)){
				table_addrow_onclick("produce_table",colName,7);
				$("input[name='ask_amount']").val(calculate("produce_table",colName,7));
			}
		});
		
		$("table.sample_produce_table a").click(function(){
			var colName = ["sample_produce_color","sample_produce_xs","sample_produce_s","sample_produce_m","sample_produce_l","sample_produce_xl","sample_produce_xxl"];
			table_addrow_onclick("sample_produce_table",colName,7);
		});
		
		$("table.version_table a").click(function(){
			var colName = ["version_size","version_centerBackLength","version_bust","version_waistLine","version_shoulder",
			               "version_buttock","version_hem","version_trousers","version_skirt","version_sleeves"];
			table_addrow_onclick("version_table",colName,10);
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

		$("input[name='is_need_sample_clothes']").change(function(){
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
		});

		init();
		
		
	});


	function table_addrow_onclick(table_name,col_name,col_sum){
	
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
				$("table."+table_name+" tr.addrow input").eq(j).val("");
				item+="<td class='"+col_name[j]+"'>"+content[j]+"</td>";
			}
			item+="<td><a onclick=\"deleteRow(this,'"+table_name+"')\">删除</a></td>";
			item="<tr>"+item+"</tr>";
			$("table."+table_name+" tr.addrow").after(item);
			
	}


	
})(jQuery);

function checkNum(table_name,col_name,col_sum){
	for(var i=0;i<col_sum;i++){
		var col=$("table."+table_name+" tr.addrow input").eq(i).val();
		if(isNaN(parseInt(col))){
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

function deleteRow(a,table){
	//alert($(a).parents('.'+table+' tr').length);
	$(a).parents('.'+table+' tr').remove();
	if(table=="produce_table"){
		var colName = ["produce_color","produce_xs","produce_s","produce_m","produce_l","produce_xl","produce_xxl"];
		$("input[name='ask_amount']").val(calculate("produce_table",colName,7));
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
	
	
	$("#fabric_name").val(getTdString("fabric_name"));
	$("#fabric_amount").val(getTdString("fabric_amount"));
	$("#tear_per_meter").val(getTdString("tear_per_meter"));
	$("#cost_per_meter").val(getTdString("cost_per_meter"));
	$("#accessory_name").val(getTdString("accessory_name"));
	$("#accessory_query").val(getTdString("accessory_query"));
	$("#tear_per_piece").val(getTdString("tear_per_piece"));
	$("#cost_per_piece").val(getTdString("cost_per_piece"));
	
	
	
	
	var FabricName=jQuery("input[name='fabric_name']").val();
	var FabricAmount=jQuery("input[name='fabric_amount']").val();
	var TearPerMeter=jQuery("input[name='tear_per_meter']").val();
	var CostPerMeter=jQuery("input[name='cost_per_meter']").val();
	var AccessoryName=jQuery("input[name='accessory_name']").val();
	var AccessoryQuery=jQuery("input[name='accessory_query']").val();
	var TearPerPiece=jQuery("input[name='tear_per_piece']").val();
	var CostPerPiece=jQuery("input[name='cost_per_piece']").val();
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
 if((FabricName== "")||(FabricAmount== "")||(TearPerMeter== "")||(CostPerMeter== "")||(AccessoryName== "")
		 ||(AccessoryQuery== "")||(TearPerPiece== "")||(CostPerPiece== "")){
	 
	
	  return false;
	
 }
	
 
 else{
	 
	 return true;
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
	
	if($('input:radio[name="is_need_sample_clothes"]:checked').val()=="0"){
		$("input[name='sample_clothes_name']").attr("disabled","disabled");
		$("input[name='sample_clothes_phone']").attr("disabled","disabled");
		$("input[name='sample_clothes_address']").attr("disabled","disabled");
		$("input[name='sample_clothes_remark']").attr("disabled","disabled");
	}
}