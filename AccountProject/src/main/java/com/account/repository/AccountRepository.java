package com.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.account.entity.Account;
@Repository
public interface AccountRepository extends CrudRepository<Account, Long >
{

}
