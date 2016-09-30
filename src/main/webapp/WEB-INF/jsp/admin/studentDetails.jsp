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
<jsp:include page="/WEB-INF/jsp/common/adminHead.jsp"/>

<!-- Carousel
================================================== -->
<%--body--%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">STUDENT DETAILS</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <p>

                <h3>NAME : ${profile.student.name}</h3>
                </p>
            </div>
            <div class="box-body">
                <p>
                <h3>REG # : ${profile.studentRegNo}</h3>
                </p>
                <p>
                <h3>ROOM # : ${profile.student.bed.number}</h3>
                </p>
                <div class="col-sm-7">
                    <h3>Room Items</h3>

                    <div class="table-responsive">
                        <c:choose>
                            <c:when test="${empty roomItems}">
                                <div class="alert alert-war">
                                    No items available for this student
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Item name</th>
                                        <th>Unit cost</th>
                                        <th>Condition</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${roomItems}" var="item">
                                        <tr>
                                            <td>${item.itemName}</td>
                                            <td>${item.cost}</td>
                                            <td>${item.itemCondition}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.clearStatus == 'ASSIGNED'}">
                                                        <a class="btn btn-primary"
                                                           href="#/admin/student/item/${item.id}/issue/${profile.student.id}">Issue</a>
                                                    </c:when>
                                                </c:choose>
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
                                                    <input type="text" name="search"
                                                           class="form-control input-sm pull-right"
                                                           style="width: 150px;" placeholder="Search student number"/>

                                                    <div class="input-group-btn">
                                                        <button class="btn btn-sm btn-default"><i class="fa fa-search">Go</i>
                                                        </button>
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
