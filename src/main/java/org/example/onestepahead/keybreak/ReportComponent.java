package org.example.onestepahead.keybreak;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReportComponent {

    public String outputReport(List<SalesLine> sales) {
        StringBuilder sb = new StringBuilder();

        String currentProductCode = null;
        int subtotalQty = 0;
        int subtotalAmount = 0;

        for (SalesLine sl: sales) {
            String productCode = sl.getProductCode();
            if (!productCode.equals(currentProductCode)) {
                // キーブレイク時は小計行を出力
                if (currentProductCode != null) {
                    sb.append(makeSubtotalLine(currentProductCode, subtotalQty, subtotalAmount)).append("\n");
                }
                currentProductCode = productCode;
                subtotalQty = 0;
                subtotalAmount = 0;
            }
            sb.append(makeNormalLine(sl)).append("\n");
            subtotalQty += sl.getQuantity();
            subtotalAmount += sl.getAmount();
        }
        // 最後の小計グループを処理
        sb.append(makeSubtotalLine(currentProductCode, subtotalQty, subtotalAmount)).append("\n");

        return sb.toString();
    }

    private String makeNormalLine(SalesLine sl) {
        return String.format("明細 %s %s %s個 %s円",
                sl.getSalesDate(), sl.getProductCode(), sl.getQuantity(), sl.getAmount());
    }

    private String makeSubtotalLine(String code, int qty, int amount) {
        return String.format("小計 %s %s個 %s円", code, qty, amount);
    }

    public String outputReportBetter(List<SalesLine> sales) {
        KeyBreakProcessor kbp = new KeyBreakProcessor(sales);
        final StringBuilder sb = new StringBuilder();
        // キーの生成
        Function<SalesLine, String> keyGenerator = SalesLine::getProductCode;
        // 1明細行を処理して出力
        Consumer<SalesLine> processLine = sl -> sb.append(makeNormalLine(sl)).append("\n");
        // キーでグループされた明細行を処理し、小計を出力
        BiConsumer<String, List<SalesLine>> subTotal = (code, lines) -> {
            int qty = lines.stream().mapToInt(SalesLine::getQuantity).sum();
            int amount = lines.stream().mapToInt(SalesLine::getAmount).sum();
            sb.append(makeSubtotalLine(code, qty, amount)).append("\n");
        };
        kbp.execute(keyGenerator, processLine, subTotal);

        return sb.toString();
    }

    public String outputReportWithGenerics(List<SalesLine> sales) {
        GeneralKeyBreakProcessor<SalesLine, String> gkbp = new GeneralKeyBreakProcessor<>(sales);
        final StringBuilder sb = new StringBuilder();
        // キーの生成
        Function<SalesLine, String> keyGenerator = SalesLine::getProductCode;
        // 1明細行を処理して出力
        Consumer<SalesLine> processLine = sl -> sb.append(makeNormalLine(sl)).append("\n");
        // キーでグループされた明細行を処理し、小計を出力
        BiConsumer<String, List<SalesLine>> subTotal = (code, lines) -> {
            int qty = lines.stream().mapToInt(SalesLine::getQuantity).sum();
            int amount = lines.stream().mapToInt(SalesLine::getAmount).sum();
            sb.append(makeSubtotalLine(code, qty, amount)).append("\n");
        };
        gkbp.execute(keyGenerator, processLine, subTotal);

        return sb.toString();
    }

}
