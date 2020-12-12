<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.logo {
	text-align: center;
	margin: 24px 0 12px 0;
	width: "42";
	height: "42";
}

.card {
	padding: 20px;
}

.register {
	float: right;
}
</style>
</head>
<body>

	<form role="form" action="home" method="post">
		<div class="logo">
			<img src="logo.png" alt="logo">
		</div>

		<div class="card">
		 <% if (request.getAttribute("showError") == "true") { %>
         <div class="alert alert-danger" id="error" role="alert">
				<strong>Incorrect Login.</strong> Try Again.
			</div>
      		<% } %>
      		 <%if (request.getAttribute("accountExists") == "true") { %>
         <div class="alert alert-danger" id="error" role="alert">
				<strong>Account Already Exists.</strong>
			</div>
      		<% } %>
			<label for="uname"><b>Username</b></label>
			<div class="form-group">
				<input type="text" placeholder="Enter Username" name="uname"
					required>
			</div>
			<label for="psw"><b>Password</b></label>
			<div class="form-group">
				<input type="password" placeholder="Enter Password" name="psw"
					required>
			</div>
			<button type="submit" name="login">Login</button>
			<label> <input type="checkbox" checked="checked"
				name="remember"> Remember me
			</label>
		</div>

		<div class="card" style="background-color: #f1f1f1; text-align: right">
			<div>
				Don't have an account? <a href="/cms/RegistrationPage.html">Register</a>
			</div>
			
		</div>
	</form>

</body>
</html>
