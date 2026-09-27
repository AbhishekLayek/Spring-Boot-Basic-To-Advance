package container.applicationcontext.annotationconfigapplicationcontext;

import org.springframework.stereotype.Component;

@Component
public class ChocolateIcecream implements Icecream{

	@Override
	public void open() {
		System.out.println("Baby Is Eating Chocolate Icecream");
	}
}
