<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<link rel="stylesheet" href="static/css/textdeco1.css">
    <title>DRV Solutions</title>
</head>
<body>
	<script src="static/js/drvcommon.js"></script>
  	<h1 class="shine">Welcome to DRV Solutions</h1>
	<form id="drvloginform" name="drvloginform" action="login" method="POST">
	<table class="links1" align="center">
		<tr>
			<td style="padding: 5px;">
				Email Address
			</td>
			<td style="padding: 5px;">
				<input id="uemail" name="uemail" type="text"></input>	
			</td>
		</tr>
		<tr>
			<td style="padding: 5px;">
				Password
			</td>
			<td style="padding: 5px;">
				<input id="upassword" name="upassword" type="password"></input>
			</td>
		</tr>
	</table>
	<a class="links2" href="#" onclick="doPost('drvloginform')">Login</a>
	<br><br><br><br><a class="links3" href="index.html">Home</a>
	<br><br><br><br><div id="errmsg" name="errmsg" class="errmsg">${errmsg}</div>
	</form>
</body>
</html>