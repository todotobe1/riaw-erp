<#ftl encoding="UTF-8">
<#import "common.ftl" as common />

<html>
	<@common.header />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#main_menu").tree({
				onClick: function(node){
					if (node.attributes.url == "" || node.attributes.url == null)
						return;

					$("#main_content").panel({
						href: "${FRAMEWORK_REQUEST_PATH}" + node.attributes.url
					});
				}
			});
		});
	</script>
	<body class="easyui-layout">
		<div data-options="region:'north'" style="height:20px;text-align:right;background:#eee;">
			<a href="${FRAMEWORK_REQUEST_PATH}/human/user/log/out.htm">退出系统</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

	    <div data-options="region:'west'" style="width:200px;padding:5px;">
			<ul id="main_menu" class="easyui-tree" data-options="url:'${FRAMEWORK_REQUEST_PATH}/human/user/menu/load.json',lines:true"></ul>
	    </div>

	    <div data-options="region:'center'">
	    	<div id="main_content" class="easyui-panel" data-options="fit:true,border:false"></div>
	    </div>

	    <div data-options="region:'south'" style="height:20px;text-align:center;background:#eee;">
	    	Copyright © 2014 Riawworks Studio. All rights reserved.
	    </div>
	</body>
</html>