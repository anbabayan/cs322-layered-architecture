package sia.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"account\"")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double balance;

    public Account() {

    }

    public Account(Long userId) {
        this.userId = userId;
        this.balance = 0.0;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userId, account.userId) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, balance);
    }

    public Long getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }
}
