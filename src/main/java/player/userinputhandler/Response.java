package player.userinputhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Response {
    final State state;
    final String textAnswer;
    final File file;

    public Response(State state, String textAnswer) {
        this.state = state;
        this.textAnswer = textAnswer;
        this.file = null;
    }
}
