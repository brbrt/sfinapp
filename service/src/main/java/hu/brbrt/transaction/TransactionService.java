package hu.brbrt.transaction;

import hu.brbrt.account.Account;
import hu.brbrt.account.AccountRepository;
import hu.brbrt.core.TransactionType;
import hu.brbrt.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static hu.brbrt.core.TransactionType.*;

@Service
@Transactional
@Validated
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction get(int id) {
        Transaction transaction = transactionRepository.get(id);
        return postProcess(transaction);
    }

    public Transaction getSkeleton() {
        Integer accountId = accountRepository.getAll()
                .stream()
                .findAny()
                .map(Account::getId)
                .orElse(null);

        return new Transaction()
                .setDate(LocalDate.now())
                .setType(Expense)
                .setAccountId(accountId);
    }

    public int create(@Valid @NotNull Transaction transaction) {
        Transaction preparedTransaction = prepare(transaction);
        return transactionRepository.create(preparedTransaction);
    }

    public void delete(int id) {
        transactionRepository.delete(id);
    }

    public void update(@Valid @NotNull Transaction transaction) {
        transactionRepository.update(transaction);
    }

    private Transaction prepare(Transaction transaction) {
        transaction.setAmount(corrigateAmount(transaction.getAmount(), transaction.getType()));
        return transaction;
    }

    private double corrigateAmount(double amount, TransactionType type) {
        if (type == Expense && amount > 0
            || type == Income && amount < 0
            || type == Transfer && amount < 0) {
            return amount * -1;
        }

        return amount;
    }

    private Transaction postProcess(Transaction transaction) {
        transaction.setType(TransactionType.calculate(transaction.getToAccountId(), transaction.getAmount()));
        return transaction;
    }

}
