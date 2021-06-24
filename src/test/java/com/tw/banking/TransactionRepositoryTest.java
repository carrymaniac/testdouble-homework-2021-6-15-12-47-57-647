package com.tw.banking;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @ProjectName: testdouble-homework
 * @Package: com.tw.banking
 * @ClassName: TransactionRepositoryTest
 * @Author: carrymaniac
 * @Description:
 * @Date: 2021/6/24 下午8:19
 * @Version:
 */
class TransactionRepositoryTest {

    String dayString = "Test day";
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(clock.todayAsString()).thenReturn(dayString);
    }


    @InjectMocks
    TransactionRepository transactionRepository;
    @Mock
    Clock clock;

    @Test
    void should_add_deposit_transcation_to_transcationList_when_addDeposit() {
        //given
        int amount = 100;
        //when
        transactionRepository.addDeposit(amount);
        //then
        Transaction transaction = transactionRepository.transactions.get(0);
        assertNotNull(transaction);
        assertEquals(amount,transaction.amount());
        assertEquals(dayString,transaction.date());
    }

    @Test
    void should_add_withdraw_transcation_to_transcationList_when_addWithdraw() {
        //given
        int amount = 100;
        //when
        transactionRepository.addWithdraw(amount);
        //then
        Transaction transaction = transactionRepository.transactions.get(0);
        assertNotNull(transaction);
        assertEquals(-amount,transaction.amount());
        assertEquals(dayString,transaction.date());
    }

    @Test
    void should_return_transactions_when_call_allTransactions() {
        //given
        Transaction transaction1 = new Transaction(LocalDate.now().toString(),100);
        Transaction transaction2 = new Transaction(LocalDate.now().toString(),200);
        List<Transaction> expectTransactionList = Arrays.asList(transaction1, transaction2);
        transactionRepository.transactions = expectTransactionList;
        //when
        List<Transaction> transactions = transactionRepository.allTransactions();
        //then
        assertIterableEquals(expectTransactionList,transactions);
    }




}