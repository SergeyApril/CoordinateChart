package com.example.coordinatechart.ui.start_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coordinatechart.domen.entity.AppData
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
                    _coordinateState.value = StartScreenState.Initial
                }
            }

            else -> {
                _coordinateState.value =
                    StartScreenState.Error(message = exception.toString())
                _coordinateState.value = StartScreenState.Initial
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
            _coordinateState.value = StartScreenState.Success
            _coordinateState.value = StartScreenState.Initial
        }
    }
}

sealed class StartScreenState {
    data object Initial : StartScreenState()
    data object Loading : StartScreenState()
    data object Success : StartScreenState()
    class Error(val message: String) : StartScreenState()
}