/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-03-30 07:05:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin.huileMoteur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import commerce.catalogue.domaine.modele.HuileMoteur;

public final class ajoute_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/admin/huileMoteur/../enTetePage.html", Long.valueOf(1616513548000L));
    _jspx_dependants.put("/admin/huileMoteur/../piedDePage.html", Long.valueOf(1616513612000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Site web marchand</title>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<link rel=\"stylesheet\" id=\"storefront-style-css\" href=\"../../css/themes/storefront/style.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" id=\"storefront-woocommerce-style-css\" href=\"../../css/themes/storefront/assets/css/woocommerce/woocommerce.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" id=\"storefront-icons-css\" href=\"../../css/themes/storefront/assets/css/base/icons.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../../js/jplayer-2.9.2/skin/blue.monday/css/jplayer.blue.monday.css\" type=\"text/css\" />\r\n");
      out.write("<style id=\"storefront-style-inline-css\" type=\"text/css\">\r\n");
      out.write("button.menu-toggle,\r\n");
      out.write("button.menu-toggle:hover,\r\n");
      out.write(".handheld-navigation .dropdown-toggle {\r\n");
      out.write("\tcolor: #333333;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("button.menu-toggle,\r\n");
      out.write("button.menu-toggle:hover {\r\n");
      out.write("\tborder-color: #333333;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("button.menu-toggle,\r\n");
      out.write("button.menu-toggle:hover {\r\n");
      out.write("\tbackground-color: #ffffff;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("button.menu-toggle:after,\r\n");
      out.write("button.menu-toggle:before,\r\n");
      out.write("button.menu-toggle span:before {\r\n");
      out.write("\tbackground-color: #333333;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table.cart td.product-remove,\r\n");
      out.write("table.cart td.actions {\r\n");
      out.write("\tborder-top-color: #ffffff;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<style id=\"admin-style-css\" type=\"text/css\">\r\n");
      out.write("div#form label {\r\n");
      out.write("\tmargin-top:20px;\r\n");
      out.write("\tdisplay:block;\r\n");
      out.write("}\r\n");
      out.write("div#form input {\r\n");
      out.write("   width: 100%;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../js/themes/storefront/assets/js/navigation.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../..//js/jquery-1.11.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../js/jplayer-2.9.2/jplayer/jquery.jplayer.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../js/jplayer-2.9.2/add-on/jplayer.playlist.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"inner-wrapper\">\r\n");
      out.write("\t\t\t<header id=\"header\" class=\"col-full\">\r\n");
      out.write("\t\t\t\t<div id=\"logo\">\r\n");
      out.write("\t\t\t\t\t<h1 class=\"site-title\">Administration du site web marchand</h1>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</header>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

  HuileMoteur huileMoteur = new HuileMoteur() ;

      out.write("\r\n");
      out.write("  <div id=\"content\" class=\"col-full\">\r\n");
      out.write("    <div class=\"col-1\">\r\n");
      out.write("      <form action=\"controleHuileMoteur.jsp?commande=ajouter\" method=\"post\">\r\n");
      out.write("        <div id=\"form\">\r\n");

  Field[] articleFields = huileMoteur.getClass().getSuperclass().getDeclaredFields();
  ArrayList<Field> list = new ArrayList<Field>(Arrays.asList(articleFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("refArticle")) {
	  list.remove(i);
    }
  }
  articleFields = list.toArray(new Field[articleFields.length-1]);
  for (Field articleField : articleFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	String value = "" ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   value = "0";
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      value = "0.0";
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      value = "";
      pattern = ".*" ;
    }

      out.write("\r\n");
      out.write("        <div>\r\n");
      out.write("          <label class=\"required\" for=\"form_");
      out.print(articleField.getName() );
      out.write('"');
      out.write('>');
      out.print(articleLabel );
      out.write("</label>\r\n");
      out.write("\t\t  <input id=\"form_");
      out.print(articleField.getName() );
      out.write("\" type=\"text\" size=\"70\" name=\"");
      out.print(articleField.getName() );
      out.write("\" value=\"");
      out.print(value );
      out.write("\" required pattern=\"");
      out.print(pattern );
      out.write("\"/>\r\n");
      out.write("\t\t</div>\r\n");

  }
  Field[] huileFields = huileMoteur.getClass().getDeclaredFields();
  list = new ArrayList<Field>(Arrays.asList(huileFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("dateDeParution")) {
	  list.remove(i);
    }
  }
  huileFields = list.toArray(new Field[huileFields.length-2]);
  for (Field articleField : huileFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
    String value = "" ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   value = "0";
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      value = "0.0";
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      value = "";
      pattern = ".*" ;
    }

      out.write("\r\n");
      out.write("        <div>\r\n");
      out.write("          <label class=\"required\" for=\"form_");
      out.print(articleField.getName() );
      out.write('"');
      out.write('>');
      out.print(articleLabel );
      out.write("</label>\r\n");
      out.write("\t\t  <input id=\"form_");
      out.print(articleField.getName() );
      out.write("\" type=\"text\" size=\"70\" name=\"");
      out.print(articleField.getName() );
      out.write("\" value=\"");
      out.print(value );
      out.write("\" required pattern=\"");
      out.print(pattern );
      out.write("\"/>\r\n");
      out.write("\t\t</div>\r\n");

  }

      out.write("\r\n");
      out.write("\t    </div>\r\n");
      out.write("        <div>\r\n");
      out.write("          <input id=\"form_valider\" type=\"Submit\" name=\"Submit\" Value=\"Valider\"/>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t  </form>\r\n");
      out.write("\t</div>\r\n");
      out.write("  </div>\r\n");
      out.write("\t\t\t<footer id=\"footer\" class=\"col-full\">\r\n");
      out.write("\t\t\t\t<div id=\"credit\" class=\"col-right\">\r\n");
      out.write("\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t<object type=\"image/svg+xml\" data=\"../../images/deezer.svg\" width=\"152\" height=\"34\"></object>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\tDesigned by\r\n");
      out.write("\t\t\t\t\t\t<a title=\"Premium WordPress Themes & Plugins by WooThemes\" alt=\"Premium WordPress Themes & Plugins by WooThemes\" href=\"http://www.woothemes.com/\">\r\n");
      out.write("\t\t\t\t\t\t\t<img width=\"74\" height=\"19\" alt=\"WooThemes\" src=\"../../images/woothemes.png\">\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</footer>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
