<%@page import="org.springframework.security.openid.OpenIDAttribute"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.security.openid.OpenIDAuthenticationToken"%>
<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@page import="java.util.Collection"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
//Collection<GrantedAuthority> auts = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//for (GrantedAuthority grantedAuthority : auts) {
//	System.out.println(grantedAuthority.getAuthority());
//}

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
%>



<div class="z-window-embedded" >
	<div class="z-window-embedded-tl">
		<div class="z-window-embedded-tr"></div>
	</div>
	<div class="z-window-embedded-hl">
		<div class="z-window-embedded-hr">
			<div class="z-window-embedded-hm">
				<div class="z-window-embedded-header" >WELCOME</div>
			</div>
		</div>
	</div>
	<div class="z-window-embedded-cl">
		<div class="z-window-embedded-cr">
			<div class="z-window-embedded-cm">
				<div class="z-window-embedded-cnt" >
					<div style="">
						<div>
							<div>
								<!-- CONTENT START -->
								<center><b>WELCOME</b></center>
								<span class="z-label" id="f5BV1">
								Hello <%=friendlyName%>, <br />
								This Page is under construction. U can manage your Cocktails.<br />
								</span>
								<!-- CONTENT END -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="z-window-embedded-bl">
		<div class="z-window-embedded-br"></div>
	</div>
</div>