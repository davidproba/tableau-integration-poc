package com.liferay.tableau.integration.configuration.action;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.tableau.integration.configuration.TableauConfiguration;
import com.liferay.tableau.integration.portlet.constants.TableauMVCPortletKeys;

@Component(
    configurationPid = "com.liferay.tableau.integration.configuration.TableauConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "javax.portlet.name=" + TableauMVCPortletKeys.TableauMVC
    },
    service = ConfigurationAction.class
)
public class TableauConfigurationAction extends DefaultConfigurationAction {
    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
        throws Exception {

        String tabServer = ParamUtil.getString(actionRequest, "server");
        String tabMap = ParamUtil.getString(actionRequest, "map");
        setPreference(actionRequest, "tabServerURL", tabServer);
        setPreference(actionRequest, "tabMap", tabMap);
        
        System.out.println(tabServer);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
            TableauConfiguration.class.getName(),
            _tableauConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _tableauConfiguration = ConfigurableUtil.createConfigurable(
            TableauConfiguration.class, properties);
    }

    private volatile TableauConfiguration _tableauConfiguration;
}
