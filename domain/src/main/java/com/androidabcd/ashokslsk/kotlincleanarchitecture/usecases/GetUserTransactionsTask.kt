package org.drulabs.bankbuddy.domain.usecases

import com.androidabcd.ashokslsk.kotlincleanarchitecture.entities.TransactionEntity
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Background
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Foreground
import com.androidabcd.ashokslsk.kotlincleanarchitecture.repository.BankingRepository
import com.androidabcd.ashokslsk.kotlincleanarchitecture.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject


class GetUserTransactionsTask @Inject constructor(
    private val bankingRepository: BankingRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<List<TransactionEntity>, GetUserTransactionsTask.Params>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Params?): Observable<List<TransactionEntity>> {
        if (input == null) {
            throw IllegalArgumentException("GetUserTransactionsTask parameter can't be null")
        }
        return bankingRepository.getUserTransactions(input.identifier, input.limit)
    }

    data class Params(val identifier: String, val limit: Int)
}