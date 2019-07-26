package com.androidabcd.ashokslsk.kotlincleanarchitecture

import com.androidabcd.ashokslsk.kotlincleanarchitecture.repository.BankingRepository
import com.androidabcd.ashokslsk.kotlincleanarchitecture.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.drulabs.bankbuddy.domain.usecases.GetUserInfoTask
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

/**
 * Created by Srinivasa Ashok Kumar on 2019-07-26.
 */
@RunWith(JUnit4::class)
class GetUserInfoTest {
    private lateinit var getUserInfoTask: GetUserInfoTask

    @Mock
    private lateinit var bankingRepository: BankingRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getUserInfoTask = GetUserInfoTask(
            bankingRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_getUserInfo_success(){
        val userInfo = TestDataGenerator.generateUserInfo()

        Mockito.`when`(bankingRepository.getUserInfo(userInfo.accountNumber))
            .thenReturn(Observable.just(userInfo))

        val ObserverTest = getUserInfoTask.buildUseCase(userInfo.accountNumber)
            .test()

        Mockito.verify(bankingRepository, times(1))
            .getUserInfo(userInfo.accountNumber)

        ObserverTest.assertSubscribed()
            .assertValue {it == userInfo}
            .assertComplete()

    }
}