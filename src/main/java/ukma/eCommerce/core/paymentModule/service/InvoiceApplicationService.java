package ukma.eCommerce.core.paymentModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.event.OrderCreatedEvent;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/**
 * Created by Максим on 11/6/2016.
 */
@Service
final class InvoiceApplicationService implements IInvoiceApplicationService {

	private static final Logger LOGGER = Logger.getLogger(InvoiceApplicationService.class.getName());

	private final IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> repository;

	@Autowired
	public InvoiceApplicationService(@NotNull @Qualifier("invoiceRepository")IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> repository) {
		this.repository = repository;
	}

	@EventListener
	public void onOrderCreated(OrderCreatedEvent event) {
		repository.create(InvoiceConverter.convert(event.getOrder()));
	}

	@Override
	public Observable<Invoice> createInvoice(@NotNull InvoiceDTO invoiceDTO) {
		return Observable.create((Observable.OnSubscribe<Invoice>) subscriber ->
				subscriber.onNext(repository.create(InvoiceConverter.convert(invoiceDTO))))
				.subscribeOn(Schedulers.newThread());
	}
}
