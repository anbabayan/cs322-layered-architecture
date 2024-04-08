package sia;

import sia.model.AccountDTO;
public interface AccountController {

    public AccountDTO createAccount(Long userId);

    public AccountDTO credit(Double amount, Long fromAccount);

    public AccountDTO debit(Double amount, Long toAccount);
}
