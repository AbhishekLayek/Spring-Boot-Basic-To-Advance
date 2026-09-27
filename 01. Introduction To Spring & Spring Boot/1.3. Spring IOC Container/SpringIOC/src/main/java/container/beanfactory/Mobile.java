package container.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Mobile {
	public static void main(String[] args) {
		
		ClassPathResource cpr = new ClassPathResource("BeanFactoryConfig.xml");
		
		BeanFactory bf = new XmlBeanFactory(cpr);
		
		MusicPlayer mp = (MusicPlayer)bf.getBean("mp");
		
		mp.playMusic();
	}
}
