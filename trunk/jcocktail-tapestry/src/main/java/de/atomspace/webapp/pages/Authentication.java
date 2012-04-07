package de.atomspace.webapp.pages;

import java.util.List;

import org.apache.tapestry5.annotations.OnEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.openid4java.util.OpenID4JavaDOMParser;
import org.openid4java.util.OpenID4JavaDOMParser;
import org.cyberneko.html.HTMLTagBalancingListener;

public class Authentication {
	
	public String getUserName(){
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		return user;
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
			friendlyName="Anonymous";
		}
		return friendlyName;
	}
	
	public String getGoogle(){
		return getOpenIdComponent("google","https://www.google.com/accounts/o8/id",false,null);
	}
		
	private String getOpenIdComponent(String provider, String url, boolean inputRequired, String label) {
		//Groupbox groupbox = new Groupbox();
		//if(label!=null){
		//	groupbox.appendChild(new Caption(label));
		//}
		String inputType="hidden";
		if(inputRequired) inputType="text";
		String content ="";
		content+="<form action=\"/jcocktail-web/j_spring_openid_security_check\" method=\"post\" name=\"openid_"+provider+"_form\">"; 
		content+="<input type=\"hidden\" name=\"action\" value=\"verify\" /> ";
		content+="<input id=\"openid_"+provider+"_identifier\" name=\"openid_identifier\" type=\"hidden\" value=\""+url+"\" />";
		//content+=label;
		content+="<input id=\"openid_name\" type=\""+inputType+"\" size=80 onchange=\"openid_"+provider+"_identifier.value='"+url+"'.replace('{username}',this.value);\" value=\"\" />";
		if(inputRequired){
			content+="<input type=\"submit\" value=\"Sign-In\" />";
		}else{
			content+="<img src=\"../resources/images/openid_"+provider+".png\" onclick=\"openid_"+provider+"_form.submit();\">";
		}
		content+="</form>";
		//HtmlNativeComponent postForm = new HtmlNativeComponent("div",content, "");
		
		//groupbox.appendChild(postForm);
		//return groupbox;
		return content;
	}
	
	
}
