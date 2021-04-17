package com.huyqgtran.queenlive.ui.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.huyqgtran.queenlive.domain.repo.FakeRepository
import com.huyqgtran.queenlive.getOrAwaitValue
import com.huyqgtran.queenlive.ui.mapper.ViewMapper
import com.huyqgtran.queenlive.ui.mapper.ViewMapperImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShowViewModelTest {
    private lateinit var fakeRepository: FakeRepository
    private lateinit var viewMapper: ViewMapper
    @Test
    fun getShows() {
        fakeRepository = FakeRepository()
        viewMapper = ViewMapperImpl()
        val showViewModel = ShowViewModel(fakeRepository, viewMapper, "Crazy Tour")
        val value = showViewModel.shows.getOrAwaitValue()

        assertNotNull(value)
    }
}