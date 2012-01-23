package dimappers.android.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;

import dimappers.android.PubData.User;
import dimappers.android.PubData.PubEvent;
import dimappers.android.PubData.PubTripState;

public class ServerUser extends dimappers.android.PubData.User 
{
	private boolean hasApp;
	private HashMap<Integer, Boolean> events;
	
	public ServerUser(Integer facebookUserId) {
		super(facebookUserId);
		hasApp = false;
		events = new HashMap<Integer, Boolean>();
	}
	
	public void addEvent(int eventId) {
		/* Current adds the event to the user list if it isn't already in, does nothing if the event is currently in */
		if (!events.containsKey(eventId)) {
			events.put(eventId, false);
		}
	}
	
	public LinkedList<Integer> getOutOfDateEvents() {
		// Iterates through each event, checking if it needs refreshing
		LinkedList<Integer> outOfDateEvents = new LinkedList<Integer>();
		Set<Integer> keys = events.keySet();
		
		for(Integer key : keys)
		{
			if (!events.get(key)) 
			{
				outOfDateEvents.add(key);
			}
		}
		
		return outOfDateEvents;
	}
	
	public LinkedList<Integer> getAllEvents() {
		/* Returns a linked list of all events */
		LinkedList<Integer> allEvents = new LinkedList<Integer>();
		Set<Integer> keys = events.keySet();
		
		for(Integer key : keys)
		{
			allEvents.add(key);
		}
		
		return allEvents;
	}
	
	public void setUpdate(int eventId, boolean update) {
		if (events.containsKey(eventId)) {
			events.put(eventId, update);
		}
	}
	
	public void NotifyEventUpdated(int eventId)
	{
		events.remove(eventId);
		events.put(eventId, true);
	}
	
	public void NotifyUpdateSent()
	{
		//Need to set all events to false has have retrived them all
		for(int eventId : events.keySet())
		{
			events.remove(eventId);
			events.put(eventId, false);
		}
	}
	public boolean GetHasApp() 				{	return hasApp;	}
	public void SetHasApp(boolean hasApp)	{	this.hasApp = hasApp;	}
}
