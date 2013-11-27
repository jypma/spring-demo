<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
</head>
<body>
	<h3>You have ${fn:length(list)} todo items:</h3>
	<ul>
	<c:forEach var="todo" items="${list}">
		<li><a href="todos/{todo.id}">${todo.title}</a></li>
	</c:forEach>
	</ul>
	<div>
	  <a href="todos/create">Add a new item</a>
	</div>
</body>
</html>