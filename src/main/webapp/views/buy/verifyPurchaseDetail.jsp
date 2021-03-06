<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/header.jsp"%>

<div class="maincontent">
	<div class="maincontentinner">
		<div class="row-fluid" style="min-height:300px;">
			<!--  如果是其它页面，这里是填充具体的内容。 -->
			<ul class="nav nav-tabs detail" id="tab">
				<li class="task-name">采购验证</li>
				<li><a href="#cad" data-toggle="tab">版型信息</a></li>
				<li><a href="#produce" data-toggle="tab">加工信息</a></li>
				<li><a href="#sample" data-toggle="tab">样衣信息</a></li>
				<li><a href="#material" data-toggle="tab">面辅信息</a></li>
				<li class="active"><a href="#basic" data-toggle="tab">基本信息</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<%@include file="/views/common/basic.jsp"%>
				</div>
				<div class="tab-pane" id="material">
					<%@include file="/views/common/material.jsp"%>
				</div>
				<div class="tab-pane" id="sample">
					<%@include file="/views/common/sample.jsp"%>
				</div>
				<div class="tab-pane" id="produce">
					<%@include file="/views/common/produce.jsp"%>
				</div>
				<div class="tab-pane" id="cad">
					<%@include file="/views/common/cad.jsp"%>
				</div>
			</div>
			
			
			<div>
				<form id="verify_form" method="post" class="verify"
					action="${ctx }/buy/verifyPurchaseSubmit.do">
					<table
						class="table table-striped table-bordered table-hover detail">
						<tr>
							<td class="span2">意见</td>
							<td colspan="6"><textarea class="span12"
									style="resize:vertical" rows="5" name="suggestion"></textarea></td>
						</tr>
						<tr>
							<td>操作</td>
							<td colspan="6"><a id="agree"
								class="btn btn-primary btn-rounded"><i
									class="icon-ok icon-white"></i> 同意</a> <a id="disagree"
								class="btn btn-danger btn-rounded"><i
									class="icon-remove icon-white"></i> 拒绝</a></td>
						</tr>
					</table>
					<input type="hidden" name="orderId"
						value="${orderInfo.order.orderId }" /> <input type="hidden"
						name="taskId" value="${orderInfo.task.id }" />
						 <input
						id="verify_val" type="hidden" name="result" value="false" />
				</form>
			</div>
		</div>
							<button class="btn btn-primary" onclick="history.back();">返回</button>
							



	</div>


	<div class="footer">
		<div class="footer-left">
			<span>&copy; 2014. 江苏南通智造链有限公司.</span>
		</div>
	</div>


</div>
</div>




<%@include file="/common/js_file.jsp"%>
<%@include file="/common/js_form_file.jsp"%>
<link rel="stylesheet" href="${ctx}/css/fmc/table.css">
<script type="text/javascript" src="${ctx}/js/fmc/table.js"></script>
<link rel="stylesheet" href="${ctx}/views/common/verify.css">
<script type="text/javascript" src="${ctx}/views/common/verify.js"></script>
<link rel="stylesheet" href="${ctx}/css/fmc/detail.css">
<script type="text/javascript" src="${ctx}/js/order/add_order.js"></script>
<script type="text/javascript" src="${ctx }/js/custom.js"></script>
<%@include file="/common/footer.jsp"%>
