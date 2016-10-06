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
        <h3 class="box-title">CLEAR STUDENT</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <p>
                <h3>NAME : ${profile.student.name}</h3>
                </p>
                <p>
                <h3>REG # : ${profile.studentRegNo}</h3>
                </p>
                <p>
                <h3>ROOM # : ${profile.student.bed.number} &nbsp;&nbsp;&nbsp;<em>${profile.student.bed.status}</em></h3>
                </p>

                <c:if test="${message}">
                    <div class="alert alert-success">
                            ${content}
                    </div>
                </c:if>

            </div>
            <div class="box-body">
                <div class="row">
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
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${roomItems}" var="item">
                                            <tr>
                                                <td>${item.itemName}</td>
                                                <td>${item.cost}</td>
                                                <td>${item.itemCondition}</td>
                                                <td>${item.clearStatus}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${item.clearStatus == 'ASSIGNED'}">
                                                            <a class="btn btn-default"
                                                               href="">Not issued</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${item.clearStatus == 'ISSUED'}">
                                                                    <a class=""
                                                                       href="/admin/item/${item.id}/form/${profile.id}?flag=true">Clear</a>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <c:choose>
                            <c:when test="${flag == true}">

                            </c:when>
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
