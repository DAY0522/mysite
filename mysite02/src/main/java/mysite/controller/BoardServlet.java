package mysite.controller;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.board.*;

import java.util.Map;

import static mysite.controller.ActionServlet.*;

@WebServlet("/board")
public class BoardServlet extends ActionServlet {

    private static final long serialVersionUID = 1L;

    private Map<String, Action> mapAction = Map.of(
            "writeform", new WriteFormAction(),
            "write", new WriteAction(),
            "modify", new ModifyAction(),
            "edit", new EditAction(),
            "view", new ViewAction(),
            "delete", new DeleteAction(),
            "search", new SearchListAction()
    );

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new ListAction());
    }
}
