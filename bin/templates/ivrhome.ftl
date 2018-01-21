<!doctype html>

<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
  <head>
    <title tiles:fragment="title">IVR Service Page</title>
	<style type="text/css">
	body {
    	background-color: #6698FF;
	}
	.btn{
		//background-color: #6698FF;
		font-size: 14px;
		cursor: pointer;
		font-weight: bold;
	}
	.container{
		  position: relative;
		  margin: 0 auto;
		  padding: 20px 20px 20px;
		  width: 350px;
		  background-color: #FFFFFF;
		  margin-top:30px;
		}
	.container1{
		  position: relative;
		  margin: 0 auto;
		  padding: 20px 20px 20px;
		  width: 800px;
		  background-color: #FFFFFF;
		  margin-top:30px;
		}
	</style>
  </head>
  <body>
    
    <div class="container">
        <form action="/ivrservice" method="post" enctype="multipart/form-data">               
            <fieldset>
                <legend><h3>IVR File Upload Form</h3></legend>
                <#if msg??>
                <h3>	${msg} </h3>
                </#if>
                <label for="file"><h3>File to Upload</h3></label>
                <input class="btn" type="file" id="file" name="file" required/> <br/><br/>     
                <div align="center">
                    <button class="btn" type="submit">UPLOAD</button>
                </div>
            </fieldset>
        </form>
    </div>
    
    <div class="container1">
    	<fieldset>
    		<legend><h3>IVR File Details</h3></legend><br/>
    		
    		<table style="width:100%">
			  <tr>
			    <th>
			    	<form action="/ivrservice" method="get">               
		                <label>Enter File Name:</label>
		                <input type="text" id="fileNm" name="fileNm" placeholder="File name without(.mp3)" required/> </br></br>     
		                <div align="center">
		                    <button class="btn" type="submit">GET DETAILS</button>
		                </div>
	        		</form>
			    </th>
			    
			    <th>
			     <#if fileVO??>
			     	Name: ${fileVO.fileNm!"NA"} </br>
			    	Duration: ${hrs!"0"} hr : ${mins!"0"} min :  ${fileVO.duration%60!"0"} sec </br>
			    	Size: ${fileVO.size!"0"} KB
                </#if>
			    </th>
			  </tr>
			  
				<#if fileNms?has_content>
					<tr colspan="2">
					<td> </br> <a href="http://localhost:8080/ivrhome"> HIDE LIST OF FILES </a> </td>
				<#else>
					<td> </br> <a href="http://localhost:8080/ivrfiles">SHOW LIST OF FILES</a> </td>
		        	</tr>
	        	</#if>
			  	
			  	<#if fileNms?has_content>
			  		<ul>
					<#list fileNms as fileNm>
						<tr colspan="2">
							<td>  <li> ${fileNm}  </br> </li> </td>
						</tr>
					</#list>
					</ul>
				</#if>
		
			</table>

        </fieldset>
    </div>
    
  </body>
</html>