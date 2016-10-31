package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.filter.BasicFilter;

@Repository("sellerRepository")
@Transactional
public class SellerRepository implements ISellerRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Observable<Collection<Seller>> find(BasicFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Seller> create(Object seller) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();
		session.save(seller);
		return null;
	}

	@Override
	public Observable<Seller> update(Seller bo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(Long key) {
		// TODO Auto-generated method stub
		return null;
	}

}
