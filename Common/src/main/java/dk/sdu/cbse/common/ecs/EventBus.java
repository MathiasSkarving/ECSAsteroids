package dk.sdu.cbse.common.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EventBus {
    private HashMap<Class<? extends EventType>, HashSet<Subscriber>> eventTypeSubscriberMap = new HashMap<>();

    private static EventBus instance;

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    private EventBus() {};

    public void notifySubscribers(EventType event) {
        HashSet<Subscriber> eventSubs = eventTypeSubscriberMap.get(event.getClass());
        if (eventSubs == null) return;
        for(Subscriber sub : eventSubs) {
            sub.onEvent(event);
        }
    }

    public void subscribe(Subscriber subscriber, Class<? extends EventType> event) {
        if(!eventTypeSubscriberMap.containsKey(event)) {
            eventTypeSubscriberMap.put(event, new HashSet<>());
        }
        eventTypeSubscriberMap.get(event).add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber, Class<? extends EventType> event) {
        eventTypeSubscriberMap.get(event).remove(subscriber);
    }
}
