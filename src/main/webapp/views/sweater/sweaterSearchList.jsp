<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/header.jsp"%>

<div class="maincontent">
	<div class="maincontentinner">
		<div class="row-fluid" style="min-height:300px;">
			<section class="list">
				<form  method="post" action="${ctx}${searchurl}">
				<table class="list tablesorter" id="grid">
					<caption>
						<span class="text-vertical">${taskName}:<span
							class="number">${fn:length(list)}</span>条订单
						</span>
						<br>
						<tr>
							<td>
								<select type="text" class="span12" name="orderState" required="required" id="selectName">
								<option selected="selected" value="0">请选择</option>
								<option value="购买组织原料">未采购毛线</option>
								<option value="打小样">未制作工艺</option>
								<option value="制作工艺">未制版打样</option>
								<option value="制版打样">未确认样衣</option>
								<option value="外发">未外发</option>
								<option value="质量检测">未质量检测</option>
							    <option value="已完成">已完成</option>
								</select>
								<span style="display: none" id="orderState">${orderState}</span>
							</td>
							<td>&nbsp;</td>
							<td>
								<input class="btn btn-primary" type="submit" value="查询" style="float:right;">
							</td>
						</tr>
					</caption>
				</table>
				</form>
			</section>
			
			<div class="OrderListWrap" style="margin-top:20px;margin-left:25px;"> 
				<div class="OrderList">
					<ul id="itemContainer" style="list-style:none;">
						<c:forEach var="model" items="${list}" varStatus="status">
							<div class="orderWrap" style="width:25%; float:left; margin-bottom:20px;">
								<li>
									<div>
										<a href="${ctx}${url}?orderId=${model.order.orderId}&cid=${cid}" title="查看详情">
										<img src="${model.order.sampleClothesPicture}" title="查看详情" style="height:225px;width:225px" >
										</a>
									</div>
							
									<div>
										<table>
											<tbody>
												<tr>
												<td colspan="2" style="color: #CD0000;font-weight: bold; height: 42px; vertical-align:middle;">${model.order.styleName}</td>
												</tr>
												<tr>
													<td>订单号：</td>
													<td>${model.orderId}</td>
												</tr>
												<tr>
													<td>开始时间：</td>
													<td>${model.order.orderTime}</td>
												</tr>
												<tr>
													<td>订单状态：</td>
													<td><span style="color:red;" id="newName${status.index + 1}">${model.order.orderProcessStateName}</span></td>
												</tr>
												<tr>
													<td>市场专员：</td>
													<td>${model.employee.employeeName}</td>
												</tr>
												<tr>
													<td>客户姓名：</td>
													<td>${model.order.customerName}</td>
												</tr>
												<tr>
													<td>客户公司：</td>
													<td>${model.order.customerCompany}</td>
												</tr>
												<tr>
													<td>操作：</td>
													<td><a href="${ctx}${url}?orderId=${model.order.orderId}&cid=${cid}">查看详情</a></td>
												</tr>
											</tbody>
										</table>
									</div>
							
								</li>
							</div>
						</c:forEach>
					</ul>
					
				</div>
			</div>
		
		</div>
		
		<div class="holder"></div>
		
		<div class="footer">
			<div class="footer-left">
				<span>&copy; 2014. 江苏南通智造链有限公司.</span>
			</div>
		</div>
		<!--footer-->

	</div>
	<!--maincontentinner-->
</div>
<!--maincontent-->


<%@include file="/common/js_file.jsp"%>
<!-- 这里引入你需要的js文件 -->
<link rel="stylesheet" href="${ctx}/css/fmc/table.css">
<link rel="stylesheet" href="${ctx}/css/jPages.css"/>
<link rel="stylesheet" href="${ctx}/css/animate.css"/>
<script type="text/javascript" src="${ctx}/js/jPages.min.js"></script>
<script type="text/javascript" src="${ctx}/js/fmc/table.js"></script>
<script type="text/javascript" src="${ctx}/js/custom.js"></script>
<script>
$(function(){
	$("div.holder").jPages({
		containerID : "itemContainer",
      	previous : "上一页",
      	next : "下一页",
      	perPage : 8,
      	delay : 200
    });  
});
</script>
<script type="text/javascript">
// 详细控制
	var length = ${fn:length(list)+1};
	var oldName = null;
	var newName = null;
	for(var i=1;i<length;i++){
		oldName = $("#"+"newName"+i+"").text();
		if(oldName.length > 12){
			newName = oldName.substring(0,12)+"...";
		}else{
			newName = oldName;
		}
	    $("#"+"newName"+i+"").text(newName);
		$("#"+"newName"+i+"").attr("title",oldName);
	}
	
//下拉框默认选中
var orderState=$("#orderState").text();
$("#selectName option[value='"+orderState+"']").attr("selected",true);
</script>

<%@include file="/common/footer.jsp"%>

