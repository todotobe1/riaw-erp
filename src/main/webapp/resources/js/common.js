function refreshSelectedTab(obj) {
	if (obj.data) {
		var params = "";
		for ( var name in obj.data) {
			params += name + "=" + obj.data[name] + "&";
		}
		params.substring(0, params.length - 1);

		obj.url = obj.url + "?" + params;
	}

	$(obj.tabs).panel("refresh", obj.url);
}

function msg(msg) {
	var index = msg.indexOf(":");
	// error, info, question, warning
	if (index == -1) {
		$.messager.alert("页面消息", msg);
	} else {
		$.messager
				.alert("页面消息", msg.substr(index + 1), msg.substring(0, index));
	}

}

function confirm(msg, onYes, onNo) {
	$.messager.confirm('页面消息', msg, function(response) {
		if (response)
			onYes();
		else
			onNo();
	});
}

function toString(obj) {
	alert(JSON.stringify(obj));
}

function showDataGridChanges(grid) {
	var changes = "";

	var insertedRows = $(grid).datagrid('getChanges', 'inserted');
	changes += " inserted: " + JSON.stringify(insertedRows);

	var updatedRows = $(grid).datagrid('getChanges', 'updated');
	changes += " updated: " + JSON.stringify(updatedRows);

	var deletedRows = $(grid).datagrid('getChanges', 'deleted');
	changes += " deleted: " + JSON.stringify(deletedRows);

	alert(changes);
}