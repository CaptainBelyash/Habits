package com.example.domainmodule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.extensions.TestDecorator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito.times

class DomainUnitTest {
    private lateinit var mockRepo: IHabitsRepo
    private lateinit var habit1: HabitEntity
    private lateinit var habit2: HabitEntity

    private lateinit var useCases: HabitsUseCases

    val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)

        habit1 = HabitEntity("id1", "n1", "d1", Priority.High, 15, HabitType.Good)
        habit2 = HabitEntity("id2", "n2", "d2", Priority.Low, 2, HabitType.Bad)
        mockRepo = mock()
        whenever(mockRepo.getHabitById("id1")).thenReturn(flow {habit1})
        whenever(mockRepo.getHabitById("id2")).thenReturn(flow {habit2})
        whenever(mockRepo.getHabits()).thenReturn(flow { listOf(habit1, habit2) })
        useCases = HabitsUseCases(mockRepo)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun useCase_getByIdFromRepo() = runBlockingTest {
        val habitFromUseCase = useCases.getHabitById("id1").count()
        Assert.assertEquals(1, habitFromUseCase)
    }

    @Test
    fun useCase_deleteFromRepo() = runBlockingTest {
        useCases.delete(habit2)
        verify(mockRepo, times(1)).delete(habit2)
    }

    @Test
    fun useCase_insertFromRepo() = runBlockingTest {
        useCases.insert(habit2)
        verify(mockRepo, times(1)).insert(habit2)
    }

    @Test
    fun useCase_refreshFromRepo() {
        useCases.refresh()
        verify(mockRepo, times(1)).refresh()
    }
}