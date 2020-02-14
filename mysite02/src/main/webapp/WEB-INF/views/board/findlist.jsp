<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board?a=find&page=1"
					method="post">
					<input type="text" id="kwd" name="kwd"
						placeholder='제목을 입력하세요' required="required"> <input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:set var='count' value='${(param.page-1)*5 }'></c:set>
					<c:if test="${param.page <= 0}">
						<c:set var='count' value='0'></c:set>
					</c:if>
					<c:if test="${count <= 0 }">
						<c:set var='count' value='0'></c:set>
					</c:if>
					<c:set var='listCount' value='${fn:length(list) }'></c:set>
					<c:set var='listSize' value='${fn:length(list) }'></c:set>

					<c:choose>
						<c:when test="${listCount%5 == 0}">
							<c:set var='limit' value='${listCount/5 }'></c:set>
						</c:when>
						<c:when test="${listCount%5 != 0 }">
							<c:set var='limit' value='${(listCount/5)+1 }'></c:set>
						</c:when>
					</c:choose>

					<c:forEach items='${list }' begin='${count }' end='${count+4 }'
						var='vo' varStatus='status'>
						<tr>
							<td>${listCount - status.index }</td>
							<c:if test="${vo.depth != 0 }">
								<c:set var='image' value='/mysite02/assets/images/reply.png'></c:set>
							</c:if>
							<c:if test="${vo.depth == 0 }">
								<c:set var='image' value=''></c:set>
							</c:if>
							<c:choose>
								<c:when test="${vo.state eq 'no' }">
									<td style="text-align:left; padding-left:${20*vo.depth }px">
										<img src='${image }'><a
										href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a>
									</td>
								</c:when>
								<c:when test="${vo.state eq 'yes' }">
									<td style="text-align:left; padding-left:${20*vo.depth }px">
										<img src='${image }'>${vo.title }
									</td>
								</c:when>
							</c:choose>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<c:choose>
								<c:when
									test="${not empty authUser && (authUser.no eq vo.userNo) && (vo.state eq 'no') }">
									<td><a
										href="${pageContext.request.contextPath }/board?a=deleteform&no=${vo.no }&num=${num+2 }&state=${vo.state }"
										class="del">삭제</a></td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<c:set var='select' value='${param.page }'></c:set>
				<div class="pager">
					<ul>
						<c:if test="${!(param.page <= 1) }">
							<li><a
								href="${pageContext.request.contextPath }/board?a=find&page=${param.page-1 }&kwd=${kwd }">◀</a></li>
						</c:if>

						<c:forEach begin='${select}' end='${select+4 }' var='i' step='1'>
							<c:choose>
								<c:when test="${select == i }">
									<li class="selected"><a style="color: red"
										href="${pageContext.request.contextPath }/board?a=find&page=${i }&kwd=${kwd }">${i }</a></li>
								</c:when>
								<c:when test="${limit >= i }">
									<li><a
										href="${pageContext.request.contextPath }/board?a=find&page=${i }&kwd=${kwd }">${i }</a></li>
								</c:when>
								<c:when test="${limit <= i }">
									<li>${i }</li>
								</c:when>
							</c:choose>
						</c:forEach>

						<fmt:parseNumber var='numberlimit' integerOnly='true'
							type='number' value='${limit }'></fmt:parseNumber>
						<c:choose>
							<c:when test="${numberlimit == select }">
								<li><a
									href="${pageContext.request.contextPath }/board?a=find&page=${param.page+1 }&kwd=${kwd }"></a></li>
							</c:when>
							<c:when test="${numberlimit != select }">
								<li><a
									href="${pageContext.request.contextPath }/board?a=find&page=${param.page+1 }&kwd=${kwd }">▶</a></li>
							</c:when>
						</c:choose>

					</ul>
				</div>

				<!-- pager 추가 -->
				<c:choose>
					<c:when test="${not empty authUser }">
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/board?a=writeform"
								id="new-book">글쓰기</a>
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>