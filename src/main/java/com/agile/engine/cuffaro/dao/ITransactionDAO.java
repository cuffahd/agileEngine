package com.agile.engine.cuffaro.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.agile.engine.cuffaro.model.TransactionItem;

/**
 * Dao to manage transaction operations
 * @author hcuff
 *
 */
public interface ITransactionDAO extends JpaRepository<TransactionItem, String>{

	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	TransactionItem save(TransactionItem entity) ;
}
