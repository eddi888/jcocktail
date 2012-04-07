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

package de.atomspace.webapp.core;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.atomspace.webapp.component.content.Content;
import de.atomspace.webapp.component.content.ContentService;

@Controller
@RequestMapping("/")
public class SpringController implements ApplicationContextAware{
	ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage(ModelMap model) {
		model.put("page", "welcome/welcome.jsp");
		return "pages/index.jsp";
	}
	
	@RequestMapping(value = "/content/{name}", method = RequestMethod.GET)
	public String getContentPage(@PathVariable("name") String name, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		ContentService contentService = (ContentService) context.getBean("contentService");
		Content content = contentService.findOneByName(name);
		if(content==null) response.setStatus(404);
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.put("name", name);
			model.put("content", content);
			model.put("page", "content/content.zul");
			return "pages/index.jsp";
		}else{
			model.put("name", name);
			if(content==null) content = new Content(true,false,"404","404","HTTP ERROR 404","NOT_FOUND");
			model.put("content", content);
			model.put("page", "content/content.jsp");
			return "pages/index.jsp";
		}
	}
	
	@RequestMapping(value = "/cocktails", method = RequestMethod.GET)
	public String getCockailListPage(@RequestParam(value="id", required=false) boolean error, ModelMap model, HttpServletRequest request) {
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("page", "cocktail/cocktail-list.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "cocktail/cocktail-list.jsp");
			List<String> list = new ArrayList<String>();
			list.add("ROW01");
			list.add("ROW02");
			list.add("ROW03");
			list.add("ROW04");
			list.add("ROW05");
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}
	
	@RequestMapping(value = "/cocktail/{id}", method = RequestMethod.GET)
	public String getCockailDetailPage(@PathVariable("id") String id, ModelMap model, HttpServletRequest request) {
		model.put("id", id);
		
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("page", "cocktail/cocktail-detail.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "cocktail/cocktail-detail.jsp");
			List<String> list = new ArrayList<String>();
			list.add("ROW01");
			list.add("ROW02");
			list.add("ROW03");
			list.add("ROW04");
			list.add("ROW05");
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public String getIngredientListIndex(ModelMap model, HttpServletRequest request) {
		//return "forward:/web/ingredients/0";
		return "redirect:/web/ingredients/0";
	}
	
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET)
	public String getIngredientListPage(@PathVariable("id") int id, ModelMap model, HttpServletRequest request) {
		int limit = 20;
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("id", id);
			model.put("limit", 20);
			model.put("page", "ingredient/ingredient-list.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "ingredient/ingredient-list.jsp");
			List<String> list = new ArrayList<String>();
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
	public String getIngredientDetailPage(@PathVariable("id") String id, ModelMap model, HttpServletRequest request) {
		model.put("id", id);
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("page", "ingredient/ingredient-detail.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "ingredient/ingredient-detail.jsp");
			List<String> list = new ArrayList<String>();
			list.add("ROW01");
			list.add("ROW02");
			list.add("ROW03");
			list.add("ROW04");
			list.add("ROW05");
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}
	
	@RequestMapping(value = "/units", method = RequestMethod.GET)
	public String getUnitListPage(@RequestParam(value="id", required=false) boolean error, ModelMap model, HttpServletRequest request) {
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("page", "unit/unit-list.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "unit/unit-list.jsp");
			List<String> list = new ArrayList<String>();
			list.add("ROW01");
			list.add("ROW02");
			list.add("ROW03");
			list.add("ROW04");
			list.add("ROW05");
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}
	
	@RequestMapping(value = "/unit/{id}", method = RequestMethod.GET)
	public String getUnitDetailPage(@PathVariable("id") String id, ModelMap model, HttpServletRequest request) {
		model.put("id", id);
		if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")){
			//FOR REALY USERS
			model.put("page", "unit/unit-detail.zul");
			return "pages/index.jsp";
		}else{
			//FOR SEO ROBOTs, anonymousUsers and no JavaScript-Support
			model.put("page", "unit/unit-detail.jsp");
			List<String> list = new ArrayList<String>();
			list.add("ROW01");
			list.add("ROW02");
			list.add("ROW03");
			list.add("ROW04");
			list.add("ROW05");
			model.put("list", list);
			return "pages/index.jsp";	
		}
	}
	
	@RequestMapping(value = "/myinventory", method = RequestMethod.GET)
	public String getMyInentoryPage(ModelMap model) {
		model.put("page", "inventory/inventory.zul");
		return "pages/index.jsp";
	}

	@RequestMapping(value = "/mypurchase", method = RequestMethod.GET)
	public String getMyPurchasePage(ModelMap model) {
		model.put("page", "purchase/purchase.zul");
		return "pages/index.jsp";
	}

	@RequestMapping(value = "/mypotential", method = RequestMethod.GET)
	public String getMyPotenzialPage(ModelMap model) {
		model.put("page", "potential/potential.zul");
		return "pages/index.jsp";
	}
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public String getAuthenticationPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if (error == true) {
			model.put("error", "You have entered an invalid credentials!");
		} else {
			model.put("error", "");
		}
		
		model.put("page", "authentication/authentication.zul");
		return "pages/index.jsp";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage(ModelMap model) {
		model.put("error", "You have entered an invalid credentials!");
		model.put("page", "authentication/deniedpage.jsp");
		return "pages/index.jsp";
	}

	
}
