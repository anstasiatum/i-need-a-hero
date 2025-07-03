package player.userinputhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
public class Response {
    private final State state;
    private final String textAnswer;
    private final File file;
    private final boolean hasFixedOptions;
    private final List<String> optionTexts;

    public Response(State state, String textAnswer, List<String> optionTexts) {
        this.state = state;
        this.textAnswer = textAnswer;
        this.file = null;
        this.hasFixedOptions = true;
        this.optionTexts = optionTexts;
    }

    public Response(State state, String textAnswer) {
        this.state = state;
        this.textAnswer = textAnswer;
        this.file = null;
        this.hasFixedOptions = false;
        this.optionTexts = List.of();
    }

    public Response(State state, String textAnswer, File file) {
        this.state = state;
        this.textAnswer = textAnswer;
        this.file = file;
        this.hasFixedOptions = false;
        this.optionTexts = List.of();
    }

    public Response(String textAnswer) {
        this.state = null;
        this.textAnswer = textAnswer;
        this.file = null;
        this.hasFixedOptions = false;
        this.optionTexts = List.of();
    }
}
