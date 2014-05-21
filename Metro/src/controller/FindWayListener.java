package controller;

import java.util.EventListener;

public interface FindWayListener extends EventListener{
	public void wayChanged(FindWayEvent event);
}
