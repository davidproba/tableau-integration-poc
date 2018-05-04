<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.tableau.integration.configuration.TableauConfiguration" %>
<%@ page import="com.liferay.tableau.integration.handler.Tableau" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />


<%
TableauConfiguration configuration = (TableauConfiguration) renderRequest.getAttribute(TableauConfiguration.class.getName());
String tabURL = StringPool.BLANK;
String tabMap = StringPool.BLANK;
String tabJS = StringPool.BLANK;

if (Validator.isNotNull(configuration)) {
	tabURL =
		portletPreferences.getValue("tabServerURL", configuration.tabServerURL());
	tabMap =
		portletPreferences.getValue("tabMap", configuration.tabMap());
	tabJS = tabURL + "/javascripts/api/viz_v1.js";
}


%>