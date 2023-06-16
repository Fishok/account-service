package accounting.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import accounting.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

	Account findByAccountNumber(String accountNumber);

	List<Account> findByBalanceBetween(double minBalance, double maxBalance);
}
