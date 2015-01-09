<#ftl encoding="UTF-8">
<#import "../../framework/common.ftl" as common />

<html>
	<@common.header />
	<body>
		<table width="100%" height="100%">
			<tr align="center" valign="middle">
				<td>
					<div class="easyui-panel" style="width:300px;padding:10px;" title="系统登入">
						<form id="login_loginForm" method="post" action="${FRAMEWORK_REQUEST_PATH}/human/user/log/in.htm">
							<table align="center" style="font-size:12px;">
								<tr>
									<td align="right">
										<label for="login_userName">用户名：</label>
									</td>
									<td>
										<input id="login_userName" class="easyui-validatebox" type="text" name="userName" data-options="required:true" />
									</td>
						        </tr>
						        <tr>
						        	<td align="right">
										<label for="login_password">密码：</label>
									</td>
									<td>
										<input id="login_password" class="easyui-validatebox" type="password" name="password" data-options="required:true" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<a id="login_btn" href="#" class="easyui-linkbutton">登  入</a>
										<script type="text/javascript">
											$("#login_btn").bind("click", function() {
												$.messager.progress();
												var isValid = $("#login_loginForm").form("validate");

												if (isValid)
													$("#login_loginForm").submit();

												$.messager.progress("close");
												return isValid;
											});
										</script>
									</td>
								</tr>
						    </table>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>