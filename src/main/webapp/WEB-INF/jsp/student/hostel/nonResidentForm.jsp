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
                <div class="col-sm-4 col-sm-offset-0">
                    <div class="row">
                        <div class="col-sm-12 col-sm-offset-0">
                            <img src="/resources/images/default.png" class="user-image" alt="User image" height="30%"
                                 width="100%">
                        </div>
                    </div>
                </div>
                <div class="col-sm-5 col-sm-offset-1">
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
                                    <c:when test="${profile == null}">
                                        <p>
                                            <button type="button" class="btn btn-sm" data-toggle="modal" data-target="#myModal">
                                                COMPLETE YOUR PROFILE
                                            </button>
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>
                                        <h3>GENDER : ${profile.gender.name()}</h3>
                                        </p>
                                        <p>
                                        <h3>REG NO : ${profile.studentRegNo.toUpperCase()}</h3>
                                        </p>
                                        <p>
                                        <h3>FACULTY : ${profile.faculty.toUpperCase()}</h3>
                                        </p>
                                        <p>
                                        <h3>DEPARTMENT : ${profile.department.toUpperCase()}</h3>
                                        </p>
                                        <p>
                                        <h3>COURSE : ${profile.course.toUpperCase()}</h3>
                                        </p>

                                        <c:choose>
                                            <c:when test="${profile.student.bed == null}">
                                                <p>
                                                <h3>NON-RESIDENT</h3>
                                                </p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>
                                                <h3>ROOM : ${profile.student.bed.number}</h3>
                                                </p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p>
                                            <button type="button" class="btn btn-sm" data-toggle="modal" data-target="#myModal">
                                                EDIT YOUR PROFILE
                                            </button>
                                        </p>
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

<%--Modal--%>
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Complete profile</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                        <form:form acceptCharset="UTF-8" action="${url}" method="post" modelAttribute="studentProfile" cssClass="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="studentRegNo" class="col-sm-3 control-label">Reg No</label>
                                <div class="col-sm-9">
                                    <form:input path="studentRegNo" id="studentRegNo" type="text" cssClass="form-control" placeholder="Registration number" />
                                    <form:input path="id" id="id" type="hidden"/>
                                    <form:errors path="studentRegNo" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="studentRegNo" class="col-sm-3 control-label">Phone No</label>
                                <div class="col-sm-9">
                                    <form:input path="phoneNumber" id="phoneNumber" type="text" cssClass="form-control" placeholder="Phone number" />
                                    <form:input path="id" id="id" type="hidden"/>
                                    <form:errors path="phoneNumber" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="faculty" class="col-sm-3 control-label">Faculty</label>
                                <div class="col-sm-9">
                                    <form:input path="faculty" id="studentRegNo" type="text" cssClass="form-control" placeholder="Faculty(e.g science)" />
                                    <form:errors path="faculty" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="department" class="col-sm-3 control-label">Department</label>
                                <div class="col-sm-9">
                                    <form:input path="department" id="department" type="text" cssClass="form-control" placeholder="Department (e.g Computer science)" />
                                    <form:errors path="department" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="course" class="col-sm-3 control-label">Course</label>
                                <div class="col-sm-9">
                                    <form:input path="course" id="course" type="text" cssClass="form-control" placeholder="Department (e.g Computer science)" />
                                    <form:errors path="course" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gender" class="col-sm-3 control-label">Gender</label>
                                <div class="col-sm-9">
                                    <form:select path="gender" id="gender" type="select" cssClass="form-control">
                                        <form:options/>
                                    </form:select>
                                    <form:errors path="gender" cssClass="form-inline" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-10">
                                    <input class="btn btn-success" type="submit" value="Submit">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--End modal--%>
<!-- start of footer section -->

<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
