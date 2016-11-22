package ukma.eCommerce.core.paymentModule.domainLogic.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Максим on 11/17/2016.
 */
@Service
public final class EventController implements IEventController, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext context;
    private final ExecutorService pool;
    private final Map<Class<?>, Collection<Class<?>>> eventToHandlers;

    @Autowired
    public EventController(ApplicationContext context) {
        this.context = context;
        this.pool = Executors.newSingleThreadExecutor();
        this.eventToHandlers = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        updateCache();
    }

    @Override
    public void dispatch(@NotNull Event event) {

        pool.execute(() -> {

            for (final Class<?> handlerClass : eventToHandlers.get(event.getClass())) {

                try {
                    final Object handler = context.getBean(handlerClass);

                    for (final Method method : handler.getClass().getMethods()) {

                        if (method.isAnnotationPresent(EventSynchronized.class)) {

                            final Class<?>[] params = method.getParameterTypes();

                            if (params.length != 1 && params.length != 2) {
                                // remove
                                continue;
                            }

                            final IEventController backRef = EventController.this;

                            if (params.length == 1) {

                                if (event.getClass().isAssignableFrom(params[0])) {
                                    method.invoke(handler, event);
                                } else if (backRef.getClass().isAssignableFrom(params[0])) {
                                    method.invoke(handler, backRef);
                                }
                            } else {

                                if (event.getClass().isAssignableFrom(params[0]) &&
                                        backRef.getClass().isAssignableFrom(params[1])) {
                                    method.invoke(handler, event, backRef);
                                } else if (event.getClass().isAssignableFrom(params[1]) &&
                                        backRef.getClass().isAssignableFrom(params[0])) {
                                    method.invoke(handler, backRef, event);
                                }
                            }
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.context = event.getApplicationContext();
        updateCache();
    }

    private void updateCache() {

        for (final Object bean : context.getBeansWithAnnotation(EventHandler.class).values()) {

            for (final Method method : bean.getClass().getMethods()) {

                if (method.isAnnotationPresent(EventSynchronized.class)) {

                    final Class<?>[] params = method.getParameterTypes();

                    if (params.length != 1 && params.length != 2) {
                        // remove
                        continue;
                    }

                    Class<?> eventClass = Event.class.isAssignableFrom(params[0]) ? params[0] : null;

                    if(eventClass == null) {
                        eventClass = (params.length == 2 && Event.class.isAssignableFrom(params[1])) ? params[1] : null;
                    }

                    if(eventClass != null) {

                        Collection<Class<?>> handlers = eventToHandlers.get(eventClass);

                        if(handlers == null) {
                            handlers = new ArrayList<>(1);
                        }
                        handlers.add(bean.getClass());
                        eventToHandlers.put(eventClass, handlers);
                    }
                }
            }
        }
    }

}
