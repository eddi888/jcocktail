/* ******************************************************************************
*    JCocktail - Webapplication for Cocktail Resource Planing and Management.   *
*    Copyright (C) 2011  Edgar Wentzlaff                                        *
*                                                                               *
*    This program is free software: you can redistribute it and/or modify       *
*    it under the terms of the GNU Affero General Public License as             *
*    published by the Free Software Foundation, either version 3 of the         *
*    License, or (at your option) any later version.                            *
*                                                                               *
*    This program is distributed in the hope that it will be useful,            *
*    but WITHOUT ANY WARRANTY; without even the implied warranty of             *
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
*    GNU Affero General Public License for more details.                        *
*                                                                               *
*    You should have received a copy of the GNU Affero General Public License   *
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.      *
*                                                                               *
****************************************************************************** */

package de.atomspace.webapp.component.authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlNativeComponent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class AuthenticationComposer extends GenericForwardComposer {
	private Div loginDiv;
	private Div detailsDiv;
	
	private Label webUserUser;
	private Textbox webUserName;
			
	private Div googleDiv;
	private Div yahooDiv;
	private Div aolDiv;
	private Div myopenidDiv;
	private Div openidDiv;
	
	Groupbox google;
	private static final long serialVersionUID = -8728152638005734417L;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(user==null || user.endsWith("anonymousUser")){
			loginDiv.setVisible(true);
			
			Component googleForm = getOpenIdComponent("google","https://www.google.com/accounts/o8/id",false,null);
			googleDiv.appendChild(googleForm);
			
			Component yahooForm = getOpenIdComponent("yahoo","http://me.yahoo.com/",false,null);
			yahooDiv.appendChild(yahooForm);
			
			Component aolForm = getOpenIdComponent("aol","http://openid.aol.com/{username}",true,"Enter your AOL screenname");
			aolDiv.appendChild(aolForm);
			
			Component myOpenIdForm = getOpenIdComponent("myopenid","http://{username}.myopenid.com/",true,"Enter your MyOpenID username");
			myopenidDiv.appendChild(myOpenIdForm);
			
			Component openIdForm = getOpenIdComponent("openid","{username}",true,"Enter your OpenID");
			openidDiv.appendChild(openIdForm);
		}else{
			detailsDiv.setVisible(true);
			
			webUserUser.setValue(user);
			webUserName.setValue("NOBODY");
		}
	}

	
	
	
	public void onClick$aolImage() {
		this.unvisibleAll();
		aolDiv.setVisible(true);
	}
	
	public void onClick$myopenidImage() {
		this.unvisibleAll();
		myopenidDiv.setVisible(true);
	}
	
	public void onClick$openidImage() {
		this.unvisibleAll();
		openidDiv.setVisible(true);
	}
	
	private void unvisibleAll(){
		aolDiv.setVisible(false);
		myopenidDiv.setVisible(false);
		openidDiv.setVisible(false);
	}
	
	private Component getOpenIdComponent(String provider, String url, boolean inputRequired, String label) {
		Groupbox groupbox = new Groupbox();
		if(label!=null){
			groupbox.appendChild(new Caption(label));
		}
		String inputType="hidden";
		if(inputRequired) inputType="text";
		String content ="";
		content+="<form action=\"/jcocktail-zk/j_spring_openid_security_check\" method=\"post\" name=\"openid_"+provider+"_form\">"; 
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
		HtmlNativeComponent postForm = new HtmlNativeComponent("div",content, "");
		
		groupbox.appendChild(postForm);
		return groupbox;
	}
	
}
