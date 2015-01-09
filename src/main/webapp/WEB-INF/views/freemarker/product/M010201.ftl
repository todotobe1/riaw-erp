<#ftl encoding="UTF-8">

<div id="M010201" style="padding:5px;">
	<script type="text/javascript">
		var M010201_editIndex = undefined;

		function M010201_endEditing(){
			if (M010201_editIndex == undefined) { return true; }
			if ($('#M010201_dg').datagrid('validateRow', M010201_editIndex)){
				var ed = $('#M010201_dg').datagrid('getEditor', {index:M010201_editIndex,field:'unitId'});
				var unitName = $(ed.target).combobox('getText');
				$('#M010201_dg').datagrid('getRows')[M010201_editIndex]['unitName'] = unitName;
				$('#M010201_dg').datagrid('endEdit', M010201_editIndex);
				M010201_editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}

		function M010201_onClickRow(index){
			if (M010201_editIndex != index){
				if (M010201_endEditing()){
					$('#M010201_dg').datagrid('selectRow', index).datagrid('beginEdit', index);
					M010201_editIndex = index;
				} else {
					$('#M010201_dg').datagrid('selectRow', M010201_editIndex);
				}
			}
		}

		function M010201_append(){
			if (M010201_endEditing()){
				$('#M010201_dg').datagrid('appendRow',{unitPrice:0.00});
				M010201_editIndex = $('#M010201_dg').datagrid('getRows').length-1;
				$('#M010201_dg').datagrid('selectRow', M010201_editIndex).datagrid('beginEdit', M010201_editIndex);
			}
		}

		function M010201_removeit(){
			if (M010201_editIndex == undefined) { return; }
			$('#M010201_dg').datagrid('cancelEdit', M010201_editIndex).datagrid('deleteRow', M010201_editIndex);
			M010201_editIndex = undefined;
		}

		function M010201_reject(){
			$('#M010201_dg').datagrid('rejectChanges');
			M010201_editIndex = undefined;
		}

		function M010201_save() {
			if (validateForm()) {
				$.messager.progress();

				var effectRow = new Object();
				effectRow["inserted"] = $('#M010201_dg').datagrid('getChanges', "inserted");
				effectRow["deleted"] = $('#M010201_dg').datagrid('getChanges', "deleted");
				effectRow["updated"] = $('#M010201_dg').datagrid('getChanges', "updated");

				$.ajax({
					type: "POST",
					url: "${FRAMEWORK_REQUEST_PATH}/product/management/add.json",
					data: {productName: $("#M010201_productName").val(), effectRows: JSON.stringify(effectRow)},
					success: function(data){
						if (data.status == "1") {
							$('#M010201_dg').datagrid('acceptChanges');
							confirm("产品已新增成功，是否继续新增？", 
									function() {
										refreshSelectedTab({tabs:'#main_content', url:'${FRAMEWORK_REQUEST_PATH}/product/management/additionView'});
									}, 
									function() {
										refreshSelectedTab({tabs:'#main_content', url:'${FRAMEWORK_REQUEST_PATH}/product/management/view'});
									});
						} else {
							msg("error:" + data.msg);
						}
					}
				});

				 $.messager.progress("close");
			 }
		}

		function validateForm() {
			if(!$("#M010201_productName").validatebox("isValid")) {
				$("#M010201_productName").focus();
				return false;
			}
			
			if(!M010201_endEditing()) {
				msg("error:价目信息不完整");
				return false;
			}
			
			var rows = $('#M010201_dg').datagrid('getRows');
			if(rows.length == 0) {
				msg("error:未填写产品价目信息");
				return false;
			}
			
			var unitNames = new Array();
			for (var i = 0; i<rows.length; i++) {
				if(rows[i].unitPrice <= 0){
					msg("error:产品单价不能小于等于0元");
					return false;
				}
				unitNames.push(rows[i].unitName);
			}
			unitNames.sort();
			for(var i = 0; i<unitNames.length-1; i++) {
				if (unitNames[i] == unitNames[i+1]) {
					msg("error:产品价目信息列表计量单位重复");
					return false;
				}
			}
			
			return true;
		}
	</script>
	 <div class="easyui-panel" title="产品基本信息" style="padding:5px;">
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="10%" align="right"><label for="productName">产品名称：</label></td>
				<td width="40%"><input id="M010201_productName" class="easyui-validatebox" style="width:100%;" type="text" name="productName" data-options="required:true" /></td>
				<td width="10%"></td>
				<td width="40%"></td>
			</tr>
		</table>
	</div>

	<br />

	<table id="M010201_dg" class="easyui-datagrid" title="价目信息" style="height:auto"
	data-options="
	fitColumns:true,
	iconCls: 'icon-edit',
	singleSelect: true,
	toolbar: '#M010201_tb',
	method: 'get',
	onClickRow: M010201_onClickRow
	">
		<thead>
			<tr>
				<th data-options="field:'unitId',width:100,
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
				}">计量单位</th>
				<th data-options="
				field:'unitPrice',
				width:80,
				align:'right',
				editor:{
					type:'numberbox',
					options:{
						precision:2,
					}
				}">单价（元）</th>
			</tr>
		</thead>
	</table>

	<div id="M010201_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="M010201_append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="M010201_removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="M010201_reject()">撤销</a>
	</div>

	<br />

	<table style="width:100%;font-size:12px;" align="center">
		<tr>
			<td align="right">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="M010201_save()">保存</a>
			</td>
			<td align="left">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="refreshSelectedTab({tabs:'#main_content', url:'${FRAMEWORK_REQUEST_PATH}/product/management/view'});">取消</a>
			</td>
		</tr>
	</table>
</div>