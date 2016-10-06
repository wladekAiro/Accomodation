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
        <h3 class="box-title">HOSTEL ADMIN</h3>
        <div class="box-tools">
            <%--<div class="input-group">--%>
                <%--<input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>--%>
                <%--<div class="input-group-btn">--%>
                    <%--<button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div><!-- /.box-header -->
    <div class="box-body">
        <h3>Welcome To Admin Panel</h3>
        <div class="col-sm-6">
            <div class="box">
                <div class="box-header">
                    <div class="box-title">
                        Semester dates
                    </div>
                </div>
                <div class="box-body">
                    <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                        <form:form acceptCharset="UTF-8" action="/admin/setDates" method="post" modelAttribute="semester" cssClass="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="semStartDate" class="col-sm-3 control-label">Semester</label>
                                <div class="col-sm-9 date">
                                    <div class="input-group input-append date" id="semCount">
                                        <form:select path="semCount" cssClass="form-control">
                                            <form:options/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="semStartDate" class="col-sm-3 control-label">Begining</label>
                                <div class="col-sm-9 date">
                                    <div class="input-group input-append date" id="semStartDate">
                                        <form:input path="semStartDate" id="itemName" name="date" type="text" cssClass="form-control" placeholder="Semester start"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                            <form:input path="id" id="id" type="hidden"/>
                                            <form:errors path="semStartDate" cssClass="form-inline" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="semEndDate" class="col-sm-3 control-label">Ending</label>
                                <div class="col-sm-9 date">
                                    <div class="input-group input-append date" id="semEndDate">
                                        <form:input path="semEndDate" id="itemName" name="date" type="text" cssClass="form-control" placeholder="Semester End" />
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                        <form:errors path="semEndDate" cssClass="form-inline" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="offSessionDate" class="col-sm-3 control-label">OffSession Start</label>
                                <div class="col-sm-9 date">
                                    <div class="input-group input-append date" id="offSessionDate">
                                        <form:input path="offSessionDate" id="itemName" name="date" type="text" cssClass="form-control" placeholder="OffSession date"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                        <form:errors path="offSessionDate" cssClass="form-inline" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-10">
                                    <input class="btn btn-success" type="submit" value="Set Dates">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-6">

        </div>
    </div>
</div>

<%--js--%>
<script>
    $(document).ready(function() {
        $('#semStartDate')
                .datepicker({
                    format: 'mm/dd/yyyy'
                })
                .on('changeDate', function(e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });

        $('#semEndDate')
                .datepicker({
                    format: 'mm/dd/yyyy'
                })
                .on('changeDate', function(e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
        $('#offSessionDate')
                .datepicker({
                    format: 'mm/dd/yyyy'
                })
                .on('changeDate', function(e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
    });
</script>
<%--en js--%>
<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
