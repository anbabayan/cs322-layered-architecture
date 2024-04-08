package sia;

import org.springframework.stereotype.Service;
import sia.model.Account;
import sia.model.AccountDTO;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(Long userId) {
        Account createdAcc = accountRepository.save(new Account(userId));
        return new AccountDTO(createdAcc.getUserId() + " " + createdAcc.getBalance());
    }

    @Override
    public AccountDTO credit(Double amount, Long toAccount) {
        Account account = accountRepository.findById(toAccount).get();
        Double balance = account.getBalance();
        account.setBalance(balance + amount);
        accountRepository.save(account);
        return new AccountDTO(account.getUserId() + " balance: " + account.getBalance());
    }

    @Override
    public AccountDTO debit(Double amount, Long fromAccount) {
        Account account = accountRepository.findById(fromAccount).get();
        Double balance = account.getBalance();
        account.setBalance(balance - amount);
        accountRepository.save(account);
        return new AccountDTO(account.getUserId() + " balance: " + account.getBalance());
    }
}
