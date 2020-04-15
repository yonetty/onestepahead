package org.example.onestepahead.keybreak;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GeneralKeyBreakProcessor<L, K> {

    private List<L> lines;

    public GeneralKeyBreakProcessor(List<L> lines) {
        this.lines = lines;
    }

    public void execute(Function<L, K> keyGenerator, Consumer<L> lineProcessor,
                        BiConsumer<K, List<L>> keyBreakLister) {
        K currentKey = null;
        List<L> subList = new ArrayList<>();
        for (L line: lines) {
            K key = keyGenerator.apply(line);
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
