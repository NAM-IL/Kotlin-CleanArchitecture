package org.drulabs.bankbuddy.domain.usecases

import com.androidabcd.ashokslsk.kotlincleanarchitecture.entities.UserInfoEntity
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Background
import com.androidabcd.ashokslsk.kotlincleanarchitecture.qualifiers.Foreground
import com.androidabcd.ashokslsk.kotlincleanarchitecture.repository.BankingRepository
import com.androidabcd.ashokslsk.kotlincleanarchitecture.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetUserInfoTask @Inject constructor(
    private val bankingRepository: BankingRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<UserInfoEntity, String>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: String?): Observable<UserInfoEntity> {
        if (input == null) {
            throw IllegalArgumentException("User identifier can't be null")
        }
        return bankingRepository.getUserInfo(input)
    }

}