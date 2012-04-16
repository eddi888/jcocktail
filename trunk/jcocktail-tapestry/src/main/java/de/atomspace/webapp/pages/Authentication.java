package de.atomspace.webapp.pages;

import java.util.List;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.openid4java.util.OpenID4JavaDOMParser;
import org.openid4java.util.OpenID4JavaDOMParser;
import org.cyberneko.html.HTMLTagBalancingListener;

import de.atomspace.webapp.component.webuser.Webuser;
import de.atomspace.webapp.component.webuser.service.WebuserService;

public class Authentication {
	
	//@Inject
	//@Autowired
	//WebuserService webuserService;
	

	
	public String getUserName(){
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		return user;
	}
	
	/*
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
			friendlyName="Anonymous";
		}
		return friendlyName;
	}
	*/	
	
}
