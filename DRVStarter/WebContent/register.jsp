<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<link rel="stylesheet" href="static/css/textdeco1.css">
    <title>DRV Solutions</title>
</head>
<body>
	<script src="static/js/drvcommon.js"></script>
  	<h1 class="shine">Welcome to DRV Solutions</h1>
  	<form id="drvregform" name="drvregform" action="register" method="POST">
	<table id="reg_table" class="links1" align="center">
		<tr>
			<td style="padding: 5px;">
				Name
			</td>
			<td style="padding: 5px;">
				<input id="uname" name="uname" type="text"></input>	
			</td>
		</tr>
		<tr>
			<td style="padding: 5px;">
				Email ID
			</td>
			<td style="padding: 5px;">
				<input id="uemail" name="uemail" type="text"></input>
			</td>
		</tr>
	</table>
		<a class="links2" href="#" onclick="doRegister()">Submit</a>
		<br><br><br><br><a class="links3" href="index.html">Home</a>
		<br><br><br><br><div id="errmsg" name="errmsg" class="errmsg">${errmsg}</div>
	</form>

</body>
</html>