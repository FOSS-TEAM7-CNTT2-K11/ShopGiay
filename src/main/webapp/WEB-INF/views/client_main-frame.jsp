<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>ZuZuShoe &mdash;${title }</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/owl.theme.default.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/aos.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/_client/css/my-css.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/_client/js/angular.min.js"></script>
    <script type="text/javascript"src="${pageContext.request.contextPath }/static/_client/js/my-angular.js"></script>
    
  </head>
  <body style="font-family: arial;">
  
  <div class="site-wrap">
  
  <!-- header -->
  <jsp:include page="/WEB-INF/layouts/client_header.jsp"></jsp:include>
  <!-- end header -->
    
  <!-- content -->
  <jsp:include page="/WEB-INF/views/${page }.jsp"></jsp:include>
  <!-- end content -->

    <!-- footer -->
    <jsp:include page="/WEB-INF/layouts/client_footer.jsp"></jsp:include>
    <!-- end footer -->
  </div>

  <script src="${pageContext.request.contextPath }/static/_client/js/jquery-3.3.1.min.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/jquery.magnific-popup.min.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/aos.js"></script>

  <script src="${pageContext.request.contextPath }/static/_client/js/main.js"></script>
  <script src="${pageContext.request.contextPath }/static/_client/js/my-js.js"></script>
    
  </body>
</html>