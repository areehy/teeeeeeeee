<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>万师屋——网上书城</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">
  <div class="header">
    <div id="menu">
      <ul>
        <li><a href="index.htm">主页</a></li>
        <li class="selected"><a href="category.jsp">所有书籍</a></li>
        <li><a href="login.jsp">登录</a></li>
        <li><a href="register.htm">注册</a></li>
        <li><a href="about.htm">关于我们</a></li>
        <li><a href="contact.htm">联系我们</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="crumb_nav"> <a href="index.htm">主页</a> &gt;&gt; 所有书籍 </div>
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>所有书籍</div>
      <div class="new_products">
        <div class="new_prod_box"> <a href="details.htm">软件工程</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb1.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">操作系统1</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb4.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">操作系统2</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb5.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">程序设计1</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb6.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">程序设计2</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb7.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">人工智能1</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb8.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">人工智能2</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb9.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">软件工程与方法1</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb10.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">软件工程与方法2</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb11.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">数据库</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb12.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="new_prod_box"> <a href="details.htm">图形图像</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb13.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
		<div class="new_prod_box"> <a href="details.htm">网络通信</a>
          <div class="new_prod_bg"> <a href="details.htm"><img src="images/thumb14.jpg" width=60px height=90px; alt="" title="" class="thumb" border="0" /></a> </div>
        </div>
        <div class="pagination"> <span class="disabled"><<</span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a>…<a href="#?page=199">10</a><a href="#?page=200">11</a><a href="#?page=2">>></a> </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
      <div class="cart">
        <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /><a href="cart.jsp"> 进入我的购物车</span></a>
        <span class="title_icon"><a href="my_order.jsp">进入我的订单</span></a></div>
        <a href="my_order.htm" class="view_cart"></a></div>
      <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>关于本店</div>
      <div class="about">
        <p> <img src="images/about.gif" alt="" title="" class="right" />大学的教材费对于大部分学生来说并不能算便宜，因此有不少学生会找学长学姐们借他们用过的课本，一般来说制作还算精良的课本仅仅一个学期并不会折旧太多，且用过的书上一般都会有一些笔记，因此二手课本的使用价值往往比一手课本要高。但是因为书本的分布零散以及缺少一条渠道或者是平台进行交易的原因，二手教材市场并没有在大学中形成。大部分都只是熟人之间的赠与或是借出。因此我们决定提供一个平台，可以让大家在上面浏览购买或者是出售一些二手教材，既可以提供给学弟学妹们一些宝贵的学习资料，也可以让学长学姐们的教材除了卖废品以外多一个重新被利用的机会。</p>
      </div>
      
      <div class="right_box">
        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>分类</div>
        <ul class="list">
          <li><a href="#">程序设计</a></li>
		  <li><a href="#">软件工程与方法</a></li>
          <li><a href="#">图形图像</a></li>
          <li><a href="#">操作系统</a></li>
          <li><a href="#">数据库</a></li>
          <li><a href="#">网络通信</a></li>
          <li><a href="#">项目管理</a></li>
          <li><a href="#">人工智能</a></li>
          <li><a href="#">信息安全</a></li>
          <li><a href="#">计算机理论</a></li>
          <li><a href="#">IT人文</a></li>
          <li><a href="#">计算机体系结构</a></li>
		  <li><a href="#">办公软件</a></li>
		  <li><a href="#">计算机考试认证</a></li>
		  <li><a href="#">硬件与维护</a></li>
		  <li><a href="#">游戏设计编程</a></li>
		  <li><a href="#">云计算</a></li>
        </ul>
      </div>
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  <!--end of center content-->
  <div class="footer">
    <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br />
      </div>
    <div class="right_footer"> <a href="#">主页</a> <a href="#">关于我们</a> <a href="#">联系我们</a> </div>
  </div>
</div>
</body>
</html>
