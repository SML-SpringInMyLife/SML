/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.90
 * Generated at: 2024-07-31 02:30:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sms_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1722307827825L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1693184009053L));
    _jspx_dependants.put("jar:file:/D:/SML-develop/SML/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SML/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>SML_Admin(SMS관리)</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<!-- 헤더 영역 포함 -->\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/common/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->\r\n");
      out.write("	<main>\r\n");
      out.write("		<div class=\"admin-container\">\r\n");
      out.write("			");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/admin/adminMenu.jsp", out, false);
      out.write("\r\n");
      out.write("			<div class=\"admin-main-content\">\r\n");
      out.write("				<h2>SMS 관리</h2>\r\n");
      out.write("				<div class=\"search-container\">\r\n");
      out.write("					<select id=\"searchCategory\">\r\n");
      out.write("						<option value=\"all\">전체</option>\r\n");
      out.write("						<option value=\"date\">발송일시</option>\r\n");
      out.write("						<option value=\"receiver\">수신인</option>\r\n");
      out.write("						<option value=\"content\">내용</option>\r\n");
      out.write("					</select> <input type=\"text\" id=\"searchQuery\" placeholder=\"검색어를 입력하세요.\">\r\n");
      out.write("					<button onclick=\"searchSMS()\">검색</button>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				<table class=\"sms-table\">\r\n");
      out.write("					<thead>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<th>No.</th>\r\n");
      out.write("							<th>발송일시</th>\r\n");
      out.write("							<th>수신인</th>\r\n");
      out.write("							<th>내용</th>\r\n");
      out.write("							<th>발신누적횟수</th>\r\n");
      out.write("						</tr>\r\n");
      out.write("					</thead>\r\n");
      out.write("					<tbody id=\"smsList\">\r\n");
      out.write("						<!-- 샘플 -->\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td data-label=\"No.\">1</td>\r\n");
      out.write("							<td data-label=\"발송일시\">2024-07-31 10:00</td>\r\n");
      out.write("							<td data-label=\"수신인\">홍길동</td>\r\n");
      out.write("							<td data-label=\"내용\"><span class=\"sms-content\"\r\n");
      out.write("								onclick=\"showSmsDetails('안녕하세요, 홍길동님!')\">안녕하세요, 홍길동님!</span></td>\r\n");
      out.write("							<td data-label=\"발신누적횟수\">5</td>\r\n");
      out.write("						</tr>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td data-label=\"No.\">2</td>\r\n");
      out.write("							<td data-label=\"발송일시\">2024-07-31 11:00</td>\r\n");
      out.write("							<td data-label=\"수신인\">임꺽정</td>\r\n");
      out.write("							<td data-label=\"내용\"><span class=\"sms-content\"\r\n");
      out.write("								onclick=\"showSmsDetails('[안부문자] 안녕하세요, 임꺽정님! 1주일간 출석이 없으셔서 문자 보냅니다.')\">[안부문자]\r\n");
      out.write("									안녕하세요, 임꺽정님! 1주일간 출석이 없으셔서 문자 보냅니다.</span></td>\r\n");
      out.write("							<td data-label=\"발신누적횟수\">3</td>\r\n");
      out.write("						</tr>\r\n");
      out.write("					</tbody>\r\n");
      out.write("				</table>\r\n");
      out.write("\r\n");
      out.write("				<div class=\"sms-button-container\">\r\n");
      out.write("					<button class=\"send-sms\" onclick=\"showSmsPopup()\">SMS 발송</button>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</main>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div id=\"smsPopup\" class=\"sms-popup\">\r\n");
      out.write("		<div class=\"sms-popup-content\">\r\n");
      out.write("			<span class=\"close\" onclick=\"closeSmsPopup()\">&times;</span>\r\n");
      out.write("			<h2>SMS 발송</h2>\r\n");
      out.write("			<div class=\"search-container\">\r\n");
      out.write("				<select id=\"popupSearchCategory\">\r\n");
      out.write("					<option value=\"name\">성명</option>\r\n");
      out.write("					<option value=\"id\">ID</option>\r\n");
      out.write("					<option value=\"phone\">전화번호</option>\r\n");
      out.write("				</select> <input type=\"text\" class=\"search-bar\" id=\"popupSearchQuery\"\r\n");
      out.write("					placeholder=\"검색어를 입력하세요.\">\r\n");
      out.write("				<button onclick=\"popupSearchMember()\">검색</button>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div>\r\n");
      out.write("				<ul id=\"searchResults\"></ul>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div>\r\n");
      out.write("				<label for=\"recipientNumber\" class=\"sms-label\">수신인 번호:</label> <input\r\n");
      out.write("					type=\"text\" id=\"recipientNumber\" readonly>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div>\r\n");
      out.write("				<label for=\"senderNumber\" class=\"sms-label\">발신인 번호:</label> <input\r\n");
      out.write("					type=\"text\" id=\"senderNumber\">\r\n");
      out.write("			</div>\r\n");
      out.write("			<div>\r\n");
      out.write("				<label for=\"smsContent\" class=\"sms-label\">내용:</label>\r\n");
      out.write("				<textarea id=\"smsContent\" rows=\"4\" cols=\"50\"></textarea>\r\n");
      out.write("			</div>\r\n");
      out.write("			<button class=\"send-button\" onclick=\"sendSms()\">보내기</button>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	<!-- SMS 상세보기 팝업 -->\r\n");
      out.write("	<div id=\"smsDetailsPopup\" class=\"smsDetail-popup\">\r\n");
      out.write("		<div class=\"sms-popup-content\">\r\n");
      out.write("			<span class=\"close\" onclick=\"closeSmsDetailsPopup()\">&times;</span>\r\n");
      out.write("			<h2>SMS 상세보기</h2>\r\n");
      out.write("			<div id=\"smsDetailsContent\"></div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	<!-- 푸터 영역 포함 -->\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Footer</title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${webappRoot}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/css/common/common.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<footer>\r\n");
      out.write("		<div class=\"footer-container\">\r\n");
      out.write("			<div class=\"footer-left\">\r\n");
      out.write("				<nav class=\"footer-menu\" id=\"footer-menu\">\r\n");
      out.write("					<ul>\r\n");
      out.write("						<li><a href=\"/privacy\">Privacy Policy</a></li>\r\n");
      out.write("						<li><a href=\"/terms\">Terms of Service</a></li>\r\n");
      out.write("						<li><a href=\"/contact\">Contact Us</a></li>\r\n");
      out.write("					</ul>\r\n");
      out.write("				</nav>\r\n");
      out.write("			</div>\r\n");
      out.write("			<p>&copy; 2024 SML Company. All Rights Reserved.</p>\r\n");
      out.write("		</div>\r\n");
      out.write("	</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
