package to_shop.model.actors;

import to_shop.model.actors.events.Event;

import java.util.*;

public class EventHistory implements Observer {
    private HashMap<Observable,List<Event>> historyDB;
    private static EventHistory instance;

    private EventHistory() {
        historyDB = new HashMap<>();
    }
    public static EventHistory getInstance() {
        if (instance == null)
            instance = new EventHistory();
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event) {
            if (!historyDB.containsKey(o))
                historyDB.put(o, new ArrayList<>());
            historyDB.get(o).add((Event) arg);
        }
    }
}
