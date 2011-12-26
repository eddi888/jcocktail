<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<z:page></z:page>

<div style="width: 100%;" >
	<div class="z-window-embedded" >
		<div class="z-window-embedded-tl">
			<div class="z-window-embedded-tr"></div>
		</div>
		<div class="z-window-embedded-hl">
			<div class="z-window-embedded-hr">
				<div class="z-window-embedded-hm">
					<div class="z-window-embedded-header" >ZUTATEN</div>
				</div>
			</div>
		</div>
		<div class="z-window-embedded-cl">
			<div class="z-window-embedded-cr">
				<div class="z-window-embedded-cm">
					<div class="z-window-embedded-cnt" id="oGuP0-cave">
						<span class="z-label">
							<c:forEach var="row" items="${list}">
								${row}<br />
							</c:forEach>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="z-window-embedded-bl">
			<div class="z-window-embedded-br"></div>
		</div>
	</div>
</div>


