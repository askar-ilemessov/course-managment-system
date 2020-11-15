<html>
<head>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

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

<form>
  <div class="logo">
    <img src="logo.png" alt="logo">
  </div>

  <div class="card">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
        
    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>

  <div class="card" style="background-color:#f1f1f1">
    <div class="register">Don't have an account? <a href="#">Register</a></div>
  </div>
</form>

</body>
</html>
