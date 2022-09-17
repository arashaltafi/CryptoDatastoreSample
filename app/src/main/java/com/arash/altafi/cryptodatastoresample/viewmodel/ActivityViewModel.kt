package com.arash.altafi.cryptodatastoresample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arash.altafi.cryptodatastoresample.model.AppSettings
import com.arash.altafi.cryptodatastoresample.model.Language
import com.arash.altafi.cryptodatastoresample.model.User
import com.arash.altafi.cryptodatastoresample.repository.ImplRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val implRepository: ImplRepository) :
    ViewModel() {

    private var language: Language = Language.ENGLISH
    var user: User = User()

    var appSettings: MutableLiveData<AppSettings> = MutableLiveData()

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.saveAppSettings(
                language = language,
                user = user
            )
        }
    }

    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.getAppSettings().collect {
                appSettings.postValue(it)
            }
        }
    }
}