
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
		
		<%@include file="/common/header.jsp" %>

        
        <div class="maincontent">
            <div class="maincontentinner">
                <div class="row-fluid" style="min-height:300px;">
                
                    <!--  如果是其它页面，这里是填充具体的内容。 -->
                    <h4 class="widgettitle">入库订单详情</h4>
                      <form onsubmit="return verify()" action="${ctx }/logistics/rukuDetailPost.do" method="post">
                <table id="dyntable" class="table table-bordered responsive">
                
                  	<tr>
								<td rowspan=${product_list.size()+1 } >生产情况</td>
								<td >生产编号</td>
								<td >订单要求</td>
								<td >实际生产</td>
								<td >自检合格</td>
								<td >颜色</td>
								<td >款式</td>
								
							</tr>
							<c:forEach var="product" items="${product_list}" >
								<tr>
										 
										
											<td>${product.id }</td>
											<td>${product.askAmount }</td>
											<td>${product.produceAmount }</td>
											
											<td>${product.qualifiedAmount }</td>
											<td>${product.color }</td>
											<td>${product.style }</td>
										
										
									</tr>
						  </c:forEach>
			<tr>
								<td rowspan="3">包</td>
								<td >衣服款式</td>
								<td >衣服颜色</td>
								<td >衣服数量</td>
								<td>操作</td>
								<input id="clothes_amount" name="clothes_amount" type="hidden" />
								<input id="clothes_style_color" name="clothes_style_color" type="hidden" />
								<input id="clothes_style_name" name="clothes_style_name" type="hidden" />
							</tr>
							
							<tr>
								<td colspan="6" class="innertable"><table
										class="span12 table package_table">
										<tr class="addrow">
											<td><input class="span12" type="text" /></td>
											<td><input class="span12" type="text" /></td>
											<td><input class="span12" type="text" /></td>
											<td><a>添加</a></td>
										</tr>
									</table></td>
			</tr>
			<tr>
							<td>打包时间</td>
							<td><input id="datepicker" type="text" name="package_date"  class="input-medium "></td>
							</tr>
							<tr> <td><a id="addpackage" >添加包</a></td></tr>
                 
                </table>
                <input  name="task_id" type="hidden" value=${task_id }>
                <input name="order_id" type="hidden" value=${order_id }>
                <input name="process_id" type="hidden" value=${process_id }>
                  <p class="stdformbutton">
                        <button class="btn btn-primary">添加</button>
                    </p>
                </form>
                <div class="dataTables_paginate paging_full_numbers" id="dyntable_paginate" style="float:right">
                	<c:if test="${page==1 }">
	                	<a tabindex="0" class="first paginate_button paginate_button_disabled" id="dyntable_first">首页</a>
						<a tabindex="0" class="previous paginate_button paginate_button_disabled" id="dyntable_previous">上一页</a>
                	</c:if>
					<c:if test="${page>1 }">
	                	<a tabindex="0" class="first paginate_button" id="dyntable_first" href="${ctx }/employee/search.do?page=1&number_per_page=1">首页</a>
						<a tabindex="0" class="previous paginate_button" id="dyntable_previous" href="${ctx }/employee/search.do?page=${page-1 }&number_per_page=1">上一页</a>
                	</c:if>
					<c:if test="${page_number<6&&page_number>0}">
						<c:forEach var ="i" begin="1" end="${page_number }">
							<c:if test="${page!=i }"><a tabindex="0" class="paginate_button" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
							<c:if test="${page==i }"><a tabindex="0" class="paginate_active" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
						</c:forEach>
					</c:if>
					<c:if test="${page_number>5}">
						<c:choose>
							<c:when test="${page<3 }">
								<c:forEach var ="i" begin="1" end="5">
									<c:if test="${page!=i }"><a tabindex="0" class="paginate_button" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
									<c:if test="${page==i }"><a tabindex="0" class="paginate_active" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
								</c:forEach>
							</c:when>
							<c:when test="${page>page_number-3 }">
								<c:forEach var ="i" begin="${page_number-4 }" end="${page_number }">
									<c:if test="${page!=i }"><a tabindex="0" class="paginate_button" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
									<c:if test="${page==i }"><a tabindex="0" class="paginate_active" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var ="i" begin="${page-2 }" end="${page+2 }">
									<c:if test="${page!=i }"><a tabindex="0" class="paginate_button" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
					 				<c:if test="${page==i }"><a tabindex="0" class="paginate_active" href="${ctx }/employee/search.do?page=${i }&number_per_page=1">${i }</a></c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page<page_number }">
	                	<a tabindex="0" class="next paginate_button" id="dyntable_next" href="${ctx }/employee/search.do?page=${page+1 }&number_per_page=1">下一页</a>
						<a tabindex="0" class="last paginate_button" id="dyntable_last" href="${ctx }/employee/search.do?page=${page_number }&number_per_page=1">尾页</a>
                	</c:if>
					<c:if test="${page==page_number }">
	                	<a tabindex="0" class="next paginate_button paginate_button_disabled" id="dyntable_next">下一页</a>
						<a tabindex="0" class="last paginate_button paginate_button_disabled" id="dyntable_last">尾页</a>
                	</c:if>
				</div>
				
				
                      </div><!--row-fluid-->
                
                <div class="footer">
                    <div class="footer-left">
                        <span>&copy; 2014. 江苏南通智造链有限公司.</span>
                    </div>
                  
                </div><!--footer-->
                
            </div><!--maincontentinner-->
        </div><!--maincontent-->
        
        <%@include file="/common/js_file.jsp" %>

        
        <!-- 这里引入你需要的js文件 -->
        <script type="text/javascript" src="${ctx }/js/custom.js"></script>
        <script type="text/javascript" src="${ctx}/js/order/add_package.js"></script>
         <script type="text/javascript" src="${ctx}/js/form.js"></script>
        <%@include file="/common/footer.jsp" %>
    