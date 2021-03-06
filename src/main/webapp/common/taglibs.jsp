<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="loginUser" value="${sessionScope.CUSER}" scope="application"/>