package container.applicationcontext.annotationconfigapplicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);
		
		Baby b = ac.getBean(Baby.class);
		
		b.eat();
	}
}
