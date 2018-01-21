<!doctype html>

<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
  <head>
    <title tiles:fragment="title">IVR Error Page</title>
	<style type="text/css">
	.container{
		  position: relative;
		  margin: 0 auto;
		  padding: 20px 20px 20px;
		  width: 350px;
		  background-color: #ffffff;
		  margin-top:150px;
		}
	body {
    	background-color: #6698FF;
	}
	.btn{
		font-size: 14px;
		cursor: pointer;
		font-weight: bold;
	}
	</style>
  </head>
  <body>
    
    <div class="container">
        <form action="/ivrhome" method="get">               
            <fieldset>
                <legend><h3>IVR Error Details</h3></legend>
                <h3>Something went wrong. Click below to go to home page.</h3>
                <div align="center">
                    <button class="btn" type="submit">Home</button>
                </div>
            </fieldset>
        </form>
    </div>
       
  </body>
</html>