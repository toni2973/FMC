<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/header.jsp"%>

<div class="maincontent">
	<div class="maincontentinner">
		<div class="row-fluid" style="min-height:300px;">
			<!--  如果是其它页面，这里是填充具体的内容。 -->
			<div class="widget">
				<h4 class="widgettitle">采购确认</h4>
				<div class="widgetcontent">
					
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<td rowspan="3">客户信息</td>
								<td>客户编号</td>
								<td>姓名</td>
								<td>公司</td>
								<td>传真</td>
								<td>手机1</td>
								<td>手机2</td>
							</tr>
							<tr>
								<td>${orderInfo.order.customerId }</td>
								<td>${orderInfo.order.customerName }</td>
								<td>${orderInfo.order.customerCompany }</td>
								<td>${orderInfo.order.customerCompanyFax}</td>
								<td>${orderInfo.order.customerPhone1}</td>
								<td>${orderInfo.order.customerPhone2}</td>
							</tr>
							<tr>
								<td>公司地址</td>
								<td colspan="5">${orderInfo.order.customerCompanyAddress}</td>
							</tr>
							<tr>
								<td rowspan="6">款式信息</td>
								<td><label>款式名称<span class="required">*</span></label></td>
								<td colspan="2">款式性别<span class="required">*</span></td>
								<td colspan="2">款式季节<span class="required">*</span></td>
								<td>订单来源<span class="required">*</span></td>
							</tr>
							<tr>
								<td>${orderInfo.order.styleName }</td>
								<td colspan="2">${orderInfo.order.styleSex }</td>
								<td colspan="2">${orderInfo.order.styleSeason eq 'CHUNXIA'?'春夏':'秋冬' }</td>
								<td>${orderInfo.order.orderSource }</td>
							</tr>
							<tr>
								<td>面料类型</td>
								<td colspan="5">
									${fn:contains(orderInfo.order.fabricType,'SUOZHI')?'梭织':'' }
									${fn:contains(orderInfo.order.fabricType,'ZHENZHI')?'针织':'' }
									${fn:contains(orderInfo.order.fabricType,'BIANZHI')?'编织':'' }
									${fn:contains(orderInfo.order.fabricType,'SUOZHENHUNHE')?'梭针混合':'' }
									${fn:contains(orderInfo.order.fabricType,'ZHENBIANHUNHE')?'针编混合':'' }
									${fn:contains(orderInfo.order.fabricType,'SUOBIANHUNHE')?'梭编混合':'' }
								</td>
							</tr>
							<tr>
								<td>特殊工艺</td>
								<td colspan="5">
									${fn:contains(orderInfo.order.specialProcess,'SHUIXI')?'水洗&nbsp;':'' } 
									${fn:contains(orderInfo.order.specialProcess,'JIGUANG')?'激光&nbsp;':'' } 
									${fn:contains(orderInfo.order.specialProcess,'YAZHOU')?'压皱&nbsp;':'' } 
									${fn:contains(orderInfo.order.specialProcess,'其他')?'其他&nbsp;':'' } 
								</td>
							</tr>
							<tr>
								<td>其他说明</td>
								<td colspan="5">
									${fn:contains(orderInfo.order.otherRequirements,'ZHUBIAO')?'有主标&nbsp;':'' }
									${fn:contains(orderInfo.order.otherRequirements,'DIAOPAI')?'有吊牌&nbsp;':'' }
									${fn:contains(orderInfo.order.otherRequirements,'SHUIXI')?'有水洗&nbsp;':'' }
								</td>
							</tr>
							<tr>
								<td>参考链接</td>
								<td colspan="5"></td>
							</tr>
							<tr>
								<td rowspan="2">加工信息</td>
								<td>加工件数<span class="required">*</span></td>
								<td colspan="2">最迟交货时间</td>
								<td colspan="2">完工时间（天）</td>
								<td>码数</td>
							</tr>
							<tr>
								<td>${orderInfo.order.askAmount }</td>
								<td colspan="2">${fn:substring(orderInfo.order.askDeliverDate,0,10) }</td>
								<td colspan="2">${orderInfo.order.askProducePeriod }</td>
								<td>${orderInfo.order.askCodeNumber }</td>
							</tr>
							
							
							<tr>
								<td rowspan="2">面料</td>
								<td colspan="2">面料名称</td>
								<td colspan="2">单件米耗</td>
								<td colspan="2">操作</td>
								<input id="fabric_name" name="fabric_name" type="hidden" />
								<input id="fabric_amount" name="fabric_amount" type="hidden" />
							</tr>
							
							
							<tr>
								<td colspan="6" class="innertable"><table
										class="span12 table fabric_table">
										<tr class="addrow">
											<td><input class="span12" type="text" /></td>
											<td><input class="span12" type="text" /></td>
											<td><a>添加</a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td rowspan="2">辅料</td>
								<td colspan="2">辅料名称</td>
								<td colspan="2">单件耗数</td>
								<td colspan="2">操作</td>
								<input id="accessory_name" name="accessory_name" type="hidden" />
								<input id="accessory_query" name="accessory_query" type="hidden" />
							</tr>
							
							
							<tr>
								<td colspan="6" class="innertable"><table table
										class="span12 table accessory_table">
										<tr class="addrow">
											<td><input class="span12" type="text" /></td>
											<td><input class="span12" type="text" /></td>
											<td><a>添加</a></td>
										</tr>
									</table></td>
							</tr>
							
							
							<tr>
								<td rowspan="2">客户样衣</td>
								<td>提供样衣</td>
								<td colspan="2">邮寄时间</td>
								<td>快递名称</td>
								<td colspan="2">快递单号</td>
							</tr>
							<tr>
								<td>${orderInfo.order.hasPostedSampleClothes==0?'没有样衣':'' }
									${orderInfo.order.hasPostedSampleClothes==1?'收到样衣':'' }
									${orderInfo.order.hasPostedSampleClothes==2?'未收到样衣':'' }
								</td>
								<td colspan="2">${fn:substring(logistics.inPostSampleClothesTime,0,10) }</td>
								<td>${logistics.inPostSampleClothesType }</td>
								<td colspan="2">${logistics.inPostSampleClothesNumber }</td>
							</tr>
							<tr>
								<td rowspan="5">生产样衣</td>
								<td>制作样衣</td>
								<td colspan="2">邮寄时间</td>
								<td>快递名称</td>
								<td colspan="2">快递单号</td>
							</tr>
							<tr>
								<td>${orderInfo.order.isNeedSampleClothes==0?'否':'是' }</td>
								<td colspan="2">${fn:substring(logistics.sampleClothesTime,0,10) }</td>
								<td>${logistics.sampleClothesType }</td>
								<td colspan="2">${logistics.sampleClothesNumber }</td>
							</tr>
							<tr>
								<td>邮寄人</td>
								<td>手机</td>
								<td colspan="4">邮寄地址</td>
							</tr>
							<tr>
								<td>${logistics.sampleClothesName }</td>
								<td>${logistics.sampleClothesPhone }</td>
								<td colspan="4">${logistics.sampleClothesAddress }</td>
							</tr>
							<tr>
								<td>其他备注</td>
								<td colspan="5">${logistics.sampleClothesRemark }</td>
							</tr>
							<tr>
								<td>样衣信息</td>
								<td>样衣图片</td>
								<td colspan="2">${orderInfo.order.sampleClothesPicture }</td>
								<td>参考图片</td>
								<td colspan="2">${orderInfo.order.referencePicture }</td>
							</tr>
							<tr>
								<td>意见</td>
								<td colspan="6"><input class="span12" type="text" name="suggestion" /></td>
							</tr>
							
							
							
							
							
								<tr>
								 <td rowspan="5">面料采购</td>
                        <td colspan="2">面料名</td>
                        <td colspan="1">单件米耗</td>
                        <td colspan="1">件数</td>
                        <td colspan="2">总采购米数</td>

								
							</tr>
							
								<tr>
							
								 <c:forEach var="fabricCost" items="${fabricCostlist}" >
	                        <tr >
	                    <td colspan="2">${fabricCost.fabricName }</td>
	                     
						<td colspan="1">${fabricCost.tearPerMeter }</td>
                        <td colspan="1">${orderInfo.data.SampleAmount}</td>
                        <td colspan="2">${fabricCost.tearPerMeter*orderInfo.data.SampleAmount}</td>
						     </tr>
                        </c:forEach>
							</tr>
							
							
								
							<tr>
								  <td rowspan= "5">辅料采购</td>
                        <td colspan="2">辅料名</td>
                        <td colspan="1">单件耗数</td>
                        <td colspan="1">件数</td>
                        <td colspan="2">总采购个数</td>

							</tr>
							
							<tr>
								 <c:forEach var="accessoryCost" items="${accessoryCostlist}" >
	                        <tr >
	                    <td colspan="2">${accessoryCost.accessoryName }</td>
       					<td colspan="1">${accessoryCost.tearPerPiece }</td>
                        <td colspan="1">${orderInfo.data.SampleAmount}</td>
                        <td colspan="2">${accessoryCost.tearPerPiece*orderInfo.data.SampleAmount}</td>
						     </tr>
                        </c:forEach>
							</tr>
							
							
								
							<tr>
								<td>操作</td>	

								<td colspan="6">
								<form id="verify_form" method="post" action="${ctx }/buy/purchaseSampleMaterialSubmit.do">
									<input type="hidden" name="orderId" value="${orderInfo.order.orderId }" />
									<input type="hidden" name="taskId" value="${orderInfo.task.id }" />
									<input type="hidden" name="purchaseerror" value="1"/>
									
									<a id="agree_detail" class="btn btn-primary btn-rounded"><i class="icon-ok icon-white"></i> 同意</a>
								
                                 </form>

	                           <form  method="post" action="${ctx }/buy/purchaseSampleMaterialSubmit.do">
									<input type="hidden" name="orderId" value="${orderInfo.order.orderId }" />
									<input type="hidden" name="taskId" value="${orderInfo.task.id }" />
							
									<input type="hidden" name="purchaseerror" value="0"/>
									
								
									<a id="disagree_detail" class="btn btn-danger btn-rounded"><i class="icon-remove icon-white"></i> 拒绝</a>
                                 </form>

								</td>
							</tr>
							
							

						</table>
				
				</div>
				<!--widgetcontent-->
			</div>

		</div>
		<!--row-fluid-->



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
<%@include file="/common/js_form_file.jsp"%>
<link rel="stylesheet" href="${ctx}/css/order/add_order.css">
<script type="text/javascript" src="${ctx}/js/order/add_order.js"></script>
<script type="text/javascript" src="${ctx }/js/custom.js"></script>
<%@include file="/common/footer.jsp"%>
