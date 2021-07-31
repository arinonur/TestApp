package com.example.marvelapp.app.ui.character_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelapp.app.base.observeOnUI
import com.example.marvelapp.app.base.subscribeOnIO
import com.example.marvelapp.app.base.viewmodel.BaseViewModel
import com.example.marvelapp.app.source.CharacterDataSource
import com.example.marvelapp.data.model.character.CharacterComics
import com.example.marvelapp.data.model.character.CharacterInfo


class CharacterInfoViewModel(
    private val characterId: Int,
    private val characterDataSource: CharacterDataSource
) : BaseViewModel() {

    private val characterInfo = MutableLiveData<CharacterInfo>()
    private val characterComics = MutableLiveData<List<CharacterComics>>()

    init {
        loading.value = true
        unsubscribeOnClear(
            characterDataSource.getCharacterInfo(characterId)
                .subscribeOnIO()
                .observeOnUI()
                .subscribe(this::onCharacterInfoLoaded, this::onError)

        )
        unsubscribeOnClear(
            characterDataSource.getCharacterComics(characterId)
                .subscribeOnIO()
                .observeOnUI()
                .subscribe(this::onCharacterComicsLoaded, this::onError)

        )
    }

    private fun onCharacterInfoLoaded(characterInfo: CharacterInfo) {
        loading.value = false
        this.characterInfo.value = characterInfo
    }
    private fun onCharacterComicsLoaded(characterComics: List<CharacterComics>) {
        loading.value = false
        this.characterComics.value = characterComics
    }

    private fun onError(throwable: Throwable) {
        error.value = throwable
    }

    fun getCharacterInfo(): LiveData<CharacterInfo> {
        return characterInfo
    }

    fun getCharacterComics(): LiveData<List<CharacterComics>> {
        return characterComics
    }

    class Factory(private val id: Int, private val characterDataSource: CharacterDataSource) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterInfoViewModel(id, characterDataSource) as T
        }

    }

}
