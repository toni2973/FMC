<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table class="table table-striped table-bordered table-hover detail">
	<tr>
		<td class="span2 title" rowspan="2">加工信息</td>
		<td class="span1 title" colspan="2">样衣总数</td>
		<td class="span1 title" colspan="2">大货总数</td>
		<td class="span1 title" colspan="2">最迟交货时间</td>
		<td class="span1 title" colspan="2">完工时间（天）</td>
	</tr>
	<tr>
		<td class="span1" colspan="2">${orderInfo.order.sampleAmount}</td>
		<td class="span1 ask_amount" colspan="2">${orderInfo.order.askAmount}</td>
		<td class="span1" colspan="2">${fn:substring(orderInfo.order.askDeliverDate,0,10)}</td>
		<td class="span1" colspan="2">${orderInfo.order.askProducePeriod}</td>
	</tr>
	<c:if test="${orderInfo.order.reorder==0 }">	
		<tr>
			<td class="span2 title" rowspan="${fn:length(orderInfo.sample)+1}">样衣加工</td>
			<td class="span1 title">颜色</td>
			<td class="span1 title">XS</td>
			<td class="span1 title">S</td>
			<td class="span1 title">M</td>
			<td class="span1 title">L</td>
			<td class="span1 title">XL</td>
			<td class="span1 title">XXL</td>
			<td class="span1 title">总计</td>
		</tr>
		<c:forEach var="sample" items="${orderInfo.sample}">
			<tr>
				<td>${sample.color}</td>
				<td>${sample.xs}</td>
				<td>${sample.s}</td>
				<td>${sample.m}</td>
				<td>${sample.l}</td>
				<td>${sample.xl}</td>
				<td>${sample.xxl}</td>
				<td>${sample.produceAmount}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<table class="table table-striped table-bordered table-hover detail">
	<tr>
		<td class="span2 title">大货加工 </td>
		<td colspan="8" class="innertable">
			<table class="span12 table table-bordered table-hover confirm_produce_table">
				<tr>
					<td class="span1 title">颜色</td>
					<td class="span1 title">XS</td>
					<td class="span1 title">S</td>
					<td class="span1 title">M</td>
					<td class="span1 title">L</td>
					<td class="span1 title">XL</td>
					<td class="span1 title">XXL</td>
					<td class="span1 title">操作</td>
				</tr>
				<tr class="addrow">
					<td><input type="text" class="span12" /></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><input type="text" class="span12"  value="0"/></td>
					<td><a>添加</a><span class="required">（点击添加之后数据生效）</span></td>
				</tr>
				<c:forEach var="produceRow" items="${orderInfo.produce}">
					<tr>
						<td class='span12 produce_color'>${produceRow.color }</td>
						<td class='span12 produce_xs'>${produceRow.xs }</td>
						<td class='span12 produce_s'>${produceRow.s }</td>
						<td class='span12 produce_m'>${produceRow.m }</td>
						<td class='span12 produce_l'>${produceRow.l }</td>
						<td class='span12 produce_xl'>${produceRow.xl }</td>
						<td class='span12 produce_xxl'>${produceRow.xxl }</td>
						<td class='span12'><a
							onclick="deleteRow(this,'confirm_produce_table')">删除</a></td>
					</tr>
				</c:forEach>
				</table></td>
	</tr>
	<tr>
	<td>加工方：</td>
	<td colspan="7"><input class="span12 " type="text"
	    value="${orderInfo.order.payAccountInfo}" readonly="readonly"/></td>
	</tr>
</table>
<table class="table table-striped table-bordered table-hover detail">
							<c:if test="${empty orderInfo.repairRecord}">
								<tr>
									<td class="title" style="width:22%;background: red;">实际生产数</td>
									<td>暂无</td>
								</tr>
							</c:if>
							<c:if test="${!empty orderInfo.repairRecord}">
								<tr>
									<td class="title" rowspan="${fn:length(orderInfo.repairRecord)+1}" style="width:22%;background: #ff0000;">实际生产数</td>
									<td class="title">日期</td>
									<td class="title">加工方</td>
									<td class="title">合格实收数量</td>
								</tr>
								<c:forEach var="repairRecord" items="${orderInfo.repairRecord}">
									<tr>
										<td>${repairRecord.repairTime}</td>
										<td>${repairRecord.repairSide}</td>
										<td>${repairRecord.qualifiedAmount}</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>