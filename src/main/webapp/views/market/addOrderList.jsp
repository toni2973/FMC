
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/header.jsp"%>


<div class="maincontent">
	<div class="maincontentinner">
		<div class="row-fluid" style="min-height:300px;">
			<!--  如果是其它页面，这里是填充具体的内容。 -->
			<section class="list">
				<table id="dyntable" class="list">
					<caption>
						<span class="text-vertical">客户列表:<span class="number">${fn:length(customers)}</span>个客户
						</span><input type="text" class="search-query float-right"
							placeholder="输入检索条件">
					</caption>
					<thead>
						<tr>
							<th>客户姓名</th>
							<th>客户电话</th>
							<th>公司名称</th>
							<th>公司地址</th>
							<th>公司电话</th>
							<th style="width: 80px;">操作</th>
						</tr>
					</thead>
					<c:forEach var="customer" items="${customers}">
						<tr class="gradeA">
							<td>${customer.customerName }</td>
							<td>${customer.customerPhone }</td>
							<td>${customer.companyName }</td>
							<td>${customer.companyAddress }</td>
							<td>${customer.companyPhone }</td>
							<td><a
								href="${ctx}/market/addOrderDetail.do?cid=${customer.customerId}">下单</a>
								<br>
								<a
								href="${ctx}/market/addMoreOrderList.do?cid=${customer.customerId}">下翻单</a></td>
						</tr>
					</c:forEach>
				</table>
			</section>

		</div>
		<!--row-fluid-->
	</div>
	<!--maincontentinner-->
</div>
<!--maincontent-->


<%@include file="/common/js_file.jsp"%>
<!-- 这里引入你需要的js文件 -->
<link rel="stylesheet" href="${ctx}/css/fmc/table.css">
<script type="text/javascript" src="${ctx}/js/fmc/table.js"></script>
<script type="text/javascript" src="${ctx }/js/custom.js"></script>
<style type="text/css">
.table th,.table td {
	color: #000;
	margin: 0;
	padding: 8px 20px;
	text-align: center;
}

.table tr {
	background: #E6E6E6;
	border-bottom: 1px solid #B5B5B5;
	border-radius: 4px;
}

.table tr:nth-child(even) {
	background: #F1F1F1;
}

.table caption {
	margin: 0;
	padding: 8px 20px;
	border-bottom: 1px solid #B5B5B5;
	color: #FFF;
	text-align: left;
	background: #003366;
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
}

.table th:first-child,.table td:first-child {
	text-align: left;
}

.table tr:last-child td {
	border-bottom-left-radius: 4px;
	border-bottom-right-radius: 4px;
}

section {
	box-shadow: 4px 4px 2px #919191;
	border-radius: 4px;
	border: 1px solid #000;
}

.button {
	border-radius: 4px;
}

.button:hover {
	text-decoration: none;
}

.table {
	border-collapse: separate;
	border-radius: 4px;
}

.table th {
	background: #CCCCCC;
}

.table tr:hover td {
	background: #FFFFCC;
}
</style>
<%@include file="/common/footer.jsp"%>
