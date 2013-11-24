<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<html>
  <body>
    <h3>You have ${fn:length(list)} todo items:</h3>
    <c:forEach var="todo" items="${list}">
      <a href="#">${todo.title}</a><br/>
    </c:forEach>
  </body>
</html>