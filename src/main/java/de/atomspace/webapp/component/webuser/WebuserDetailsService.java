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

package de.atomspace.webapp.component.webuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class WebuserDetailsService implements UserDetailsService {
	private WebuserService webuserService;

    public WebuserDetailsService(WebuserService webuserService) {
        this.webuserService = webuserService;
    }
    
	@Override
	public UserDetails loadUserByUsername(final String user) throws UsernameNotFoundException, DataAccessException {
		final Webuser webuser = webuserService.findOneByUserOrInitOne(user);
		
		UserDetails ud = new UserDetails() {
			static final long serialVersionUID = 4500702149295269256L;
			@Override public boolean isEnabled() { return true;}
			@Override public boolean isCredentialsNonExpired() { return true;}
			@Override public boolean isAccountNonLocked() { return true; }
			@Override public boolean isAccountNonExpired() { return true; }
			@Override public String getUsername() { return user;	}
			@Override public String getPassword() { return ""; }
			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
				
				if(user.equals("https://www.google.com/accounts/o8/id?id=AItOawlpXWsMBjKUFfkiJJjTW0pjTdX8FW0RL74")){ //INITIAL ADMIN
					listOfAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				}
				
				List<String> roles = webuser.getRoles();
				for (String role : roles) {
					listOfAuthorities.add(new GrantedAuthorityImpl(role));
				}
				return listOfAuthorities;
			}
		};
		return ud;
	}
}
