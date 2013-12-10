<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<base
  href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
</head>
<body>
  <h3>Todo item</h3>
  <form:form id="form" method="post" modelAttribute="item" action="todos/save">
    <form:hidden path="id"/>

    <div>
      <form:label path="title"><spring:message code="item.title" /></form:label>
      <form:input path="title" placeholder="What needs to be done?" />
      <form:errors path="title" />
    </div>

    <div>
      <button type="submit" class="btn">Save</button>
    </div>

  </form:form>
  <a href="todos">Cancel</a>
</body>
</html>