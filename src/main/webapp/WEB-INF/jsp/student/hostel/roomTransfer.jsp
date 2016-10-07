<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/common/adminHeader.jsp">
        <jsp:param name="title" value="EU"/>
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/jsp/common/studentHead.jsp"/>

<!-- Carousel
================================================== -->
<%--body--%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal" var="principal"/>
                <span class="hidden-xs">MY PROFILE</span>
            </sec:authorize>
        </h3>

        <div class="box-tools">
            <div class="input-group">
                <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;"
                       placeholder="Search"/>

                <div class="input-group-btn">
                    <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <div class="row">
            <div class="box">
                <div class="col-sm-12">
                    <div class="box">
                        <div class="box-header">
                            <c:if test="${message}">
                                <div class="alert alert-success">
                                        ${content}
                                </div>
                            </c:if>
                        </div>
                        <div class="box-body">
                            <sec:authorize access="isAuthenticated()">
                                <sec:authentication property="principal" var="principal"/>
                                <p>

                                <h3>NAME : ${principal.user.name.toUpperCase()}</h3>
                                </p>
                                <c:choose>
                                    <c:when test="${profile.student.bed == null}">
                                        <div class="alert alert-info">
                                            You have not been allocated a room. Fill non-resident form or book a room
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                                            <form:form acceptCharset="UTF-8" action="/student/submittransfer/${profile.id}" method="post"
                                                       modelAttribute="transfer" cssClass="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="currentRoom" class="col-sm-3 control-label">Current Room</label>

                                                    <div class="col-sm-9">
                                                        <form:input path="currentRoom" id="currentRoom" type="text"
                                                                    cssClass="form-control" value="${profile.student.bed.number}"
                                                                    placeholder="Current room number" readonly="true"/>
                                                        <form:input path="profileId" id="profileId" value="${profile.id}" type="hidden"/>
                                                        <form:errors path="currentRoom" cssClass="form-inline"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="prefer" class="col-sm-3 control-label">Preferred accommodation/Room</label>

                                                    <div class="col-sm-9">
                                                        <form:input path="prefer" id="prefer" type="text"
                                                                    cssClass="form-control" placeholder="Preferred room/accomodation"/>
                                                        <form:errors path="prefer" cssClass="form-inline"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="reason" class="col-sm-3 control-label">Reason for transfer</label>

                                                    <div class="col-sm-9">
                                                        <form:textarea path="reason" cssClass="form-control"
                                                                       placeholder="Reason for transfer request"/>
                                                        <form:errors path="reason" cssClass="form-inline"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-3 col-sm-10">
                                                        <input class="btn btn-success" type="submit" value="Submit">
                                                    </div>
                                                </div>
                                            </form:form>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--end body--%>
<!-- start of footer section -->

<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
