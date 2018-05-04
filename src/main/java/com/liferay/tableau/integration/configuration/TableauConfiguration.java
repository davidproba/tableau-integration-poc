package com.liferay.tableau.integration.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
		category="display-content",
		scope=ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(id="com.liferay.tableau.integration.configuration.TableauConfiguration")
public interface TableauConfiguration {

	@Meta.AD(required=false)
	public String tabServerURL();
	
	@Meta.AD(required=false)
	public String tabMap();
}
