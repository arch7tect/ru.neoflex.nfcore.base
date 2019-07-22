package ru.neoflex.nfcore.base.components;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Publisher {
    interface IEvent {}
    interface ISubscriber {
        void handleEvent(IEvent event);
        boolean canHandle(IEvent event);
    }
    public abstract static class EObjectEvent implements IEvent {
        private EObject eObject;
        public EObjectEvent(EObject eObject) {
            this.eObject = eObject;
        }

        public EObject getEObject() {
            return eObject;
        }

        public void setEObject(EObject eObject) {
            this.eObject = eObject;
        }
    }
    public static class BeforeSaveEvent extends EObjectEvent {
        public BeforeSaveEvent(EObject eObject) {
            super(eObject);
        }
    }
    public static class AfterSaveEvent extends EObjectEvent {
        public AfterSaveEvent(EObject eObject) {
            super(eObject);
        }
    }
    public static class AfterLoadEvent extends EObjectEvent {
        public AfterLoadEvent(EObject eObject) {
            super(eObject);
        }
    }
    public abstract class EObjectHandler<T extends EObject> implements ISubscriber {
        EClass eClass;

        public EObjectHandler(EClass eClass) {
            this.eClass = eClass;
        }

        abstract public EObject handleEObject(T eObject);

        @Override
        public void handleEvent(IEvent event) {
            EObjectEvent eObjectEvent = (EObjectEvent) event;
            eObjectEvent.eObject = handleEObject((T) eObjectEvent.eObject);
        }

        @Override
        public boolean canHandle(IEvent event) {
            return event instanceof EObjectEvent && eClass.isInstance(((EObjectEvent)event).eObject);
        }
    }
    private List<ISubscriber> subscribers = new ArrayList<>();

    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void publish(IEvent event) {
        for (ISubscriber subscriber: subscribers) {
            if (subscriber.canHandle(event)) {
                subscriber.handleEvent(event);
            }
        }
    }
}
