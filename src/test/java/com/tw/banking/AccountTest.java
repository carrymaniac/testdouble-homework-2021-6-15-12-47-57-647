package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @ProjectName: testdouble-homework
 * @Package: com.tw.banking
 * @ClassName: AccountTest
 * @Author: carrymaniac
 * @Description:
 * @Date: 2021/6/24 下午7:53
 * @Version:
 */
class AccountTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    Printer printer;

    @InjectMocks
    Account account;

    @Test
    void should_call_addDepoist_with_amout_when_deposit_given_amout() {
        //given
        int amount = 100;
        //when
        account.deposit(amount);
        //then
        assertAll(
                () -> verify(transactionRepository).addDeposit(amount)
        );
    }

    @Test
    void should_call_withdrad_with_amount_when_withdraw_given_amount() {
        //given
        int amount = 100;
        //when
        account.withdraw(amount);
        //then
        assertAll(
                () -> verify(transactionRepository).addWithdraw(amount)
        );
    }

    @Test
    void should_print_when_printStatement() {
        Transaction transaction1 = new Transaction(LocalDate.now().toString(),100);
        Transaction transaction2 = new Transaction(LocalDate.now().toString(),200);
        List<Transaction> transactionList = Arrays.asList(transaction1, transaction2);
        when(transactionRepository.allTransactions()).thenReturn(transactionList);
        ArgumentCaptor<List<Transaction>> captorTransactions = ArgumentCaptor.forClass(List.class);
        //when
        account.printStatement();

        //then
        assertAll(
                () -> verify(transactionRepository).allTransactions(),
                () -> verify(printer).print(captorTransactions.capture()),
                () -> assertEquals(transactionList,captorTransactions.getValue())

        );
    }
}