<html>
<head>
    <title>Sign Up Page</title>
</head>
<body>
<form>
    <div id = "attributes">
    <div class = "elements" id="name">Name:<input type="text" name="name" value="${signUpCO.name}"/></div><br>
    <div class = "elements" id="age"> Age:<input type="number" name="age" value="${signUpCO.age}"/></div><br>
    <div class = "elements" id="address">Address:<input type="text" name="address" value="${signUpCO.address}"/></div><br>
    <div class = "elements" id ="email"> Email:<input type="text" name="email" value="${signUpCO.email}"/></div><br>
    <div class = "elements" id ="contact">Contact:<input type="number" name="contact" value="${signUpCO.contact}"/></div><br>
    <div class = "elements" id="password">Password:<input type="password" name="password" value="${signUpCO.password}"/></div><br>
    <div class = "elements" id="cpassword">Confirm Password:<input type="password" name="confirm password" value="${signUpCO.confirmPassword}"/></div><br>
    <input type="submit"/>
    </div>
</form>
</body>
</html>

<style>
    #attributes
    {
     margin:auto;
    }
    .elements{
     display: flex;
     align-items: flex-start;
     flex-direction: column;
     width: fit-content;
 }
</style>

