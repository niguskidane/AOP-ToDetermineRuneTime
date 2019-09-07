package com.nk.aoptodetermineruntime.AopExample;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

/*
Filter that modifies how the log is printed, adds the Transaction Id when Applicable
 */

@Component
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            ;
            //generate the random Transaction Id
            long tagNumber=System.currentTimeMillis();
            tagNumber+=(long)(1000000.0d*Math.random());

            //adding transaction id for the logging purpose
            String transId=String.format("TransactionID: %s",Long.toString(tagNumber, Character.MAX_RADIX));

            //storing transaction id inside of a data structure that can be pulled from inside of application.properties
            MDC.put(Constants.TRANS_ID, transId);

            // calling the next filter in the chain, in this case the default filter
            filterChain.doFilter(servletRequest, servletResponse);

        }finally {
            // cleaning out the mdc of the transaction id on garbage collection
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
