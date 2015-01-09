var common_datagrid = {

	getSelectionsFields : function(datagrid, fieldName) {
		if (!$(datagrid) || !fieldName)
			return;

		var selections = $(datagrid).datagrid("getSelections");

		if (!selections)
			return;

		var fieldValue = new Array();

		for ( var idx in selections) {
			var row = selections[idx];
			fieldValue.push(eval("row." + fieldName));
		}

		return fieldValue;
	}

};