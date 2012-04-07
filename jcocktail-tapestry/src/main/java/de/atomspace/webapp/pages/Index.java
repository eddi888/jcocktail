package de.atomspace.webapp.pages;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * Start page of application jcocktail-tapestry.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;

    public Date getCurrentTime()
    {
        return new Date();
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }
    
    public String getFriendlyName(){
		String friendlyName="Barkeeper";
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(!user.startsWith("anonymous")){
			OpenIDAuthenticationToken token = (OpenIDAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
			List<OpenIDAttribute> attributes = token.getAttributes();
			boolean breakAll=false;
			for (OpenIDAttribute attribute : attributes) {
				List<String> values = attribute.getValues();
				for (String value : values) {
					if(attribute.getType().equals("http://axschema.org/namePerson/friendly") && value != null){
						friendlyName=value;
						breakAll=true;
						break;
					}
					if(attribute.getType().equals("http://axschema.org/namePerson/first") && value != null){
						friendlyName=value;
						breakAll=true;
						break;
					}
				}
				if(breakAll) break;
			}
		}else{
			friendlyName="Cocktail-Freund";
		}
		return friendlyName;
	}
}
