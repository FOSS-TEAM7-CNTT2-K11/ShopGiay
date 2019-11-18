<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<header class="site-navbar" role="banner">
      <div class="site-navbar-top">
        <div class="container">
          <div class="row align-items-center">

            <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
              <form action="" class="site-block-top-search">
                <span class="icon icon-search2"></span>
                <input type="text" class="form-control border-0 my-search-input" placeholder="Search">
              </form>
            </div>

            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
              <div class="site-logo">
                <a href="${pageContext.request.contextPath }/home" class="js-logo-clone">Shoppers</a>
              </div>
            </div>

            <div class="col-6 col-md-4 order-3 order-md-3 text-right">
              <div class="site-top-icons">
                <ul>
                  <li><a href="${pageContext.request.contextPath }/user" title="Thông tin tài khoản"><span class="icon icon-person"></span></a></li>
                  <li><a href="${pageContext.request.contextPath }/wish" title="Danh sách yêu thích"><span class="icon icon-heart-o"></span></a></li>
                  <li>
                    <a href="${pageContext.request.contextPath }/cart" class="site-cart" title="Giỏ hàng">
                      <span class="icon icon-shopping_cart"></span>
                      <span class="count">2</span>
                    </a>
                  </li> 
                  <li class="d-inline-block d-md-none ml-md-0"><a href="#" class="site-menu-toggle js-menu-toggle"><span class="icon-menu"></span></a></li>
                </ul>
              </div> 
            </div>

          </div>
        </div>
      </div> 
      <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
          <ul class="site-menu js-clone-nav d-none d-md-block">
            <li>
              <a class="a-home" style="" href="${pageContext.request.contextPath }/home"><b>Home</b></a>
              
            </li>
            <li>
              <a href="${pageContext.request.contextPath }/about">Giới thiệu</a>
              
            </li>
            <li><a href="${pageContext.request.contextPath }/shop">Vào cửa hàng</a></li>
            
            <li><a href="${pageContext.request.contextPath }/contact">Liên hệ</a></li>
          </ul>
        </div>
      </nav>
    </header>