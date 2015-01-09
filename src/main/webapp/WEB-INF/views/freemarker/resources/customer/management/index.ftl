<#ftl encoding="UTF-8">

<div id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX" style="padding:5px;">
	<script type="text/javascript">
		var RESOURCES_CUSTOMER_MANAGEMENT_INDEX = {
		
			editCustomer: function () {
				var row = $('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID').datagrid('getSelected');
				if (row) {
					var record;
					
					$.ajax({
						async:false,
						type: "POST",
						url: "${FRAMEWORK_REQUEST_PATH}/resources/customer/management/read.json",
						data: {code: row.code},
						success: function(data){
							if (data.status == "1") {
								record = data.data;
							} else {
								msg("error:" + data.msg);
								return;
							}
						}
					});
					
					RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT.fill(record);
					$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT').window('open');
				}
			},
			
			deleteCustomer: function () {
				var row = $('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID').datagrid('getSelected');
				if(row) {
					confirm("确定要删除此客户？",function() {
						$.ajax({
							type: "POST",
							url: "${FRAMEWORK_REQUEST_PATH}/resources/customer/management/delete.json",
							data:  {code: row.code},
							success: function(data){
								if (data.status == "1") {
									$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID').datagrid('reload');
									msg("info:删除成功");
								}
							}
						});
					}, function() {
						return;
					});
				}
			}
		
		};
	</script>
	<table style="width:100%;">
		<tr>
			<td>
				<table id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID" title="客户列表" class="easyui-datagrid" 
					data-options="
						url:'${FRAMEWORK_REQUEST_PATH}/resources/customer/management/list.json',
						toolbar:'#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID_TOOLBAR',
						pagination:'true',
						rownumbers:'true',
						fitColumns:'true',
						singleSelect:'true'
					">
					<thead>
						<tr>
							<th field="code" width="100">客户编码</th>
							<th field="name" width="100">客户名称</th>
							<th field="liaison" width="100">联系人</th>
							<th field="telephone" width="100">联系电话</th>
						</tr>
					</thead>
				</table>
			</td>
		</tr>
	</table>
	<div id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID_TOOLBAR">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD').window('open')">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX.editCustomer()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX.deleteCustomer()">删除</a>
	</div>

	<script type="text/javascript">
		var RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD = {
			clear: function(){
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_CODE").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE").val("");
			},
			stringifyData: function(){
				var name = "name=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME").val();
				var liaison = "liaison=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON").val();
				var telephone = "telephone=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE").val();
				return name + "&" + liaison + "&" + telephone;
			},
			validate:function(){
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME").focus();
					return false;
				}
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON").focus();
					return false;
				}
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE").focus();
					return false;
				}
				
				return true;
			},
			save:function(){
				if(!this.validate())
					return;
					
				$.ajax({
					type: "POST",
					url: "${FRAMEWORK_REQUEST_PATH}/resources/customer/management/save.json",
					data: this.stringifyData(),
					success: function(data){
						if (data.status == "1") {
							msg("info:用户新增成功");
							$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD').window('close');
							RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD.clear();
							$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID').datagrid('reload');
						} else {
							msg("error:" + data.msg);
						}
					}
				});
			},
			cancel:function(){
				this.clear();
				$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD').window('close');
			}
		};
	</script>
	<!-- 新增客户 -->
	<div id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD" class="easyui-window" title="新增客户"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:854px;height:480px;padding:10px;">
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME">客户名称：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_NAME" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON">联系人：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_LIAISON" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE">联系电话：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD_TELEPHONE" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%"></td>
				<td width="40%"></td>
			</tr>
		</table>
		<table style="width:100%;font-size:12px;">
			<tr><td>&nbsp;</td></tr>
		</table>
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="40%">&nbsp;</td>
				<td width="10%" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD.save()">保存</a>
				</td>
				<td width="10%" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_ADD.cancel()">取消</a>
				</td>
				<td width="40%">&nbsp;</td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		var RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT = {
			fill: function(record) {
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE").val(record.code);
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME").val(record.name);
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON").val(record.liaison);
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE").val(record.telephone);
			},
			clear: function(){
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON").val("");
				$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE").val("");
			},
			stringifyData: function(){
				var code = "code=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE").val();
				var name = "name=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME").val();
				var liaison = "liaison=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON").val();
				var telephone = "telephone=" + $("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE").val();
				return code + "&" + name + "&" + liaison + "&" + telephone;
			},
			validate:function(){
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE").focus();
					return false;
				}
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME").focus();
					return false;
				}
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON").focus();
					return false;
				}
				if (!$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE").validatebox("isValid")) {
					$("#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE").focus();
					return false;
				}
				
				return true;
			},
			save:function(){
				if(!this.validate())
					return;
					
				$.ajax({
					type: "POST",
					url: "${FRAMEWORK_REQUEST_PATH}/resources/customer/management/edit.json",
					data: this.stringifyData(),
					success: function(data){
						if (data.status == "1") {
							msg("info:用户编辑成功");
							$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT').window('close');
							RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT.clear();
							$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_CUSTOMER_GRID').datagrid('reload');
						} else {
							msg("error:" + data.msg);
						}
					}
				});
			},
			cancel:function(){
				this.clear();
				$('#RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT').window('close');
			}
		};
	</script>
	<!-- 编辑客户 -->
	<div id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT" class="easyui-window" title="编辑客户"
		data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width:854px;height:480px;padding:10px;">
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE">客户编码：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_CODE" class="easyui-validatebox" disabled="disabled" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME">客户名称：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_NAME" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON">联系人：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_LIAISON" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE">联系电话：</label></td>
				<td width="40%"><input id="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT_TELEPHONE" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
			</tr>
		</table>
		<table style="width:100%;font-size:12px;">
			<tr><td>&nbsp;</td></tr>
		</table>
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="40%">&nbsp;</td>
				<td width="10%" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT.save()">保存</a>
				</td>
				<td width="10%" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="RESOURCES_CUSTOMER_MANAGEMENT_INDEX_EDIT.cancel()">取消</a>
				</td>
				<td width="40%">&nbsp;</td>
			</tr>
		</table>
	</div>
</div>