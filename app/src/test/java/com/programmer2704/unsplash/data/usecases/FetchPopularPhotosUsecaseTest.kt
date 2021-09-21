package com.programmer2704.unsplash.data.usecases

import com.MockTestUtil
import com.programmer2704.unsplash.data.DataState
import com.programmer2704.unsplash.data.repository.ImagineRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchPopularPhotosUsecaseTest {

    @MockK
    private lateinit var repository: ImagineRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking FetchPopularPhotosUsecase gives list of photos`() = runBlocking {
        // Given
        val usecase = FetchPopularPhotosUsecase(repository)
        val givenPhotos = MockTestUtil.createPhotos(3)

        // When
        coEvery { repository.loadPhotos(any(), any(), any()) }
            .returns(flowOf(DataState.success(givenPhotos)))

        // Invoke
        val photosListFlow = usecase(1, 1, "")

        // Then
        MatcherAssert.assertThat(photosListFlow, CoreMatchers.notNullValue())

        val photosListDataState = photosListFlow.first()
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val photosList = (photosListDataState as DataState.Success).data
        MatcherAssert.assertThat(photosList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosList.size, `is`(givenPhotos.size))
    }
}
