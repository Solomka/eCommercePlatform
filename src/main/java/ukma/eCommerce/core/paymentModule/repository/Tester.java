package ukma.eCommerce.core.paymentModule.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

public class Tester {

	public static void main(String[] args) {
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:**/dispatcher-servlet.xml");
		//IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> repository = ctx.getBean("orderRepository",
				//IRepository.class);
		
		List<UUID> testUUIDs  = new ArrayList<UUID>();
		
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		UUID uuid3 = UUID.randomUUID();
		UUID uuid4 = UUID.randomUUID();
		UUID uuid5 = UUID.randomUUID();
		UUID uuid6 = UUID.randomUUID();
		
		testUUIDs.add(uuid6);
		testUUIDs.add(uuid5);
		testUUIDs.add(uuid4);
		testUUIDs.add(uuid3);
		testUUIDs.add(uuid2);
		testUUIDs.add(uuid1);
		
		for(UUID uuid:testUUIDs ){
			System.err.println("UUId: "+ uuid);
		}

	}

}
