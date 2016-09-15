<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>


    <spring:url value="/themes/hsenid/js/customers_table.js" var="js1"/>
    <script src="${js1}"></script>

    <script>
        //datepicker
        $(document).ready(function() {
            $("#fromDate").datepicker({
                "changeMonth":true,
                "changeYear":true

            });

            $("#toDate").datepicker({
                "changeMonth":true,
                "changeYear":true

            });
        });
    </script>

</head>
<body>
<!--<div  id="toolbar">-->
    <div class="col-sm-1"></div>
    <div class="col-sm-10">
        <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="searchCriteria"></legend>

            <div class="form-group">
                <div class="row">
                    <div class="col-xs-8" style="width: 64%  ">
                        <div class="col-xs-2">
                            <label class=" control-label" >
                                Filter By:
                            </label>
                        </div>
                        <!--checkboxes to select-->
                        <div class="col-xs-10" id="checkboxDiv">
                            <div class="col-xs-5">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="1" id="regCheck" onchange="showMe()">Registered Date
                                </label>
                            </div>
                            <div class="col-xs-3">
                                <label class="checkbox-inline">
                                    <input  type="checkbox" value="2" id="nameCheck" onchange="showMe()" checked>Name
                                </label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="3" id="liveCheck" onchange="showMe()">Living Area
                                </label>
                            </div>
                        </div>
                        <!--<div class="col-xs-2"></div>-->
                    </div>

                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                            <input class="form-control"  placeholder=" Name" type="text" id="cname"  >
                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-success" >
                                <span class="glyphicon glyphicon-search"></span> Search
                            </button>
                        </div>
                        <div class="col-xs-4"></div>
                    </div>
                </div>
            </div>

       <!--Filtering elements to be displayed as checkbox is checked     -->
            <div class="form-group">
                <div class="row">
                    <div class="col-xs-8" id="regPeriod"  style="width: 64%; display:none">
                        <div class="col-xs-2"  style="text-align: right">
                            <label class=" control-label">
                                From:
                            </label>
                        </div>
                        <div class="col-xs-10">
                            <div class="col-xs-4">
                                <input class="form-control" id="fromDate" type="text"
                                               placeholder="Click on me" style="width: 130px">
                            </div>
                            <div class="col-xs-1">
                                <label class=" control-label">
                                    To:
                                </label>
                            </div>
                            <div class="col-xs-4">
                                <input class="form-control" id="toDate" placeholder="Click on me"
                                               type="text" style="width: 130px">
                            </div>
                        </div>
                    </div>

                    <!--next half-->
                    <!--name form-group-->
                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                                <select class="form-control" id="city"  style="display: none;">
                                    <option>Colombo</option>
                                    <option>Gampaha</option>
                                    <option>Ja-Ela</option>
                                </select>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
    <div class="col-sm-1"></div>

<table  id="tableCustomer">
</table>

<!--div to include modals-->
<!--<div id="includedContent1"></div>-->


<!--modelto appear when banning a Customer or Staff user -->
<div class="modal fade" id="removeCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span> Block Customer User
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockCustomerMsg">Do you really want to block this User ?</label><br><br>

                    <div align="center">
                        <label id="lblBlockCustomerId">Username : xxxxxx </label><br>
                        <label id="lblBlockCustomerName">Name : yyyyyy </label><br>
                    </div>
                </div>
            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnBlockCustomer">Yes
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockCustomer">
                    No
                </button>
            </div>
        </div>
    </div>
</div>


</body>
</html>