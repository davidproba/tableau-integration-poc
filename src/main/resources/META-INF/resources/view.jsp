<%@ include file="init.jsp" %>

<script type="text/javascript" src="<%= tabJS %>"></script> 
<object class="tableauViz" width="100%" height="800" style="display:none;"> 
    <param name="name" value="<%= tabMap %>" /> 
    <param name="ticket" value="<%= Tableau.getTrustedTicket(PortalUtil.getUser(request).getScreenName(),tabURL) %>" /> 
</object> 