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
        <h3 class="box-title">ROOM ITEMS MANAGEMENT</h3>
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
                <div class="col-sm-6">
                            <h3>Room items and their unit Costs</h3>
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty itemCosts}">
                                        <div class="alert alert-war">
                                            No room items available
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Unit cost</th>
                                                <th>Total</th>
                                                <th>Issued</th>
                                                <th>Available</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${itemCosts}" var="item">
                                                <tr>
                                                    <td>${item.itemName}</td>
                                                    <td>${item.unitCost}</td>
                                                    <td>${item.totalAvailable}</td>
                                                    <td>${item.totalIssued}</td>
                                                    <td>${item.getAvailable(item.totalAvailable , item.totalIssued)}</td>
                                                    <td>
                                                        <a href="/admin/hostels/roomitems?flag=true&val=${item.id}">Edit</a>
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
                    <div style="margin-top: 50px">
                        <div class="box-body">
                            <c:if test="${flag == true}">
                                <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                                    <form:form acceptCharset="UTF-8" action="/admin/hostel/itemcostupdate" method="post" modelAttribute="itemCost" cssClass="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="itemName" class="col-sm-3 control-label">Item Name</label>
                                            <div class="col-sm-9">
                                                <form:input path="itemName" id="itemName" type="text" cssClass="form-control" placeholder="Name of hostel" readonly="true" />
                                                <form:input path="id" id="id" type="hidden"/>
                                                <form:errors path="itemName" cssClass="form-inline" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="unitCost" class="col-sm-3 control-label">Unit Cost</label>
                                            <div class="col-sm-9">
                                                <form:input path="unitCost" id="code" type="text" cssClass="form-control" placeholder="Ksh" />
                                                <form:errors path="unitCost" cssClass="form-inline" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="totalAvailable" class="col-sm-3 control-label">Total available</label>
                                            <div class="col-sm-9">
                                                <form:input path="totalAvailable" id="code" type="text" cssClass="form-control" placeholder="Total available" />
                                                <form:errors path="totalAvailable" cssClass="form-inline" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-10">
                                                <input class="btn btn-success" type="submit" value="Submit">
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </c:if>
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
