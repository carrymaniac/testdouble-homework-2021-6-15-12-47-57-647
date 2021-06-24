package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.tw.banking.Printer.SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @ProjectName: testdouble-homework
 * @Package: com.tw.banking
 * @ClassName: PrinterTest
 * @Author: carrymaniac
 * @Description:
 * @Date: 2021/6/24 下午8:36
 * @Version:
 */
class PrinterTest {

    @Mock
    Printer printer;
    @Mock
    Console console;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPrint_shouldPrintHeader_andPrintStatementLinesWithTransactions() {
        //given
        List<Transaction> transactions = Arrays.asList(new Transaction("test-date",100));
        //when
        printer.print(transactions);
        //then
        //没想到更好的处理方式
        //1。也许把line的结果自己先预计出来 拿结果来assertEquals
        //2。或者是把private方法去掉 对单独每个方法加测试
        verify(console, times(2)).printLine(any());
    }

    @Test
    void should_return_statementLine_when_statementLine_with_Transaction_and_Balance() {
        //given
        Transaction tx = mock(Transaction.class);
        doReturn("test-date").when(tx).date();
        doReturn(100).when(tx).amount();
        //when
        String statementLine = printer.statementLine(tx, 2);
        //then
        assertEquals("test-date" + SEPARATOR + "100" + SEPARATOR + "2", statementLine);
    }
}