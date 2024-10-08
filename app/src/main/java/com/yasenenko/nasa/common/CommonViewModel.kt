package com.yasenenko.nasa.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasenenko.nasa.R
import com.yasenenko.nasa.domain.model.ErrorModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.atomic.AtomicInteger

open class CommonViewModel : ViewModel() {

    protected val _error: SingleLiveEvent<ErrorModel> =
        SingleLiveEvent()

    protected val _progress: SingleLiveEvent<Boolean> =
        SingleLiveEvent()

    private val requestsCount: AtomicInteger by lazy {
        AtomicInteger(0)
    }

    val error: LiveData<ErrorModel>
        get() = _error


    val progressEvent: LiveData<Boolean>
        get() = _progress


    protected fun ViewModel.tryHttp(invoke: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch {
            _progress.postValue(true)
            requestsCount.incrementAndGet()
            try {
                invoke()
            } catch (e: SocketTimeoutException) {
                _error.postValue(ErrorModel(errorRes = R.string.not_answer_from_server))
            } catch (e: IOException) {
                _error.postValue(ErrorModel(errorRes = R.string.no_internet))
            } catch (e: HttpException) {
                // Implement error handling
                e.printStackTrace()

            } catch (e: Throwable) {
                _error.postValue(ErrorModel(errorRes = R.string.something_went_wrong))
            } finally {
                val count = requestsCount.decrementAndGet()
                _progress.postValue(count != 0)
            }
        }


}