package hsl.devspace.app.selfcare.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected String[] getServletMappings(){
        return new String[]{"self-care-home"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class<?>[]{
                RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class<?>[]{WebConfig.class};
    }
}
