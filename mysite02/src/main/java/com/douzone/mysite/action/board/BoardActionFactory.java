package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "view" : return new ViewAction();
		case "modifyform" : return new ModifyFormAction();
		case "modify" : return new ModifyAction();
		case "writeform" : return new WriteFormAction();
		case "write" : return new WriteAction();
		case "deleteform" : return new DeleteFormAction();
		case "delete" : return new DeleteAction();
		case "replywriteform" : return new ReplyWriteFormAction();
		case "replywrite" : return new ReplyWriteAction();
		case "find" : return new FindAction();
		default : return new BoardAction();
		}
	}

}
