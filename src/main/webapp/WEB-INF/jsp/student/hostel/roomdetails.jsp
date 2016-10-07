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
                <c:choose>
                    <c:when test="${profile == null}">
                        <div class="col-sm-10 col-sm-offset-1">
                            <div class="alert alert-danger">
                                You must complete your profile before you can book a room.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-sm-8">
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty room.beds}">
                                        <div class="alert alert-warning">
                                            You have not been allocated a room. Book or consult administrator for
                                            assistance.
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Bed #</th>
                                                <th>Type</th>
                                                <th>Status</th>
                                                <th>Occupant</th>
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
                                                        <c:choose>
                                                            <c:when test="${bed.student != null}">
                                                                ${bed.student.name} &nbsp;(${bed.student.profile.studentRegNo})
                                                            </c:when>
                                                            <c:otherwise>
                                                                Not Occupied
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <sec:authorize access="isAuthenticated()">
                                                            <sec:authentication property="principal" var="principal"/>
                                                            <c:choose>
                                                                <c:when test="${bed.status == 'BOOKED'}">
                                                                    <c:choose>
                                                                        <c:when test="${bed.myBed(principal.user.id)}">
                                                                            <a class="btn-sm btn-primary btn-danger"
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
                                                                                    <a class="btn-sm btn-primary btn-success"
                                                                                       href="#">
                                                                                        Request transfer</a>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </c:when>
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
                        <div class="col-sm-4">
                            <div style="margin-top: 50px">
                                <div class="box-body">
                                    <h3>My Room items ${roomItems.size()}</h3>

                                    <div class="table-responsive">
                                        <c:choose>
                                            <c:when test="${empty roomItems}">
                                                <div class="alert alert-warning">
                                                    You have not been allocated room items
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Item name</th>
                                                        <th>Status</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${roomItems}" var="item">
                                                        <tr>
                                                            <td>${item.itemName.name()}</td>
                                                            <td>${item.clearStatus.name()}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
