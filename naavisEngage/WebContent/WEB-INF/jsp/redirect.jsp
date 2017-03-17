<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Redirect</title>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
  <script type="text/javascript">
  	var uagent = navigator.platform.toLowerCase();
  	function DetectIphone() {
  	  	alert(uagent);
     	if(uagent.search(uagent) > -1)
     		return true;
     	else
     		return false;
  	}
    function load() {
       // alert("in load");

        //alert("${url}");
        /* var getValue = $("#myUrl").val(); */
        //var isIphone = DetectIphone();
       // var url="${url}";
        //alert("url" + url);
		/* var url = window.location.href;
		var subarr = url.split("=");
		var param = subarr[subarr.length - 1];
		/*if(isIphone == true) {
			
		}
		else {
			window.location.href = "intent://com.versaworks.monsterhospital/#Intent;scheme=com.versaworks.monsterhospital;package=com.versaworks.monsterhospital;S.scode=" + param + ";end";
		} */
        //window.location.reload = "intent://com.versaworks.cascadevalley/#Intent;scheme=com.versaworks.cascadevalley;package=com.versaworks.cascadevalley;S.scode=123456;end";
        <c:redirect url="${url}"/>
    }
    load();
    </script>
    </head>

    <body>
		<h1>Redirecting to NavisHealth</h1>
		<%-- <input type="text" value="${url}" id="myUrl">  --%>
		 <%-- ${ssc} --%>
		 ${url}
		 
		 <a href="${url}" >Click here</a>
    </body>


</html>
