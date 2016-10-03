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
        <div class="col-sm-12">
            <form id="eventForm" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-xs-3 control-label">Date</label>
                    <div class="col-xs-5 date">
                        <div class="input-group input-append date" id="datePicker1">
                            <input type="text" class="form-control" name="date" />
                            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Date</label>
                    <div class="col-xs-5 date">
                        <div class="input-group input-append date" id="datePicker">
                            <input type="text" class="form-control" name="date" />
                            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-3">
                        <button type="submit" class="btn btn-default">Validate</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%--js--%>
<script>
    $(document).ready(function() {
        $('#datePicker')
                .datepicker({
                    format: 'mm/dd/yyyy'
                })
                .on('changeDate', function(e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });

        $('#datePicker1')
                .datepicker({
                    format: 'mm/dd/yyyy'
                })
                .on('changeDate', function(e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });

        $('#eventForm').formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required'
                        }
                    }
                },
                date: {
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                        date: {
                            format: 'MM/DD/YYYY',
                            message: 'The date is not a valid'
                        }
                    }
                }
            }
        });
    });
</script>
<%--en js--%>
<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
