package org.example.onestepahead.keybreak

import spock.lang.Specification

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReportComponentSpec extends Specification {

    def "商品別レポートが出力できる"() {
        given: "売上リスト"
        def s1 = sales("2020-04-01", "A", 100, 2)
        def s2 = sales("2020-04-01", "A", 100, 3)
        def s3 = sales("2020-04-02", "A", 100, 1)
        def s4 = sales("2020-04-02", "A", 100, 1)
        def s5 = sales("2020-04-01", "B", 150, 1)
        def s6 = sales("2020-04-02", "B", 150, 2)
        def s7 = sales("2020-04-02", "B", 150, 2)
        def s8 = sales("2020-04-01", "C", 200, 2)
        def sales = [s1, s2, s3, s4, s5, s6, s7, s8]

        and: "レポート出力コンポーネント"
        def sut = new ReportComponent()

        when: "レポート出力"
        def report = sut.outputReport(sales)

        then:
        """|明細 2020-04-01 A 2個 200円
           |明細 2020-04-01 A 3個 300円
           |明細 2020-04-02 A 1個 100円
           |明細 2020-04-02 A 1個 100円
           |小計 A 7個 700円
           |明細 2020-04-01 B 1個 150円
           |明細 2020-04-02 B 2個 300円
           |明細 2020-04-02 B 2個 300円
           |小計 B 5個 750円
           |明細 2020-04-01 C 2個 400円
           |小計 C 2個 400円
           |""".stripMargin() == report
    }

    def "商品別レポートが出力できる2"() {
        given: "売上リスト"
        def s1 = sales("2020-04-01", "A", 100, 2)
        def s2 = sales("2020-04-01", "A", 100, 3)
        def s3 = sales("2020-04-02", "A", 100, 1)
        def s4 = sales("2020-04-02", "A", 100, 1)
        def s5 = sales("2020-04-01", "B", 150, 1)
        def s6 = sales("2020-04-02", "B", 150, 2)
        def s7 = sales("2020-04-02", "B", 150, 2)
        def s8 = sales("2020-04-01", "C", 200, 2)
        def sales = [s1, s2, s3, s4, s5, s6, s7, s8]

        and: "レポート出力コンポーネント"
        def sut = new ReportComponent()

        when: "レポート出力"
        def report = sut.outputReportBetter(sales)

        then:
        """|明細 2020-04-01 A 2個 200円
           |明細 2020-04-01 A 3個 300円
           |明細 2020-04-02 A 1個 100円
           |明細 2020-04-02 A 1個 100円
           |小計 A 7個 700円
           |明細 2020-04-01 B 1個 150円
           |明細 2020-04-02 B 2個 300円
           |明細 2020-04-02 B 2個 300円
           |小計 B 5個 750円
           |明細 2020-04-01 C 2個 400円
           |小計 C 2個 400円
           |""".stripMargin() == report
    }

    def "商品別レポートが出力できる3"() {
        given: "売上リスト"
        def s1 = sales("2020-04-01", "A", 100, 2)
        def s2 = sales("2020-04-01", "A", 100, 3)
        def s3 = sales("2020-04-02", "A", 100, 1)
        def s4 = sales("2020-04-02", "A", 100, 1)
        def s5 = sales("2020-04-01", "B", 150, 1)
        def s6 = sales("2020-04-02", "B", 150, 2)
        def s7 = sales("2020-04-02", "B", 150, 2)
        def s8 = sales("2020-04-01", "C", 200, 2)
        def sales = [s1, s2, s3, s4, s5, s6, s7, s8]

        and: "レポート出力コンポーネント"
        def sut = new ReportComponent()

        when: "レポート出力"
        def report = sut.outputReportWithGenerics(sales)

        then:
        """|明細 2020-04-01 A 2個 200円
           |明細 2020-04-01 A 3個 300円
           |明細 2020-04-02 A 1個 100円
           |明細 2020-04-02 A 1個 100円
           |小計 A 7個 700円
           |明細 2020-04-01 B 1個 150円
           |明細 2020-04-02 B 2個 300円
           |明細 2020-04-02 B 2個 300円
           |小計 B 5個 750円
           |明細 2020-04-01 C 2個 400円
           |小計 C 2個 400円
           |""".stripMargin() == report
    }
    def sales(String date, String code, int price, int qty) {
        new SalesLine(
                salesDate: date,
                productCode: code,
                unitPrice: price,
                quantity: qty,
                amount: price * qty
        )
    }

}
