package com.agile.engine.cuffaro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.engine.cuffaro.model.AccountBalance;

/**
 * DAO to manage Account Balance.
 * @author hcuff
 *
 */
public interface IAccountBalanceDAO  extends JpaRepository<AccountBalance, Long>{

}
