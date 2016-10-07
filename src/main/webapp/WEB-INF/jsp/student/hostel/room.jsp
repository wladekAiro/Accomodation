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
        <h3 class="box-title">${room.block.hostel.name.toUpperCase()}
            HOSTEL,BLOCK ${room.block.name.toUpperCase()},ROOM ${room.name.toUpperCase()}</h3>

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
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <%--<div class="box-tools">
                    <a type="button" class="btn btn-primary btn-sm" href="#">
                        Users
                    </a>
                    &nbsp;&nbsp;
                    <a type="button" class="btn btn-primary btn-sm" href="#">
                        Roles
                    </a>
                </div>--%>

                <c:if test="${message}">
                    <div class="alert alert-success">
                            ${content}
                    </div>
                </c:if>
            </div>
            <div class="box-body">
                <div class="col-sm-8">
                    <h3>Beds</h3>

                    <div class="table-responsive">
                        <c:choose>
                            <c:when test="${empty room.beds}">
                                <div class="alert alert-warning">
                                    No beds available in this room
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Bed #</th>
                                        <th>Type</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${room.beds}" var="bed">
                                        <tr>
                                            <td>${bed.number}</td>
                                            <td>${bed.bedType.name()}</td>
                                            <td>${bed.status.name()}</td>
                                            <td>
                                                <sec:authorize access="isAuthenticated()">
                                                    <sec:authentication property="principal" var="principal"/>
                                                    <c:choose>
                                                        <c:when test="${bed.status == 'BOOKED'}">
                                                            <c:choose>
                                                                <c:when test="${bed.myBed(principal.user.id)}">
                                                                    <a class="btn-sm btn-primary btn-foursquare"
                                                                       href="/student/room/details/${principal.user.id}/cancel">
                                                                        Cancel Booking</a>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${bed.status == 'OCCUPIED'}">
                                                                    <c:choose>
                                                                        <c:when test="${bed.myBed(principal.user.id)}">
                                                                            <a class="btn-sm btn-primary btn-foursquare"
                                                                               href="/student/room/details/${principal.user.id}/cancel">
                                                                                Request Transfer</a>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:choose>
                                                                        <c:when test="${bed.status == 'RESERVED'}">
                                                                            <c:choose>
                                                                                <c:when test="${bed.myBed(principal.user.id)}">
                                                                                    <a class="btn-sm btn-primary"
                                                                                       href="/student/room/bed/${bed.id}?flag=true">Book</a>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <a class="btn-sm btn-primary"
                                                                               href="/student/room/bed/${bed.id}?flag=true">Book</a>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </sec:authorize>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                        <%--<tr>--%>
                                        <%--<jsp:include page="/WEB-INF/jsp/common/pagination.jsp">--%>
                                        <%--<jsp:param name="paginatedRecord" value="roomsPage"/>--%>
                                        <%--<jsp:param name="url" value="${pagenatedUrl}"/>--%>
                                        <%--</jsp:include>--%>
                                        <%--</tr>--%>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div style="margin-top: 50px">
                        <div class="box-body">
                            <c:choose>
                                <c:when test="${book != null}">
                                    <c:choose>
                                        <c:when test="${book == true}">

                                        </c:when>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--modal--%>
<%--<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<%--<div class="modal-dialog" role="document">--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--<h4 class="modal-title" id="myModalLabel">Register Bed</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<div class="box">--%>
<%--<div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">--%>
<%--<form:form acceptCharset="UTF-8" action="/admin/room/createbed" method="post" modelAttribute="bed" cssClass="form-horizontal" role="form">--%>
<%--<div class="form-group">--%>
<%--<label for="number" class="col-sm-3 control-label">Number</label>--%>
<%--<div class="col-sm-9">--%>
<%--<form:input path="number" id="number" type="text" cssClass="form-control" placeholder="Bed number" />--%>
<%--<form:input path="id" id="id" type="hidden"/>--%>
<%--<form:input path="roomId" id="roomId" value="${room.id}" type="hidden"/>--%>
<%--<form:errors path="number" cssClass="form-inline" />--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label for="bedType" class="col-sm-3 control-label">Bed type</label>--%>
<%--<div class="col-sm-9">--%>
<%--<form:select path="bedType" id="bedType" type="select" cssClass="form-control">--%>
<%--<form:options/>--%>
<%--</form:select>--%>
<%--<form:errors path="bedType" cssClass="form-inline" />--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-sm-offset-3 col-sm-10">--%>
<%--<input class="btn btn-success" type="submit" value="Submit">--%>
<%--</div>--%>
<%--</div>--%>
<%--</form:form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--end body--%>
<!-- start of footer section -->

<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
