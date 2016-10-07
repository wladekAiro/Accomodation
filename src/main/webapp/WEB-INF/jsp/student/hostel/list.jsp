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
        <h3 class="box-title">EGERTON HOSTELS</h3>

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
                    <c:choose>
                        <c:when test="${profile == null}">
                            <div class="alert alert-danger">
                                You must complete your profile before you can proceed
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty hostelList}">
                                        <div class="alert alert-warning">
                                            No Hostels registered
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <h3>Hostels</h3>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Code</th>
                                                <th>Zone</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${hostelList}" var="hostel">
                                                <tr>
                                                    <td>${hostel.name}</td>
                                                    <td>${hostel.code}</td>
                                                    <td>${hostel.zone.code}</td>
                                                    <td>
                                                        <a href="/student/hostel/${hostel.id}">View</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-3">
                    <%--<div style="margin-top: 50px">--%>
                    <%--<div class="box-body">--%>
                    <%--<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal">--%>
                    <%--ADD ZONE--%>
                    <%--</button>--%>
                    <%--</div>--%>
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
