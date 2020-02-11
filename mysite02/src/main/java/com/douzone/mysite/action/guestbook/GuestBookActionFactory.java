package com.douzone.mysite.action.guestbook;


import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "list" : return new GuestAction();
		case "add" : return new GuestInsertAction();
		case "deleteform" : return new GuestDeleteFormAction();
		case "delete" : return new GuestDeleteAction();
		default : return new GuestAction();
		}
	}

}
