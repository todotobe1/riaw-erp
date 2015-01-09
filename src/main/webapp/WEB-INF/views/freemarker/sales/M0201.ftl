<#ftl encoding="UTF-8">

<div id="M0201" style="padding:5px;">
	<script type="text/javascript">
		var M0201 = {
			editProduct : function() {
				var row = $('#M0201_deliveryNoteGrid').datagrid('getSelections');
				
				if (row.length < 1) {
					msg("info:请选择一条记录进行编辑");
					return;
				}
				
				if(row.length > 1){
					msg("error:只能同时进行一条记录编辑");
					return;
				}
				
				refreshSelectedTab({
					tabs : '#main_content',
					url : '${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/editView.htm',
					data : {
						id : row[0].id
					}
				});
			},

			printProduct : function() {
				var row = $('#M0201_deliveryNoteGrid').datagrid('getSelections');
				
				if (row.length < 1) {
					msg("info:请选择至少一条记录进行打印");
					return;
				}
				
				window
						.open("${FRAMEWORK_REQUEST_PATH}/report/sales/testReport?id="
								+ JSON.stringify(common_datagrid.getSelectionsFields('#M0201_deliveryNoteGrid',"id")));
			},

			deleteProduct : function() {
				var row = $('#M0201_deliveryNoteGrid').datagrid('getSelections');
				
				if (row.length < 1) {
					msg("info:请选择至少一条记录进行删除");
					return;
				}
				
				confirm(
						"确定要删除此送货单？",
						function() {
							$
									.ajax({
										type : "POST",
										url : "${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/delete.json",
										data : {
											deliveryNoteId : JSON.stringify(common_datagrid.getSelectionsFields('#M0201_deliveryNoteGrid',"id"))
										},
										success : function(data) {
											if (data.status == "1") {
												$('#M0201_deliveryNoteGrid')
														.datagrid('reload');
												msg("info:删除成功");
											}
										}
									});
						}, function() {
							return;
						});
			}
		};
	</script>
	<table style="width:100%;">
		<tr>
			<td>
				<table id="M0201_deliveryNoteGrid" title="送货单列表" class="easyui-datagrid" url="${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/list.json" 
					toolbar="#M0201_deliveryNoteList_toolBar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true"></th>
							<th field="orderNo"  width="100">订单编号</th>
							<th field="recordNo"  width="100">备案号</th>
							<th field="customer"  width="100">购货单位</th>
							<th field="deliveryVan"  width="100">送货车号</th>
							<th field="deliveryDate"  width="100" data-options="
								formatter: function (value,row,index) {
													return new Date(value).toLocaleDateString();
												}
							">送货日期</th>
							<th field="deliveryMan"  width="100">送货员</th>
							<th field="containerDeliver"  width="100" data-options="
								formatter: function (value,row,index) {
													return value.toFixed(0);
												}
							">配送周转箱数</th>
							<th field="containerTake"  width="100" data-options="
								formatter: function (value,row,index) {
													return value.toFixed(0);
												}
							">接收周转箱数</th>
						</tr>
					</thead>
				</table>
			</td>
		</tr>
	</table>
	<div id="M0201_deliveryNoteList_toolBar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="refreshSelectedTab({tabs: '#main_content', url: '${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/additionView'});">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="M0201.editProduct()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="M0201.printProduct()">打印</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="M0201.deleteProduct()">删除</a>
	</div>
</div>