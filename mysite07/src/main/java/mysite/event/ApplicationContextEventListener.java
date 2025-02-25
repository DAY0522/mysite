package mysite.event;

import mysite.service.AdminService;
import mysite.vo.AdminVo;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ApplicationContextEventListener {
    @Autowired
    private ApplicationContext applicationContext;

    @EventListener({ContextRefreshedEvent.class})
    public void handlerContextRefreshedEvent() {
        System.out.println("-- Context Refreshed Event Received --");

        AdminService adminService = applicationContext.getBean(AdminService.class);
        AdminVo vo = adminService.getContent();

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("title", vo.getTitle());
        propertyValues.add("welcome", vo.getWelcome());
        propertyValues.add("description", vo.getDescription());
        propertyValues.add("profile", vo.getProfile());

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(AdminVo.class);
        beanDefinition.setPropertyValues(propertyValues);

        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.registerBeanDefinition("site", beanDefinition);
    }
}
