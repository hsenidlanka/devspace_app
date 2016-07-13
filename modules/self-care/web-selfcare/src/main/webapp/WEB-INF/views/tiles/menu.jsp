<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:bundle basename="messages">
    <script type="text/javascript" src="<c:url value="/resources/js/sdmenu.js" />">
        //
    </script>

    <div id="my_menu" class="sdmenu">

        <sec:authorize
                ifAnyGranted="ROLE_CAMPAIGN_CREATION_NORMAL,ROLE_CAMPAIGN_CREATION_FAST_FLOW,ROLE_CAMPAIGN_CREATION_BULK_REWARDS,ROLE_CAMPAIGN_SEARCH">
            <div>
                <span><fmt:message key="side.menu.campaign.title"/></span>

                <sec:authorize ifAllGranted="ROLE_CAMPAIGN_CREATION_NORMAL,ROLE_CAMPAIGN_CREATION_BULK_REWARDS">
                    <a tabindex="-1" href='#sccf' id="cm_rule_create" name='modal'>&nbsp;&nbsp;<fmt:message
                            key="side.menu.campaign.create"/></a>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_CAMPAIGN_CREATION_NORMAL"
                               ifNotGranted="ROLE_CAMPAIGN_CREATION_BULK_REWARDS">
                    <a tabindex="-1" href="<c:url value="/createCampaign/main.html"/>"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.campaign.create"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_CAMPAIGN_SEARCH">
                    <a tabindex="-1" href="<c:url value="/campaign/manageCampaign.html?page=Manage Campaigns"/>"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.campaign.manage"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_DRAFT_CAMPAIGN_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/campaign/manageDraftCampaign.html?page=Manage Draft Campaigns"/>"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.draft.campaign.manage"/></a>
                </sec:authorize>

            </div>
        </sec:authorize>

        <sec:authorize
                ifAnyGranted="ROLE_PENDING_NOTIFICATION_SEARCH,ROLE_PENDING_REWARDS_SEARCH,ROLE_PENDING_CAMPAIGN_SEARCH,ROLE_PENDING_PROFILE_SEARCH">
            <div>
                <span><fmt:message key="side.menu.pending.approval.title"/></span>
                <sec:authorize ifAnyGranted="ROLE_PENDING_CAMPAIGN_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/pending/campaignApprovals.html?page=Pending Campaign Approvals" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.pending.approval.campaigns"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_PENDING_PROFILE_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/pending/profileApprovals.html?page=Pending Profile Approvals" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.pending.approval.profiles"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_PENDING_NOTIFICATION_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/pending/notificationApprovals.html?page=Pending Pre Notification Approvals" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.pending.approval.notification"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_PENDING_REWARDS_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/pending/rewardApprovals.html?page=Pending Reward Approvals" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.pending.approval.rewards"/></a>
                </sec:authorize>


            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_EVENT_FILTER_ADD,ROLE_EVENT_FILTER_SEARCH">
            <div>
                <span><fmt:message key="side.menu.event.category.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_EVENT_FILTER_ADD">
                    <a tabindex="-1" href="<c:url value="/event/addFilter.html?page=Add Event Filter" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.event.category.add"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_EVENT_FILTER_SEARCH">
                    <a tabindex="-1" href="<c:url value="/event/manageFilter.html?page=Manage Event Filter" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.event.category.manage"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>


        <sec:authorize ifAnyGranted="ROLE_EVENT_ALIAS_ADD,ROLE_EVENT_ALIAS_SEARCH">
            <div>
                <span><fmt:message key="side.menu.event.alias.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_EVENT_ALIAS_ADD">
                    <a tabindex="-1" href="<c:url value="/event/addAlias.html?page=Add Event Alias" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.event.alias.add"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_EVENT_ALIAS_SEARCH">
                    <a tabindex="-1" href="<c:url value="/event/manageEventAlias.html?page=Manage Event Alias" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.event.alias.manage"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_SUBSCRIBER_AGGREGATION_ADD,ROLE_SUBSCRIBER_AGGREGATION_SEARCH">
            <div>
                <span><fmt:message key="side.menu.subscriber.category.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_SUBSCRIBER_AGGREGATION_ADD">
                    <a tabindex="-1"
                       href="<c:url value="/subscriber/addAggregation.html?page=Add Subscriber Aggregation" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.subscriber.category.add"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_SUBSCRIBER_AGGREGATION_SEARCH">
                    <a tabindex="-1"
                       href="<c:url value="/subscriber/manageAggregation.html?page=Manage Subscriber Aggregation" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.subscriber.category.manage"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_PROFILE_ADD,ROLE_PROFILE_SEARCH">
            <div>
                <span><fmt:message key="side.menu.profile.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_PROFILE_ADD">
                    <a tabindex="-1" href="<c:url value="/profile/createProfile.html?page=Profile Management" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.profile.create"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_PROFILE_SEARCH">
                    <a tabindex="-1" href="<c:url value="/profile/manageProfiles.html?page=Manage Profiles" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.profile.manage"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_BLACK_LISTED_USER_ADD,ROLE_WHITE_LISTED_USER_ADD">
            <div>
                <span><fmt:message key="side.menu.white.listed.user.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_WHITE_LISTED_USER_ADD">
                    <a tabindex="-1" href="<c:url value="/whiteList/addList.html?page=White List Users" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.white.listed.user.white.list"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_BLACK_LISTED_USER_ADD">
                    <a tabindex="-1" href="<c:url value="/blackList/addList.html?page=Black List Users" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.white.listed.user.black.list"/></a>
                </sec:authorize>


            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_POINT_SCHEME_VIEW">
            <div>
                <span><fmt:message key="side.menu.points.scheme.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_POINT_SCHEME_VIEW">
                    <a tabindex="-1" href="<c:url value="/points/editScheme.html?page=Point Scheme" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.points.scheme.configure"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>

        <sec:authorize ifAnyGranted="ROLE_EVENT_SOURCE_AD,ROLE_EVENT_SOURCE_SEARCH">
            <div>
                <span><fmt:message key="side.menu.event.source.title"/></span>

                <sec:authorize ifAnyGranted="ROLE_EVENT_SOURCE_ADD">
                    <a tabindex="-1" href="<c:url value="/event/addSource.html?page=Add Event Source" />">
                        &nbsp;&nbsp;<fmt:message key="side.menu.event.source.add"/></a>
                </sec:authorize>

                <sec:authorize ifAnyGranted="ROLE_EVENT_SOURCE_SEARCH">
                    <a tabindex="-1" href="<c:url value="/event/manageSource.html?page=Manage Event Source" />"> &nbsp;&nbsp;<fmt:message
                            key="side.menu.event.source.manage"/></a>
                </sec:authorize>
            </div>
        </sec:authorize>

            <%--        <sec:authorize ifAnyGranted="ROLE_SMS_NOTIFICATION_REPORT,ROLE_SUBSCRIBER_VIEW_REPORT">
                        <div>
                            <span><fmt:message key="side.menu.reports.title"/></span>

                            <sec:authorize ifAnyGranted="ROLE_SMS_NOTIFICATION_REPORT">
                                <a tabindex="-1" href="<c:url value="/reports/smsNotification.html?page=SMS Notification Based Reports" />"> &nbsp;&nbsp;<fmt:message key="side.menu.reports.sms.notification"/></a>
                            </sec:authorize>

                            <sec:authorize ifAnyGranted="ROLE_SUBSCRIBER_VIEW_REPORT">
                                <a tabindex="-1" href="<c:url value="/reports/subscriberView.html?page=Subscriber View"/>"> &nbsp;&nbsp;<fmt:message key="side.menu.reports.subscriber.view"/></a>
                            </sec:authorize>

                            <sec:authorize ifAnyGranted="ROLE_SUBSCRIBER_VIEW_REPORT">
                                <a tabindex="-1" target="_blank" href="<c:url value="/reports/customerCare.html?page=Subscriber Query" />"> &nbsp;&nbsp;<fmt:message key="side.menu.reports.subscriber.query"/></a>
                            </sec:authorize>

                        </div>
                    </sec:authorize>--%>
    </div>
</fmt:bundle>
