<#ftl encoding="UTF-8">

<script type="text/javascript">
	function genContent(obj) {
		var htmlContent = "<form id='bill'><table width='100%' style='font-size:12px;' border='1'>";
		for (i in obj) {
			htmlContent += "<tr>";
			htmlContent += "<td>" + obj[i].subjectName+ "</td>";
			htmlContent += "<td>" + "<input type='text' name='" + obj[i].subjectCode + "' value='" + obj[i].dataThisMonth + "' />" + "</td>";
			
			htmlContent += "<td>";
			if (obj[i].unitPrice != null) {
				htmlContent += "单价：" + obj[i].unitPrice + obj[i].unit;
			} else {
				htmlContent += "&nbsp;";
			}
			htmlContent += "</td>";
			
			htmlContent += "<td>" + "上月数据：" + obj[i].dataLastMonth + "</td>";
			htmlContent += "</tr>";
		}
		htmlContent += "</table></form>";
		htmlContent += "<table width='100%' style='font-size:12px;'><tr><td align='center'><button onclick='save();'>提交</button></td></tr></table>";
		$("#content1").html(htmlContent);
	}
	
	function save() {
		$.ajax({
			type: 'POST',
			url: '${FRAMEWORK_REQUEST_PATH}/cost/allocation/currentBill/save.json',
			data: {partnerCode: $('#COST_ALLOCATION_CURRENTBILL_INDEX_COMBOBOX').combobox('getValue'), data:JSON.stringify($('#bill').serializeArray())},
			success: function(data){
				if(data.status=="1")
					msg("info:数据保存成功");
				else
					msg("error:" + data.msg);
			}
		});
	}
</script>

<div id="COST_ALLOCATION_CURRENTBILL_INDEX" style="padding:5px;">
	<div class="easyui-panel" style="text-align:center;padding:10px;">
		承包商：
		<input class="easyui-combobox" style="width:500px;" id="COST_ALLOCATION_CURRENTBILL_INDEX_COMBOBOX"
			data-options="
				url:'${FRAMEWORK_REQUEST_PATH}/cost/allocation/currentBill/partners.json',
				method:'get',
				valueField:'value',
				textField:'name',
				panelHeight:'auto',
				editable:false,
				onSelect:function(record){
					$.messager.progress();
					$.ajax({
						type: 'POST',
						url: '${FRAMEWORK_REQUEST_PATH}/cost/allocation/currentBill/partnerCurrentBill.json',
						data: {partnerCode: $('#COST_ALLOCATION_CURRENTBILL_INDEX_COMBOBOX').combobox('getValue')},
						success: function(data){
							genContent(data);
						}
					});
					$.messager.progress('close');
				},
				onLoadSuccess:function(){
					var data = $('#COST_ALLOCATION_CURRENTBILL_INDEX_COMBOBOX').combobox('getData');
					if (data.length > 0) {
						$('#COST_ALLOCATION_CURRENTBILL_INDEX_COMBOBOX').combobox('select',data[0].value);
					}
				}
				"
		>
		<table><tr><td>&nbsp;</td></tr></table>
		<div id="content1"></div>
	</div>
</div>