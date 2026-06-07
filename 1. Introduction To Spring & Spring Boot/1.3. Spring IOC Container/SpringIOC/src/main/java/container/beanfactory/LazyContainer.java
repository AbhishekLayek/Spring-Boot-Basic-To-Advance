package container.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LazyContainer {
	public static void main(String[] args) {
		
		/*
		 * BeanFactory is called as Lazy Container, because as soon as we load the xml configuration file it'll not create the bean.
		 * When we invoke getBean() method then only it'll create the bean.
		 */
		
		ClassPathResource cpr = new ClassPathResource("BeanFactoryConfig.xml");
		
		BeanFactory bf = new XmlBeanFactory(cpr);
		
		Work w = (Work)bf.getBean("work");
	}
}
