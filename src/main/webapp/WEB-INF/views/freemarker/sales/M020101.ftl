<#ftl encoding="UTF-8">

<div id="M020101" style="padding:5px;">
	<script type="text/javascript">

		var M020101 = {
			editIndex : undefined,
			updatePrice : function() {
				var ed1 = $('#M020101_dg').datagrid('getEditor', {
					index : M020101.editIndex,
					field : 'productId'
				});
				var productId = $(ed1.target).combobox('getValue');

				var ed2 = $('#M020101_dg').datagrid('getEditor', {
					index : M020101.editIndex,
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
									customerCode: $("#M020101_customer").combobox('getValue')
								},
								success : function(data) {
									if (data.status == "1") {
										var edUnitPrice = $('#M020101_dg')
												.datagrid('getEditor', {
													index : M020101.editIndex,
													field : 'unitPrice'
												});
										$(edUnitPrice.target).numberbox(
												"setValue", data.data);
									} else {
										var edUnitPrice = $('#M020101_dg')
												.datagrid('getEditor', {
													index : M020101.editIndex,
													field : 'unitPrice'
												});
										$(edUnitPrice.target)
												.numberbox("clear");
										var edAmount = $('#M020101_dg')
												.datagrid('getEditor', {
													index : M020101.editIndex,
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
				var ed1 = $('#M020101_dg').datagrid('getEditor', {
					index : M020101.editIndex,
					field : 'unitPrice'
				});
				var ed2 = $('#M020101_dg').datagrid('getEditor', {
					index : M020101.editIndex,
					field : 'quantity'
				});

				if (ed1 && ed2) {

					var unitPrice = $(ed1.target).numberbox('getValue');
					var quantity = $(ed2.target).numberbox('getValue');

					if (quantity && unitPrice) {
						var ed3 = $('#M020101_dg').datagrid('getEditor', {
							index : M020101.editIndex,
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
			},
			validateForm : function() {
				if (!$("#M020101_recordNo").validatebox("isValid")) {
					$("#M020101_recordNo").focus();
					return false;
				}
				if (!$("#M020101_customer").combobox("isValid")) {
					$("#M020101_customer").focus();
					return false;
				}
				if (!$("#M020101_deliveryVan").validatebox("isValid")) {
					$("#M020101_deliveryVan").focus();
					return false;
				}
				if (!$("#M020101_deliveryDate").datebox("isValid")) {
					$("#M020101_deliveryDate").focus();
					return false;
				}
				if (!$("#M020101_deliveryMan").validatebox("isValid")) {
					$("#M020101_deliveryMan").focus();
					return false;
				}

				if (!M020101.endEditing()) {
					msg("error:明细信息不完整");
					return false;
				}

				var rows = $('#M020101_dg').datagrid('getRows');
				if (rows.length == 0) {
					msg("error:未填写明细信息");
					return false;
				}

				return true;
			},
			endEditing : function() {
				if (M020101.editIndex == undefined) {
					return true;
				}
				if ($('#M020101_dg').datagrid('validateRow', M020101.editIndex)) {
					var ed = $('#M020101_dg').datagrid('getEditor', {
						index : M020101.editIndex,
						field : 'unitId'
					});
					var unitName = $(ed.target).combobox('getText');
					$('#M020101_dg').datagrid('getRows')[M020101.editIndex]['unitName'] = unitName;

					var ed1 = $('#M020101_dg').datagrid('getEditor', {
						index : M020101.editIndex,
						field : 'productId'
					});
					var productName = $(ed1.target).combobox('getText');
					$('#M020101_dg').datagrid('getRows')[M020101.editIndex]['productName'] = productName;

					$('#M020101_dg').datagrid('endEdit', M020101.editIndex);
					M020101.editIndex = undefined;
					return true;
				} else {
					return false;
				}
			},

			onClickRow : function(index) {
				if (M020101.editIndex != index) {
					if (M020101.endEditing()) {
						$('#M020101_dg').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						M020101.editIndex = index;
					} else {
						$('#M020101_dg').datagrid('selectRow',
								M020101.editIndex);
					}
				}
			},

			append : function() {
				if (M020101.endEditing()) {
					$('#M020101_dg').datagrid('appendRow', {});
					M020101.editIndex = $('#M020101_dg').datagrid('getRows').length - 1;
					$('#M020101_dg').datagrid('selectRow', M020101.editIndex)
							.datagrid('beginEdit', M020101.editIndex);
				}
			},

			removeit : function() {
				if (M020101.editIndex == undefined) {
					return;
				}
				$('#M020101_dg').datagrid('cancelEdit', M020101.editIndex)
						.datagrid('deleteRow', M020101.editIndex);
				M020101.editIndex = undefined;
			},

			reject : function() {
				$('#M020101_dg').datagrid('rejectChanges');
				M020101.editIndex = undefined;
			},

			save : function() {
				if (M020101.validateForm()) {
					$.messager.progress();

					var effectRow = new Object();
					effectRow["inserted"] = $('#M020101_dg').datagrid(
							'getChanges', "inserted");
					effectRow["deleted"] = $('#M020101_dg').datagrid(
							'getChanges', "deleted");
					effectRow["updated"] = $('#M020101_dg').datagrid(
							'getChanges', "updated");

					$
							.ajax({
								type : "POST",
								url : "${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/add.json",
								data : {
									recordNo : $("#M020101_recordNo").val(),
									customer : $("#M020101_customer").combobox("getValue"),
									deliveryVan : $("#M020101_deliveryVan")
											.val(),
									deliveryDate : $("#M020101_deliveryDate")
											.datebox("getValue"),
									deliveryMan : $("#M020101_deliveryMan")
											.val(),
									containerDeliver : $(
											"#M020101_containerDeliver").val(),
									containerTake : $("#M020101_containerTake")
											.val(),
									effectRows : JSON.stringify(effectRow)
								},
								success : function(data) {
									if (data.status == "1") {
										$('#M020101_dg').datagrid(
												'acceptChanges');
										confirm(
												"送货单已创建成功，是否继续创建？",
												function() {
												refreshSelectedTab({tabs: '#main_content', url: '${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/additionView'});
												},
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
			}
		};
	</script>
	 <div class="easyui-panel" title="订单基本信息" style="padding:5px;">
		<table style="width:100%;font-size:12px;">
			<tr>
				<td width="10%" align="right"><label for="M020101_recordNo">备案号：</label></td>
				<td width="40%"><input id="M020101_recordNo" class="easyui-validatebox" style="width:100%;" value="SHDX034" disabled="disabled" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020101_customer">购货单位：</label></td>
				<td width="40%">
					<input class="easyui-combobox" id="M020101_customer" style="width:100%;" name="language"
						data-options="
							url:'${FRAMEWORK_REQUEST_PATH}/resources/customer/code/customers.json',
							method:'get',
							valueField:'code',
							textField:'name',
							editable:false,
							panelHeight:'auto',
							required:true,
							onSelect:function(record){
								var rows = $('#M020101_dg').datagrid('getChanges');
								if(rows.length > 0) {
									confirm('切换客户将清空明细列表，是否放弃当前所做的修改？',function(){
										M020101.reject();
									},function(){
										
									});
								} else {
									M020101.reject();
								}
							},
							onLoadSuccess:function(){
								var data = $('#M020101_customer').combobox('getData');
								if (data.length > 0) {
									$('#M020101_customer').combobox('select',data[0].code);
								}
							}">
				</td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020101_deliveryVan">送货车号：</label></td>
				<td width="40%"><input id="M020101_deliveryVan" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020101_deliveryDate">送货日期：</label></td>
				<td width="40%"><input id="M020101_deliveryDate" class="easyui-datebox" style="width:100%;" type="text" data-options="required:true,editable:false" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020101_deliveryMan">送货员：</label></td>
				<td width="40%"><input id="M020101_deliveryMan" class="easyui-validatebox" style="width:100%;" type="text" data-options="required:true" /></td>
				<td width="10%" align="right"><label for="M020101_containerDeliver">配送周转箱数：</label></td>
				<td width="40%"><input id="M020101_containerDeliver" class="easyui-numberbox" style="width:100%;" type="text" data-options="value:0,min:0,parser:M020101.numberBoxParser" /></td>
			</tr>
			<tr>
				<td width="10%" align="right"><label for="M020101_containerTake">接收周转箱数：</label></td>
				<td width="40%"><input id="M020101_containerTake" class="easyui-numberbox" style="width:100%;" type="text" data-options="value:0,min:0,parser:M020101.numberBoxParser" /></td>
				<td width="10%" align="right"></td>
				<td width="40%"></td>
			</tr>
		</table>
	</div>

	<br />

	<table id="M020101_dg" class="easyui-datagrid" title="订单明细信息" style="height:auto"
	data-options="
	fitColumns:true,
	iconCls: 'icon-edit',
	singleSelect: true,
	toolbar: '#M020101_tb',
	method: 'get',
	onClickRow: M020101.onClickRow
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
								M020101.updatePrice();
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
								M020101.updatePrice();
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
							onChange: M020101.updateAmount
						}}">数量</th>
				<th width="50" data-options="
					field:'unitPrice',
					editor:{
						type:'numberbox',
						options:{
							precision:2,
							required:true,
							onChange: M020101.updateAmount
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

	<div id="M020101_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="M020101.append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="M020101.removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="M020101.reject()">撤销</a>
	</div>

	<br />

	<table style="width:100%;font-size:12px;" align="center">
		<tr>
			<td align="right">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="M020101.save()">保存</a>
			</td>
			<td align="left">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="refreshSelectedTab({tabs:'#main_content', url:'${FRAMEWORK_REQUEST_PATH}/sales/deliverynote/view'});">取消</a>
			</td>
		</tr>
	</table>
</div>