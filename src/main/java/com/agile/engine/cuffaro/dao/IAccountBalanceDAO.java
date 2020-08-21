package com.agile.engine.cuffaro.dao;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.agile.engine.cuffaro.model.AccountBalance;

/**
 * DAO to manage Account Balance.
 * @author hcuff
 *
 */
public interface IAccountBalanceDAO  extends JpaRepository<AccountBalance, Long>{

	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<AccountBalance> findById(Long id);
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	AccountBalance save(AccountBalance entity) ;
}
