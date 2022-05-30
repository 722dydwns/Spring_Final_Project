<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value='${pageContext.request.contextPath}/'/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>기말 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action='${root }board/write_pro' method='post' modelAttribute="writeContentBean" enctype="multipart/form-data">
						<form:hidden path="content_board_idx"/>
						<div class="form-group">
							<form:label path="content_name">상품명</form:label>
							<form:input path="content_name" class='form-control'/>
							<form:errors path='content_name' style='color:red' />
						</div>
						<div class="form-group">
							<form:label path="content_price">가격</form:label>
							<form:input path="content_price" class='form-control'/>
							<form:errors path='content_price' style='color:red'/>
						</div>
						<div class="form-group">
							<form:label path="content_count">상품수량</form:label>
							<form:input path="content_count" class='form-control'/>
							<form:errors path='content_count' style='color:red'/>
						</div>
						
						<div class="form-group">
							<div class="text-right">
								<form:button class='btn btn-primary'>ADD</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>
    