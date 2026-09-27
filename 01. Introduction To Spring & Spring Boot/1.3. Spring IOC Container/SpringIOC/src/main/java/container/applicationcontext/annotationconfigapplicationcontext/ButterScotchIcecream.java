package container.applicationcontext.annotationconfigapplicationcontext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ButterScotchIcecream implements Icecream{

	@Override
	public void open() {
		System.out.println("Baby Is Eating ButterScotch Icecream");
	}
}
