package de.atomspace.webapp.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import de.atomspace.webapp.component.webuser.Webuser;
import de.atomspace.webapp.component.webuser.service.WebuserDetailsService;
import de.atomspace.webapp.component.webuser.service.WebuserService;

/**
 * Layout component for pages of application jcocktail-tapestry.
 */
@Import(stylesheet = "context:layout/layout.css")
public class Layout
{
	
	@SessionState(create=false)
	private Integer recipeListNumber;
	
	public boolean isAnonymousUser(){
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		return user.equals("anonymousUser");
	}
	
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;


    public String getClassForPageName()
    {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "current_page_item"
                : null;
    }

    public String[] getPageNames()
    {
        return new String[]{"Index"};
    }

	public Integer getRecipeListNumber() {

		return recipeListNumber;
	}

	public void setRecipeListNumber(Integer recipeListNumber) {
		this.recipeListNumber = recipeListNumber;
	}
}
