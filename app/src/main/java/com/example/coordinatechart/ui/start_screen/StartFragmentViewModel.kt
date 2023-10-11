package com.example.coordinatechart.ui.start_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coordinatechart.domen.entity.AppData
import com.example.coordinatechart.domen.entity.Coordinate
import com.example.coordinatechart.domen.usecase.GetCoordinateUseCaseApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class StartFragmentViewModel @Inject constructor(
    private val getCoordinateUseCase: GetCoordinateUseCaseApi,
    private val getaAppData: AppData
) :
    ViewModel() {
    private val _coordinateState: MutableStateFlow<StartScreenState> =
        MutableStateFlow(StartScreenState.Initial)
    val coordinateState = _coordinateState

    private val handler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                if (exception.code() != 200) {
                    val message = exception.response()?.errorBody()?.string() ?: exception.message()
                    _coordinateState.value = StartScreenState.Error(message = message)
                }
            }

            else -> {
                _coordinateState.value =
                    StartScreenState.Error(message = exception.toString())
            }
        }
    }

    fun onSentButtonClicked(pointValue: Int) {
        _coordinateState.value = StartScreenState.Loading
        viewModelScope.launch(handler) {
            // задержка только для того, чтоб полюбоваться прогресс баром
            delay(500)
            val coordinate = getCoordinateUseCase.execute(pointValue)
            getaAppData.setResultList(coordinate.pointList)
            Log.d("CoordinateApp", "coordinate is : ${coordinate.pointList.toString()}")
            _coordinateState.value = StartScreenState.Success(coordinate)
            _coordinateState.value = StartScreenState.Initial
        }
    }
}

sealed class StartScreenState {
    data object Initial : StartScreenState()
    data object Loading : StartScreenState()
    class Success(val coordinate: Coordinate) : StartScreenState()
    class Error(val message: String) : StartScreenState()
}