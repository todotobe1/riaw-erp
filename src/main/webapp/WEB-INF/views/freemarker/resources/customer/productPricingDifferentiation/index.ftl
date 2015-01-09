<#ftl encoding="UTF-8">

<div id="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX" style="padding:5px;">

	<script type="text/javascript">
	
		var RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX = {
		
			editIndex: undefined,
			
			endEditing: function() {
				if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex == undefined){
					return true
				}
				if ($('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('validateRow', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex)){
					var ed = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getEditor', {index:RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex,field:'unitId'});
					$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getRows')[RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex]['unitName'] = $(ed.target).combobox('getText');;
					
					var ed2 = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getEditor', {index:RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex,field:'productId'});
					$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getRows')[RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex]['productName'] = $(ed2.target).combobox('getText');;
					
					$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('endEdit', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex);
					
					RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex = undefined;
					return true;
				} else {
					return false;
				}
			},
			
			onClickRow: function(index) {
				if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex != index){
					if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.endEditing()){
						$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('selectRow', index).datagrid('beginEdit', index);
						RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex = index;
					} else {
						$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('selectRow', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex);
					}
				}
			},
		
			append: function() {
				if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.endEditing()){
					$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('appendRow',{});
					RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getRows').length-1;
					$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('selectRow', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex)
					.datagrid('beginEdit', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex);
				}
			},
			
			removeit: function() {
				if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex == undefined){return}
				$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('cancelEdit', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex)
				.datagrid('deleteRow', RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex);
				RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex = undefined;
			},
			
			accept: function() {
				if(!RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.validate()){
					return false;
				}
			
				if (RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.endEditing()){
					var effectRow = new Object();
					effectRow["inserted"] = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getChanges', "inserted");
					effectRow["deleted"] = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getChanges', "deleted");
					effectRow["updated"] = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getChanges', "updated");
					$.ajax({
						type: "POST",
						url: "${FRAMEWORK_REQUEST_PATH}/resources/customer/productPricingDifferentiation/save.json",
						data: {
							customerCode: $("#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_COMBOBOX").combobox('getValue'),
							effectRows: JSON.stringify(effectRow)
						},
						success: function(data){
							if (data.status == "1") {
								$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('acceptChanges');
								msg("info:保存成功");
							} else {
								msg("error:" + data.msg);
							}
						}
					});
				}
			},
		
			reject: function() {
				$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('rejectChanges');
				RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.editIndex = undefined;
			},
			
			validate: function(){
				if (!RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.endEditing()) {
					msg("error:明细信息不完整");
					return false;
				}
			
				var rows = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getRows');
				
				rows.sort(function(a, b) {
					return a.productName > b.productName?true: (a.productName == b.productName?(a.unitName>b.unitName?true:false):false);
				});
				
				for (var i = 0; i<rows.length; i++) {
					if(rows[i].unitPrice <= 0){
						msg("error:产品单价不能小于等于0元");
						return false;
					}
				}
				for (var i = 1; i<rows.length; i++) {
					if(rows[i].productName == rows[i-1].productName && rows[i].unitName == rows[i-1].unitName) {
						msg("error:产品单价[" + rows[i].productName + "-" + rows[i].unitName + "]重复");
						return false;
					}
				}
				return true;
			}

		};
	</script>

	<div class="easyui-panel" style="text-align:center;padding:10px;">
		客户列表：<input class="easyui-combobox" name="language" style="width:500px;" id="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_COMBOBOX"
			data-options="
				url:'${FRAMEWORK_REQUEST_PATH}/resources/customer/code/customers.json',
				method:'get',
				valueField:'code',
				textField:'name',
				panelHeight:'auto',
				editable:false,
				onSelect:function(record){
					var rows = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('getChanges');
					if(rows.length > 0) {
						confirm('是否放弃当前所做的修改？',function(){
							RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.reject();
							$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('load',{customerCode: record.code});
						},function(){
							
						});
					} else {
						RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.reject();
						$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg').datagrid('load',{customerCode: record.code});
					}
				},
				onLoadSuccess:function(){
					var data = $('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_COMBOBOX').combobox('getData');
					if (data.length > 0) {
						$('#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_COMBOBOX').combobox('select',data[0].code);
					}
				}
				"
		>
	</div>
	<br />
	<table id="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_dg" class="easyui-datagrid"
		data-options="
			iconCls: 'icon-edit',
			singleSelect: true,
			toolbar: '#RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_tb',
			url:'${FRAMEWORK_REQUEST_PATH}/resources/customer/productPricingDifferentiation/pricing.json',
			method: 'get',
			onClickRow: RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.onClickRow,
			fitColumns:true
	">
		<thead>
			<tr>
				<th
					data-options="
						field:'productId',
						width:10,
						formatter:function(value,row){
							return row.productName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'productId',
								textField:'productName',
								url:'${FRAMEWORK_REQUEST_PATH}/product/code/product',
								required:true,
								editable:false
							}
						}"
					>产品名称</th>
				<th
					data-options="
						field:'unitId',
						width:10,
						formatter:function(value,row){
							return row.unitName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'unitId',
								textField:'unitName',
								url:'${FRAMEWORK_REQUEST_PATH}/product/code/measurementUnit',
								required:true,
								editable:false
							}
						}"
					>计量单位</th>
				<th
					data-options="
						field:'unitPrice',
						width:10,
						align:'right',
						editor:{
							type:'numberbox',
							options:{
								precision:2,
								required:true
							}
						}"
					>单价（元）</th>
			</tr>
		</thead>
    </table>
     
    <div id="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX_tb" style="height:auto">
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.append()">新增</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.removeit()">删除</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.accept()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="RESOURCES_CUSTOMER_PRODUCTPRICINGDIFFERENTIATION_INDEX.reject()">撤销</a>
    </div>

</div>