package org.drulabs.bankbuddy.domain.usecases

import com.androidabcd.ashokslsk.kotlincleanarchitecture.entities.TransactionEntity
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Background
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Foreground
import com.androidabcd.ashokslsk.kotlincleanarchitecture.repository.BankingRepository
import com.androidabcd.ashokslsk.kotlincleanarchitecture.usecases.base.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject


class TransactionStatusUpdaterTask @Inject constructor(
    private val bankingRepository: BankingRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : CompletableUseCase<TransactionEntity>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun generateCompletable(input: TransactionEntity?): Completable {
        if (input == null) {
            throw IllegalArgumentException("TransactionStatusUpdaterTask parameter can't be null")
        }
        return bankingRepository.updateTransaction(input)
    }
}