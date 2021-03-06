package by.bsuir.mpp.transpony.command;

import by.bsuir.mpp.transpony.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commands;

    public CommandHelper() {
        commands = new HashMap<>();
        commands.put("CREATE_CHECKPOINT", new CreateCheckpointCommand());
        commands.put("CREATE_ROUTE", new CreateRouteCommand());
        commands.put("EDIT_ROUTE", new EditRouteCommand());
        commands.put("GET_CAR", new GetCarCommand());
        commands.put("LOGIN", new LoginCommand());
        commands.put("SAVE_CHECKPOINT", new SaveCheckpointCommand());
        commands.put("SAVE_ROUTE", new SaveRouteCommand());
        commands.put("SAVE_ROUTE_CHANGES", new SaveRouteChangesCommand());
        commands.put("SHOW_CHECKPOINTS", new ShowCheckpointsCommand());
        commands.put("SHOW_ROUTES", new ShowRoutesCommand());
        commands.put("SHOW_USER", new ShowUserCommand());
    }

    public Command getCommand(String command) {
        return commands.get(command.toUpperCase());
    }
}
