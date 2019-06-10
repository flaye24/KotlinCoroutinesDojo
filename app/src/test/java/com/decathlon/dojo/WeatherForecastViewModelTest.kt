package com.decathlon.dojo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.decathlon.dojo.data.model.DailyForecast
import com.decathlon.dojo.data.source.WeatherForecastDataSource
import com.decathlon.dojo.utils.schedulers.BaseSchedulerProvider
import com.decathlon.dojo.utils.schedulers.ImmediateSchedulerProvider
import com.decathlon.dojo.weather.viewmodel.WeatherForecastViewModel
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException


class WeatherForecastViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var weatherForecastDataSource: WeatherForecastDataSource

    private lateinit var schedulerProvider: BaseSchedulerProvider

    private lateinit var weatherForecastViewModel: WeatherForecastViewModel

    private val mockedDailyForecast = listOf(
        DailyForecast(
            date = "Saturday, June 14",
            lowestTemp = "11°",
            highestTemp = "14°",
            weatherDescription = "Strong Breeze",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Friday, June 15",
            lowestTemp = "4°",
            highestTemp = "20°",
            weatherDescription = "Drizzle",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Monday, June 16",
            lowestTemp = "5°",
            highestTemp = "14°",
            weatherDescription = "Storm",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Wednesday, June 17",
            lowestTemp = "6°",
            highestTemp = "17°",
            weatherDescription = "Ragged Shower",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Saturday, June 18",
            lowestTemp = "12°",
            highestTemp = "18°",
            weatherDescription = "Mostly Clear",
            weatherResId = 2131099733
        )
    )

    private val expectedDailyForecast = listOf(
        DailyForecast(
            date = "Saturday, June 14",
            lowestTemp = "11°",
            highestTemp = "14°",
            weatherDescription = "Strong Breeze",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Friday, June 15",
            lowestTemp = "4°",
            highestTemp = "20°",
            weatherDescription = "Drizzle",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Monday, June 16",
            lowestTemp = "5°",
            highestTemp = "14°",
            weatherDescription = "Storm",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Wednesday, June 17",
            lowestTemp = "6°",
            highestTemp = "17°",
            weatherDescription = "Ragged Shower",
            weatherResId = 2131099733
        ),
        DailyForecast(
            date = "Saturday, June 18",
            lowestTemp = "12°",
            highestTemp = "18°",
            weatherDescription = "Mostly Clear",
            weatherResId = 2131099733
        )
    )


    @Before
    fun setupWeatherForecastViewModel() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Make the sure that all schedulers are immediate.
        schedulerProvider = ImmediateSchedulerProvider()

        // Get a reference to the class under test
        weatherForecastViewModel = WeatherForecastViewModel(weatherForecastDataSource, schedulerProvider)
    }

    @Test
    fun dailyForecasts__loadWeatherForecast__loadDailyForecastIntoView() {
        // Given an initialized WeatherForecastViewModel with daily forecasts
        `when`(weatherForecastDataSource.getDailyForecasts()).thenReturn(Single.just(mockedDailyForecast))

        // When loading of weather forecasts is requested
        weatherForecastViewModel.loadWeatherForecast()

        // Then daily forecast are loaded into view
        assertEquals(expectedDailyForecast, weatherForecastViewModel.weatherForecasts.value)
    }


    @Test
    fun dailyForecastsError__loadWeatherForecast__displayErrorMessage() {
        // Given an error occurred while loading daily forecasts
        `when`(weatherForecastDataSource.getDailyForecasts()).thenReturn(Single.error(RuntimeException("Error while loading daily forecasts")))

        // When loading of weather forecasts is requested
        weatherForecastViewModel.loadWeatherForecast()

        // Then error message is shown
        assertEquals("Error while loading daily forecasts", weatherForecastViewModel.displayErrorMessage.value)
    }
}
