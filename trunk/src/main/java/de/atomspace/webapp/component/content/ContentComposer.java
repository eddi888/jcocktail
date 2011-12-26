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

package de.atomspace.webapp.component.content;

import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlNativeComponent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ContentComposer  extends GenericForwardComposer {
	
	private Content content;
	private ContentService contentService;
	private Div exDiv;
	private Div viewDiv;
	private Div editDiv;
	private Div bodyDiv;
	private HtmlNativeComponent html;
	private Window contentWindow;
	private CKeditor bodyCKeditor;
	private Textbox nameTxt;
	private Textbox titleTxt;
	private Textbox robotsTxt;
	private Textbox descriptionTxt;
	private Textbox keywordsTxt;
	
	private Button robotsBtn1;
	private Button robotsBtn2;
	private Button robotsBtn3;
	private static final long serialVersionUID = -8728152638005734417L;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		contentService = (ContentService) SpringUtil.getBean("contentService");
		content = (Content) Executions.getCurrent().getAttribute("content");
		String name = (String) Executions.getCurrent().getAttribute("name");
		
		if(content==null){
			content = new Content(false,false,name,name,name,"new Text");
			contentWindow.setTitle("Page not existing, u can create it now.");
		}else{
			content=contentService.findOneByName(name);
			contentWindow.setTitle(content.getTitle());
		}
		
		loadFields();
	}
	
	private void loadFields() {
		//View-Mode
		exDiv.removeChild(bodyDiv);
		bodyDiv = new Div();
		bodyDiv.setParent(exDiv);
		html = new HtmlNativeComponent("div",content.getBody(), "");
		bodyDiv.appendChild(html);
		
		//Edit-Mode
		nameTxt.setValue(content.getName());
		titleTxt.setValue(content.getTitle());
		robotsTxt.setValue(content.getRobots());
		descriptionTxt.setValue(content.getRobots());
		keywordsTxt.setValue(content.getRobots());
		bodyCKeditor.setValue(content.getBody());
		
	}
	
	private void modeEdit(){
		loadFields();
		viewDiv.setVisible(false);
		editDiv.setVisible(true);
		
	}
	
	private void modeView(){
		loadFields();
		viewDiv.setVisible(true);
		editDiv.setVisible(false);
	}
	
	public void onChange$bodyCKeditor() {
		content.setBody(bodyCKeditor.getValue());
	}
	public void onChange$titleTxt() {
		content.setTitle(titleTxt.getValue());
	}
	public void onChange$nameTxt() {
		content.setName(nameTxt.getValue());
	}
	public void onChange$robotsTxt() {
		content.setRobots(robotsTxt.getValue());
	}
	public void onChange$descriptionTxt() {
		content.setDescription(descriptionTxt.getValue());
	}
	public void onChange$keywordsTxt() {
		content.setKeywords(keywordsTxt.getValue());
	}
	
	public void onClick$editBtn() {
		this.modeEdit();
	}
	
	public void onClick$viewBtn() {
		this.modeView();
	}

	public void onClick$saveBtn() throws InterruptedException {
		contentService.save(content);
		Messagebox.show("saved","INFORMATION",Messagebox.OK,Messagebox.INFORMATION);
	}
	
	public void onClick$robotsBtn1(){
		robotsTxt.setValue(robotsBtn1.getLabel());
		content.setRobots(robotsTxt.getValue());
	}
	
	public void onClick$robotsBtn2(){
		robotsTxt.setValue(robotsBtn2.getLabel());
		content.setRobots(robotsTxt.getValue());
	}
	
	public void onClick$robotsBtn3(){
		robotsTxt.setValue(robotsBtn3.getLabel());
		content.setRobots(robotsTxt.getValue());
	}
}
