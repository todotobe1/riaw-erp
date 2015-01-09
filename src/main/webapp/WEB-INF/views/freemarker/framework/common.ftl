<#ftl encoding="UTF-8">

<#macro header>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Riaw System</title>

	<link rel="stylesheet" type="text/css" href="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/themes/icon.css">

	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/jquery.min.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/extension/jquery.edatagrid.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/json2.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/common.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/common.datagrid.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/util.StringUtils.js"></script>
	<script type="text/javascript" src="${FRAMEWORK_REQUEST_BASEPATH}/resources/js/util.FormUtils.js"></script>

	<@message />
</head>
</#macro>

<#macro footer>
</#macro>

<#macro message>
	<#if PAGE_MESSAGE??>
		<script type="text/javascript">
			$(document).ready(function() {
				msg("${PAGE_MESSAGE}");
			});
		</script>
	</#if>
</#macro>