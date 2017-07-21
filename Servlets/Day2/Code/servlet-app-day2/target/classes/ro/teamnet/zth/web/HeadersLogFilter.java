package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Emilia.Palaghita on 20-Jul-17.
 */
public class HeadersLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> headers = request.getHeaderNames();
        if(headers != null) {
            while(headers.hasMoreElements()) {
                String header = headers.nextElement();
                String value = request.getHeader(header);
                LogFileWriter.logHeader(header, value);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
