<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <h3 class="box-title">${block.hostel.name.toUpperCase()} HOSTEL  BLOCK ${block.name.toUpperCase()}</h3>
        <div class="box-tools">
            <div class="input-group">
                <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
                <div class="input-group-btn">
                    <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </div>
    </div><!-- /.box-header -->
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
                            <h3>Rooms</h3>
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty roomsPage.content}">
                                        <div class="alert alert-war">
                                            No rooms registered
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Room #</th>
                                                <th>Type</th>
                                                <th>Capacity</th>
                                                <th>Cost</th>
                                                <th>Beds</th>
                                                <th>Status</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${roomsPage.content}" var="room">
                                                <tr
                                                     <%--<c:choose>--%>
                                                         <%--<c:when test="${room.status(room.beds,room.capacity) == 'Full'}">--%>
                                                             <%--style="background-color: red"--%>
                                                         <%--</c:when>--%>
                                                     <%--</c:choose>--%>
                                                        >
                                                    <td>${room.name}</td>
                                                    <td>${room.roomType.name()}</td>
                                                    <td>${room.capacity}</td>
                                                    <td>${room.cost}</td>
                                                    <td>${room.beds.size()}</td>
                                                    <td
                                                            <c:choose>
                                                                <c:when test="${room.status(room.beds,room.capacity) == 'Full'}">
                                                                    style="background-color: red"
                                                                </c:when>
                                                            </c:choose>
                                                            >${room.status(room.beds,room.capacity)}
                                                    </td>
                                                    <td
                                                            <c:choose>
                                                                <c:when test="${room.status(room.beds,room.capacity) == 'Full'}">
                                                                    style="background-color: red"
                                                                </c:when>
                                                            </c:choose>
                                                            >
                                                        <c:choose>
                                                            <c:when test="${room.status(room.beds,room.capacity) == 'Full'}">
                                                                Full
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="/student/room/${room.id}?flag=true">Book</a>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <jsp:include page="/WEB-INF/jsp/common/pagination.jsp">
                                                    <jsp:param name="paginatedRecord" value="roomsPage"/>
                                                    <jsp:param name="url" value="${pagenatedUrl}"/>
                                                </jsp:include>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                </div>
                <div class="col-sm-3">
                    <%--<div style="margin-top: 50px">--%>
                        <%--<div class="box-body">--%>
                                    <%--<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal">--%>
                                        <%--ADD ROOM--%>
                                    <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--end body--%>
<!-- start of footer section -->

<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
