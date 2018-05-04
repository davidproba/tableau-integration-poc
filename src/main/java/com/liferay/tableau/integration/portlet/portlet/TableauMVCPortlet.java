package com.liferay.tableau.integration.portlet.portlet;

import com.liferay.tableau.integration.configuration.TableauConfiguration;
import com.liferay.tableau.integration.portlet.constants.TableauMVCPortletKeys;

import java.io.IOException;
import java.util.Map;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;


/**
 * @author David Proba
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=com.liferay.tableau.integration Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TableauMVCPortletKeys.TableauMVC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TableauMVCPortlet extends MVCPortlet {
	
    @Override
    public void doView(RenderRequest renderRequest,
        RenderResponse renderResponse) throws IOException, PortletException {

        renderRequest.setAttribute(
            TableauConfiguration.class.getName(), _configuration);

        super.doView(renderRequest, renderResponse);
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
        TableauConfiguration.class, properties);
    }

    private volatile TableauConfiguration _configuration;	
	
}