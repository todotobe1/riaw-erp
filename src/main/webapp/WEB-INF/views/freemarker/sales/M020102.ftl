<#ftl encoding="UTF-8">

<div id="M020102" style="padding:5px;">
	<script type="text/javascript">

		var M020102 = {
			editIndex : undefined,
			endEditing : function() {
				if (M020102.editIndex == undefined) {
					return true;
				}
				if ($('#M020102_dg').datagrid('validateRow', M020102.editIndex)) {
					var ed = $('#M020102_dg').datagrid('getEditor', {
						index : M020102.editIndex,
						field : 'unitId'
					});
					var unitName = $(ed.target).combobox('getText');
					$('#M020102_dg').datagrid('getRows')[M020102.editIndex]['unitName'] = unitName;

					var ed1 = $('#M020102_dg').datagrid('getEditor', {
						index : M020102.editIndex,
						field : 'productId'
					});
					var productName = $(ed1.target).combobox('getText');
					$('#M020102_dg').datagrid('getRows')[M020102.editIndex]['productName'] = productName;

					$('#M020102_dg').datagrid('endEdit', M020102.editIndex);
					M020102.editIndex = undefined;
					return true;
				} else {
					return false;
				}
			},
			onClickRow : function(index) {
				if (M020102.editIndex != index) {
					if (M020102.endEditing()) {
						$('#M020102_dg').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						M020102.editIndex = index;
					} else {
						$('#M020102_dg').datagrid('selectRow',
								M020102.editIndex);
					}
				}
			},
			append : function() {
				if (M020102.endEditing()) {
					$('#M020102_dg').datagrid('appendRow', {});
					M020102.editIndex = $('#M020102_dg').datagrid('getRows').length - 1;
					$('#M020102_dg').datagrid('selectRow', M020102.editIndex)
							.datagrid('beginEdit', M020102.editIndex);
				}
			},
			removeit : function() {
				if (M020102.editIndex == undefined) {
					return;
				}
				$('#M020102_dg').datagrid('cancelEdit', M020102.editIndex)
						.datagrid('deleteRow', M020102.editIndex);
				M020102.editIndex = undefined;
			},
			reject : function() {
				$('#M020102_dg').datagrid('rejectChanges');
				M020102.editIndex = undefined;
			},
			save : function() {
				if (M020102.validateForm()) {
					$.messager.progress();

					var effectRow = new Object();
					effectRow["inserted"] = $('#M020102_dg').datagrid(
							'getChanges', "inserted");
					effectRow["deleted"] = $('#M020102_dg').datagrid(
							'getChanges', "deleted");
					effectRow["updated"] = $('#M020102_dg').datagrid(
							'getChanges', "updated");

					$
							.ajax({
								type : "POST",
								url : "${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/edit.json",
								data : {
									deliveryNoteId : "${deliveryNote.id}",
									recordNo : $("#M020102_recordNo").val(),
									customer : $("#M020102_customer").combobox("getValue"),
									deliveryVan : $("#M020102_deliveryVan")
											.val(),
									deliveryDate : $("#M020102_deliveryDate")
											.datebox("getValue"),
									deliveryMan : $("#M020102_deliveryMan")
											.val(),
									containerDeliver : $(
											"#M020102_containerDeliver").val(),
									containerTake : $("#M020102_containerTake")
											.val(),
									effectRows : JSON.stringify(effectRow)
								},
								success : function(data) {
									if (data.status == "1") {
										$('#M020102_dg').datagrid(
												'acceptChanges');
										$.messager
												.alert(
														"页面消息",
														"送货单已编辑成功",
														"info",
														function() {
															refreshSelectedTab({
																tabs : '#main_content',
																url : '${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/view'
															});
														});
									} else {
										msg("error:" + data.msg);
									}
								}
							});

					$.messager.progress("close");
				}
			},
			validateForm : function() {
				if (!$("#M020102_recordNo").validatebox("isValid")) {
					$("#M020102_recordNo").focus();
					return false;
				}
				if (!$("#M020102_customer").combobox("isValid")) {
					$("#M020102_customer").focus();
					return false;
				}
				if (!$("#M020102_deliveryVan").validatebox("isValid")) {
					$("#M020102_deliveryVan").focus();
					return false;
				}
				if (!$("#M020102_deliveryDate").datebox("isValid")) {
					$("#M020102_deliveryDate").focus();
					return false;
				}
				if (!$("#M020102_deliveryMan").validatebox("isValid")) {
					$("#M020102_deliveryMan").focus();
					return false;
				}
				if (!M020102.endEditing()) {
					msg("error:明细信息不完整");
					return false;
				}

				var rows = $('#M020102_dg').datagrid('getRows');
				if (rows.length == 0) {
					msg("error:未填写明细信息");
					return false;
				}

				return true;
			},
			updatePrice : function() {
				var ed1 = $('#M020102_dg').datagrid('getEditor', {
					index : M020102.editIndex,
					field : 'productId'
				});
				var productId = $(ed1.target).combobox('getValue');

				var ed2 = $('#M020102_dg').datagrid('getEditor', {
					index : M020102.editIndex,
					field : 'unitId'
				});
				var unitId = $(ed2.target).combobox('getValue');

				if (productId && unitId) {
					$
							.ajax({
								type : "POST",
								url : "${FRAMEWORK_REQUEST_PATH}/product/management/unitPrice.json",
								data : {
									productId : productId,
									unitId : unitId,
									customerCode: $("#M020102_customer").combobox('getValue')
								},
								success : function(data) {
									if (data.status == "1") {
										var edUnitPrice = $('#M020102_dg')
												.datagrid('getEditor', {
													index : M020102.editIndex,
													field : 'unitPrice'
												});
										$(edUnitPrice.target).numberbox(
												"setValue", data.data);
									} else {
										var edUnitPrice = $('#M020102_dg')
												.datagrid('getEditor', {
													index : M020102.editIndex,
													field : 'unitPrice'
												});
										$(edUnitPrice.target)
												.numberbox("clear");
										var edAmount = $('#M020102_dg')
										.datagrid('getEditor', {
											index : M020102.editIndex,
											field : 'amount'
										});
								$(edAmount.target).numberbox("clear");
										msg("error:" + data.msg);
									}
								}
							});
				}
			},
			updateAmount : function() {
				var ed1 = $('#M020102_dg').datagrid('getEditor', {
					index : M020102.editIndex,
					field : 'unitPrice'
				});
				var ed2 = $('#M020102_dg').datagrid('getEditor', {
					index : M020102.editIndex,
					field : 'quantity'
				});

				if (ed1 && ed2) {

					var unitPrice = $(ed1.target).numberbox('getValue');
					var quantity = $(ed2.target).numberbox('getValue');

					if (quantity && unitPrice) {
						var ed3 = $('#M020102_dg').datagrid('getEditor', {
							index : M020102.editIndex,
							field : 'amount'
						});
						$(ed3.target).numberbox('setValue',
								unitPrice * quantity);
					}

				}
			},
			numberBoxParser : function(s) {
				if (s == "")
					return 0;
				else
					return s;
			}
		};
	</script>
	 <div class="easyui-panel" title="订单基本信息" style="padding:5px;">
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="10%" align="right"><label for="M020102_recordNo">备案号：</label></td>
				<td width="40%"><input id="M020102_recordNo"  value="${deliveryNote.recordNo}" disabled="disabled" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020102_customer">购货单位：</label></td>
				<td width="40%">
					<input class="easyui-combobox" id="M020102_customer" style="width:100%;" name="language" value="${deliveryNote.customer}"
						data-options="
							url:'${FRAMEWORK_REQUEST_PATH}/resources/customer/code/allCustomers.json',
							method:'get',
							valueField:'code',
							disabled:true,
							textField:'name',
							editable:false,
							panelHeight:'auto',
							required:true
							">
				</td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020102_deliveryVan">送货车号：</label></td>
				<td width="40%"><input id="M020102_deliveryVan" value="${deliveryNote.deliveryVan}"  class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020102_deliveryDate">送货日期：</label></td>
				<td width="40%"><input id="M020102_deliveryDate" value="${deliveryNote.deliveryDate}"  class="easyui-datebox" style="width:100%;" type="text" data-options="required:true,editable:false" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020102_deliveryMan">送货员：</label></td>
				<td width="40%"><input id="M020102_deliveryMan" value="${deliveryNote.deliveryMan}" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020102_containerDeliver">配送周转箱数：</label></td>
				<td width="40%"><input id="M020102_containerDeliver" value="${deliveryNote.containerDeliver}" class="easyui-numberbox" style="width:100%;" type="text" data-options="value:0,min:0,parser:M020102.numberBoxParser" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020102_containerTake">接收周转箱数：</label></td>
				<td width="40%"><input id="M020102_containerTake" value="${deliveryNote.containerTake}" class="easyui-numberbox" style="width:100%;" type="text" data-options="value:0,min:0,parser:M020102.numberBoxParser" /></td>
				<td width="10%" align="right"></td>
				<td width="40%"></td>
			</tr>
		</table>
	</div>

	<br />
	<table id="M020102_dg" class="easyui-datagrid" title="订单明细信息" style="height:auto"
	data-options="
	fitColumns:true,
	iconCls: 'icon-edit',
	singleSelect: true,
	toolbar: '#M020102_tb',
	url: '${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/detail.json?id=${deliveryNote.id}',
	method: 'get',
	onClickRow: M020102.onClickRow
	">
		<thead>
			<tr>
				<th width="200" data-options="field:'productId',
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
							editable:false,
							onSelect: function (record) {
								M020102.updatePrice();
							}
						}}">品名及规格</th>
				<th width="50" data-options="field:'unitId',
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
						editable:false,
						onSelect: function (record) {
								M020102.updatePrice();
							}
					}}">单位</th>
				<th width="50" data-options="
				field:'quantity',
				editor:{
						type:'numberbox',
						options:{
							precision:0,
							required:true,
							min:1,
							value:1,
							onChange: M020102.updateAmount
						}}">数量</th>
				<th width="50" data-options="
					field:'unitPrice',
					editor:{
						type:'numberbox',
						options:{
							precision:2,
							required:true,
							onChange: M020102.updateAmount
						}}">单价</th>
				<th width="100" data-options="
					field:'amount',
					align:'right',
					editor:{
						type:'numberbox',
						options:{
							precision:2,
							disabled:true
						}}">金额</th>
				<th width="100" data-options="
					field:'productionDate',
					align:'right',
					editor:{
						type:'datebox',
						options:{
						required:true,
						editable:false
						}}">生产日期</th>
				<th width="300" data-options="
					field:'comments',
						editor:{
							type:'validatebox'}">备注</th>
			</tr>
		</thead>
	</table>

	<div id="M020102_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="M020102.append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="M020102.removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="M020102.reject()">撤销</a>
	</div>

	<br />
	<table style="width:100%;font-size:12px;" align="center">
		<tr>
			<td align="right">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="M020102.save()">保存</a>
			</td>
			<td align="left">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="refreshSelectedTab({tabs:'#main_content', url:'${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/view'});">取消</a>
			</td>
		</tr>
	</table>
</div>