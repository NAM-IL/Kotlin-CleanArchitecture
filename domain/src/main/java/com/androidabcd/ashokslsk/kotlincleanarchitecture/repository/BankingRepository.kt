package com.androidabcd.ashokslsk.kotlincleanarchitecture.repository

import com.androidabcd.ashokslsk.kotlincleanarchitecture.entities.TransactionEntity
import com.androidabcd.ashokslsk.kotlincleanarchitecture.entities.UserInfoEntity
import io.reactivex.Completable
import io.reactivex.Observable


interface BankingRepository {

    fun getUserInfo(identifier: String): Observable<UserInfoEntity>

    fun getUserTransactions(userIdentifier: String, limit: Int): Observable<List<TransactionEntity>>

    fun getFilteredTransactions(
        userIdentifier: String,
        credit: Boolean,
        debit: Boolean,
        flagged: Boolean
    ): Observable<List<TransactionEntity>>

    fun updateTransaction(transaction: TransactionEntity): Completable
}
