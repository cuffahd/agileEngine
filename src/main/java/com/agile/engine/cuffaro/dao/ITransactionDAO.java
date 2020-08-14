package com.agile.engine.cuffaro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.engine.cuffaro.model.TransactionItem;

public interface ITransactionDAO extends JpaRepository<TransactionItem, String>{

}
