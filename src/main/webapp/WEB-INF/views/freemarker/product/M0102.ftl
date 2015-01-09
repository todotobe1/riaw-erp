<#ftl encoding="UTF-8">

<div id="M0102" style="padding:5px;">
	<script type="text/javascript">
		function M0102_editProduct(){
			var row = $('#M0102_productGrid').datagrid('getSelected');
			if (row) {
				refreshSelectedTab({
					tabs: '#main_content',
					url: '${FRAMEWORK_REQUEST_PATH}/product/management/editView.htm',
					data: {id: row.productId}
				});
			}
		}

		function M0102_deleteProduct(){
			var row = $('#M0102_productGrid').datagrid('getSelected');
			if(row) {
				confirm("如删除此产品，相应的所有定价价目将被连带删除？",function() {
					$.ajax({
						type: "POST",
						url: "${FRAMEWORK_REQUEST_PATH}/product/management/delete.json",
						data: {productId: row.productId},
						success: function(data){
							if (data.status == "1") {
								$('#M0102_productGrid').datagrid('reload');
								msg("info:删除成功");
							}
						}
					});
				}, function() {
					return;
				});
			}
		}
	</script>
	<table style="width:100%;">
		<tr>
			<td>
				<table id="M0102_productGrid" title="产品列表" class="easyui-datagrid" url="${FRAMEWORK_REQUEST_PATH}/product/management/list.json" 
					toolbar="#M0102_productList_toolBar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th field="productName"  width="100">产品名称</th>
						</tr>
					</thead>
				</table>
			</td>
		</tr>
	</table>
	<div id="M0102_productList_toolBar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="refreshSelectedTab({tabs: '#main_content', url: '${FRAMEWORK_REQUEST_PATH}/product/management/additionView'});">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="M0102_editProduct()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="M0102_deleteProduct()">删除</a>
	</div>
</div>