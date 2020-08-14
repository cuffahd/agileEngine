package com.agile.engine.cuffaro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.engine.cuffaro.model.AccountBalance;

public interface IAccountBalanceDAO  extends JpaRepository<AccountBalance, Long>{

}
