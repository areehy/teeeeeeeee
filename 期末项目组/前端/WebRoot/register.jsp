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
<script type="text/javascript">
function duihua()
{
alert("注册成功")
}

</script>
<div id="wrap">
  <div class="header">
    <div id="menu">
      <ul>
        <li><a href="index.htm">主页</a></li>
        <li><a href="category.jsp">所有书籍</a></li>
        <li><a href="login.jsp">登录</a></li>
        <li class="selected"><a href="register.htm">注册</a></li>
        <li><a href="about.htm">关于我们</a></li>
        <li><a href="contact.htm">联系我们</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>免费注册账号</div>
      <div class="feat_prod_box_details">
        <p class="details"> 免费注册属于您的新账号，方便进行购买。 </p>
        <div class="contact_form">
          <div class="form_subtitle">创建新账号</div>
          <form name="register" action="#" method="post">
            <div class="form_row">
              <label class="contact"><strong>用户名:</strong></label>
              <input type="text" class="contact_input" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>密码:</strong></label>
              <input type="password" class="contact_input" />
            </div>

            <div class="form_row">
              <div class="terms">
                <input type="checkbox" name="terms" />
                我已阅读并同意 <a href="#">《万师屋用户注册协议》</a> </div>
            </div>
            <div class="form_row">
              <input type="button" class="register" value="注册" onclick="duihua()" />
            </div>
          </form>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
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
