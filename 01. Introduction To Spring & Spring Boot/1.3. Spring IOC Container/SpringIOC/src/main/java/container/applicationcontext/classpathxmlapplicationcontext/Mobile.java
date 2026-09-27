package container.applicationcontext.classpathxmlapplicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mobile {
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
		
		MusicPlayer mp = (MusicPlayer)ac.getBean("mp");
		
		mp.playMusic();
	}
}
