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
<jsp:include page="/WEB-INF/jsp/common/adminHead.jsp"/>

<!-- Carousel
================================================== -->
<%--body--%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">STUDENTS MANAGEMENT</h3>
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
                <div class="col-sm-7">
                            <h3>Students</h3>
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty studentPage.content}">
                                        <div class="alert alert-war">
                                            No students registered
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Full name</th>
                                                <th>Reg #</th>
                                                <th>Room #</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${studentPage.content}" var="student">
                                                <tr>
                                                    <td>${student.student.name}</td>
                                                    <td>${student.studentRegNo}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${student.student.bed == null}">
                                                                Non resident
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${student.student.bed.number}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <a href="/admin/student/${student.id}/details">Manage</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <div class="box-tools">
                                                    <jsp:include page="/WEB-INF/jsp/common/pagination.jsp">
                                                        <jsp:param name="paginatedRecord" value="studentPage"/>
                                                        <jsp:param name="url" value="${pagenatedUrl}"/>
                                                    </jsp:include>
                                                    <div class="input-group">
                                                        <form method="get" action="/admin/student/list">
                                                            <input type="text" name="search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search student number"/>
                                                            <div class="input-group-btn">
                                                                <button class="btn btn-sm btn-default"><i class="fa fa-search">Go</i></button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
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
