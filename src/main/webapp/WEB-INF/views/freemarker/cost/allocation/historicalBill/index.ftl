<#ftl encoding="UTF-8">

<script type="text/javascript">

	var COST_ALLOCATION_HISTORICALBILL_INDEX = {};

</script>

<div id="COST_ALLOCATION_HISTORICALBILL_INDEX" style="padding:5px;">

	<div class="easyui-panel" style="text-align:center;padding:10px;">
		分摊帐期：
		<input class="easyui-combobox" style="width:500px;" id="COST_ALLOCATION_HISTORICALBILL_INDEX_COMBOBOX"
			data-options="
				url:'${FRAMEWORK_REQUEST_PATH}/cost/allocation/historicalBill/periods.json',
				method:'get',
				valueField:'value',
				textField:'name',
				panelHeight:'auto',
				editable:false,
				onSelect:function(record){
					$.messager.progress();
					window.open('${FRAMEWORK_REQUEST_PATH}/cost/allocation/billReport/print?period=' + record.value,'COST_ALLOCATION_HISTORICALBILL_INDEX_report');
					$.messager.progress('close');
				},
				onLoadSuccess:function(){
					var data = $('#COST_ALLOCATION_HISTORICALBILL_INDEX_COMBOBOX').combobox('getData');
					if (data.length > 0) {
						$('#COST_ALLOCATION_HISTORICALBILL_INDEX_COMBOBOX').combobox('select',data[0].value);
					}
				}
				"
		>
	</div>

	<iframe width="100%" height="100%" scrolling="auto" name="COST_ALLOCATION_HISTORICALBILL_INDEX_report"></iframe>

</div>