<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<input type="button"
		onclick="javascript:beginGrabing('${pageContext.request.contextPath }');"
		value="开始抓取网站数据" />
	<input type="button"
		onclick="javascript:beginAcfunGrabing('${pageContext.request.contextPath }');"
		value="开始抓取A站数据" />
	<a href="${pageContext.request.contextPath }/dispatcher/beginningGrab">开始抓取页面</a>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		function beginAcfunGrabing(basePath) {
			$.ajax({
				url : basePath + "/acfun/acfunGrabing",
				type : "POST",
				asyn : true,
				dataType : "text",
				data : null,
				success : function(data) {
					if (data != "success") {
						alert("抓取acfun数据请求失败");
					} else {
						alert("完成acfun数据的抓取");
					}
				},
				error : function() {
					alert("acfun请求出错!");
				},
				beforeSend : function() {
					alert("开始抓取acfun数据");
				}
			});
		}
		function beginGrabing(basePath) {
			$.ajax({
				url : basePath + "/dispatcher/beginningGrab",
				type : "POST",
				asyn : true,
				dataType : "text",
				data : null,
				success : function(data) {
					if (data != "success") {
						alert("抓取网页数据请求失败");
					} else {
						alert("完成网页数据的抓取");
					}
				},
				error : function() {
					alert("请求出错!");
				},
				beforeSend : function() {
					alert("开始抓取网络数据");
				}
			});
		}
	</script>
</body>
</html>