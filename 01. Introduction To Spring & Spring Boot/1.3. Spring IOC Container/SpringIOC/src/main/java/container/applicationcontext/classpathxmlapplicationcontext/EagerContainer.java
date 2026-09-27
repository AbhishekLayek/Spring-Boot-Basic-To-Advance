package container.applicationcontext.classpathxmlapplicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EagerContainer {
	public static void main(String[] args) {
		
		/*
		 * ApplicationContext is called as Eager Container, because as soon as we load the xml configuration file it'll create the bean.
		 */
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
	}
}
