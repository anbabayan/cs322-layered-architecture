package sia;

import sia.model.AccountDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @PostMapping
    public AccountDTO createAccount(@RequestParam Long userId) {
        return accountService.createAccount(userId);
    }

    @Override
    @PostMapping("/credit")
    public AccountDTO credit(@RequestParam Double amount, @RequestParam Long toAccount) {
        return accountService.credit(amount, toAccount);
    }

    @Override
    @PostMapping("/debit")
    public AccountDTO debit(@RequestParam Double amount, @RequestParam Long fromAccount) {
        return accountService.debit(amount, fromAccount);
    }
}
