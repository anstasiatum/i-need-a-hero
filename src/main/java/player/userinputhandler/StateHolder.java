package player.userinputhandler;

import java.util.HashMap;
import java.util.Map;

public class StateHolder {
    public Map<Long, State> statesByChatId = new HashMap<>();

    public State getStateByChatId(Long chatId) {
        return statesByChatId.get(chatId);
    }

    public void updateStateByChatId(Long chatId, State state) {
        statesByChatId.put(chatId, state);
    }
}
