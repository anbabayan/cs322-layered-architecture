package sia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sia.model.Account;
import sia.model.AccountDTO;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        reset(accountRepository);
    }

    @Test
    public void testCreateAccount() {
        Long userId = 123L;
        Account expectedAccount = new Account(userId);

        when(accountRepository.save(any(Account.class))).thenReturn(expectedAccount);

        AccountDTO result = accountService.createAccount(userId);
        Assertions.assertNotNull(result);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    public void testCredit() {
        Long toAccount = 123L;
        Double amount = 100.0;
        Account account = new Account(toAccount);
        account.setBalance(500.0);

        when(accountRepository.findById(toAccount)).thenReturn(java.util.Optional.of(account));

        AccountDTO result = accountService.credit(amount, toAccount);

        verify(accountRepository).findById(toAccount);
        verify(accountRepository).save(any(Account.class));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(toAccount + " balance: 600.0", result.info());
    }

    @Test
    public void testDebit() {
        Long fromAccount = 123L;
        Double amount = 100.0;
        Account account = new Account(fromAccount);
        account.setBalance(500.0);

        when(accountRepository.findById(fromAccount)).thenReturn(java.util.Optional.of(account));

        AccountDTO result = accountService.debit(amount, fromAccount);

        verify(accountRepository).findById(fromAccount);
        verify(accountRepository).save(any(Account.class));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(fromAccount + " balance: 400.0", result.info());
    }
}
