package by.it.artiuschik.project.java.filtres;

import javax.servlet.*;
import java.io.IOException;

public class FilterUTF8 implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        //FilterConfig-исходные данные для фильтра
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        //запуск остальных фильтров и самого сервлета
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
     encoding=null;
    }
}
