<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="de" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="${content.description}" />
<meta name="keywords" content="${content.keywords}" />
<meta name="robots" content="${content.robots}" />
<meta name="language" content="de" />
<meta name="copyright" content="http://www-jcocktail.de" />
<meta name="generator" content="Eclipse Helios Service Release 1" />
<link href="/favicon.ico" rel="shortcut icon" />


<title>Window Mode Demo${content.title}</title>
<z:zkhead />
</head>
<body>

	<center>
		<!-- Page Header Start-->
		<div class="z-window-embedded" style="width: 800px;" id="index">
			<div class="z-window-embedded-tl">
				<div class="z-window-embedded-tr"></div>
			</div>
			<div class="z-window-embedded-cl">
				<div class="z-window-embedded-cr">
					<div class="z-window-embedded-cm">
						<div class="z-window-embedded-cnt" >
							<!-- Page Header End -->



							<!-- NAVIGATION START -->
							<c:url value="/resources/images/" var="imgUrl" />
							<c:url value="/web/" var="webUrl" />
							<div align="center" id="navigation">
								<div class="z-toolbar" style="height: 74px;" >
									<div class="z-toolbar-body z-toolbar-start" >
										<div title="Home" class="z-toolbarbutton"
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt" style="font-weight: bold;">
													<img align="absmiddle" width="72px" height="72px"
														src="${imgUrl}banner_cocktail.png">
												</div>
											</div>
										</div>
										<img src="${imgUrl}navi_seperator.png" >
										<div title="Rezepte" class="z-toolbarbutton" 
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}cocktails'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_recipe.png"><br>Rezepte
												</div>
											</div>
										</div>
										<div title="Zutaten" class="z-toolbarbutton" 
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}ingredients'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_ingredient.png"><br>Zutaten
												</div>
											</div>
										</div>
										<div title="Einheiten" class="z-toolbarbutton" 
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}units'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_unit.png"><br>Einheiten
												</div>
											</div>
										</div>
										<img src="${imgUrl}navi_seperator.png" id="eIBQe">
										<div title="Barbestand" class="z-toolbarbutton"
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}myinventory'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_inventory.png"><br>Barbestand
												</div>
											</div>
										</div>
										<div title="Potenzial" class="z-toolbarbutton" 
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}mypotential'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_potential.png"><br>Potenzial
												</div>
											</div>
										</div>
										<div title="Einkaufszettel" class="z-toolbarbutton" 
											style="font-weight: bold;" 
											onclick="window.location='${webUrl}mypurchase'"
											onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
											onmouseout="this.className='z-toolbarbutton'">
											<div class="z-toolbarbutton-body">
												<div class="z-toolbarbutton-cnt">
													<img align="absmiddle" src="${imgUrl}navi_shopinglist.png"><br>Einkaufszettel
												</div>
											</div>
										</div>
										<img src="${imgUrl}navi_seperator.png" >
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<div title="Webuser" class="z-toolbarbutton" 
												style="font-weight: bold;" 
												onclick="window.location='${webUrl}myinventory'"
												onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
												onmouseout="this.className='z-toolbarbutton'">
												<div class="z-toolbarbutton-body">
													<div class="z-toolbarbutton-cnt">
														<img align="absmiddle" src="${imgUrl}navi_webuser.png" width="48px" height="48px" ><br>Webuser
													</div>
												</div>
											</div>
											<img src="${imgUrl}navi_seperator.png" >
										</sec:authorize>
										<sec:authorize access="hasRole('ROLE_USER')">
											<div title="Einstellungen" class="z-toolbarbutton" 
												style="font-weight: bold;" 
												onclick="window.location='${webUrl}myinventory'"
												onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
												onmouseout="this.className='z-toolbarbutton'">
												<div class="z-toolbarbutton-body">
													<div class="z-toolbarbutton-cnt">
														<img align="absmiddle" src="${imgUrl}navi_setting.png"><br>Einstellungen
													</div>
												</div>
											</div>
											<div title="Abmelden" class="z-toolbarbutton" 
												style="font-weight: bold;" 
												onclick="window.location='${webUrl}logout'"
												onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
												onmouseout="this.className='z-toolbarbutton'">
												<div class="z-toolbarbutton-body">
													<div class="z-toolbarbutton-cnt">
														<img align="absmiddle" src="${imgUrl}navi_logout.png"><br>Abmelden
													</div>
												</div>
											</div>
										</sec:authorize>
										<sec:authorize access="isAnonymous()">
											<div title="Abmelden" class="z-toolbarbutton" 
												style="font-weight: bold;" 
												onclick="window.location='${webUrl}authentication'"
												onmouseover="this.className='z-toolbarbutton z-toolbarbutton-over'"
												onmouseout="this.className='z-toolbarbutton'">
												<div class="z-toolbarbutton-body">
													<div class="z-toolbarbutton-cnt">
														<img align="absmiddle" src="${imgUrl}navi_logout.png"><br>Anmelden
													</div>
												</div>
											</div>
										</sec:authorize>
									</div>
									<div class="z-clear"></div>
								</div>
							</div>
							<!-- NAVIGATION END -->

							<!-- CONTENT Start-->
							<div class="z-separator-hor" id="separator">&nbsp;</div>
							<div align="left" id="content">
								<jsp:include page="${page}" />
							</div>
							<!-- CONTENT End-->

							<!-- WINDOWS-END Start -->
						</div>
					</div>
				</div>
			</div>
			<div class="z-window-embedded-bl">
				<div class="z-window-embedded-br"></div>
			</div>
		</div>
		<!-- WINDOW-END End -->
	</center>

<!-- FOOT LINKS Start -->
	<div align="center" >
		<c:url value="/web/" var="myWebUrl" />
		<span  style="font-family: Arial, Helvetica, Geneva; font-weight: normal; font-size: x-small; color: grey; text-decoration: none; a: link color : grey;">
			...:::|:::... <a href="${myWebUrl}" style="color: grey; text-decoration: none;">Welcome</a>
			...:::|:::... <a href="${myWebUrl}cocktails" style="color: grey; text-decoration: none;">Rezepte</a> 
			...:::|:::... <a href="${myWebUrl}ingredients" style="color: grey; text-decoration: none;">Zutaten</a> 
			...:::|:::... <a href="${myWebUrl}units" style="color: grey; text-decoration: none;">Einheiten</a>
			...:::|:::... <a href="${myWebUrl}content/legalnotice" style="color: grey; text-decoration: none;">Impressum</a>
			...:::|:::... <a href="${myWebUrl}content/termsofuse" style="color: grey; text-decoration: none;">Nutzungsbedingung</a> 
			...:::|:::... <a href="${myWebUrl}sitemap" style="color: grey; text-decoration: none;">Sidemap</a>
			...:::|:::...</span>
	</div>
<!-- FOOT LINKS End -->













</body>
</html>