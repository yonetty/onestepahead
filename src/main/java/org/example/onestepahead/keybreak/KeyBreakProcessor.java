package org.example.onestepahead.keybreak;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class KeyBreakProcessor {

    private List<SalesLine> lines;

    public KeyBreakProcessor(List<SalesLine> lines) {
        this.lines = lines;
    }

    public void execute(Function<SalesLine, String> keyGenerator, Consumer<SalesLine> lineProcessor,
                        BiConsumer<String, List<SalesLine>> keyBreakLister) {
        String currentKey = null;
        List<SalesLine> subList = new ArrayList<>();
        for (SalesLine line : lines) {
            String key = keyGenerator.apply(line);
            if (!key.equals(currentKey)) {
                if (currentKey != null) {
                    keyBreakLister.accept(currentKey, subList);
                    subList = new ArrayList<>();
                }
                currentKey = key;
            }
            lineProcessor.accept(line);
            subList.add(line);
        }
        keyBreakLister.accept(currentKey, subList);
    }

}
