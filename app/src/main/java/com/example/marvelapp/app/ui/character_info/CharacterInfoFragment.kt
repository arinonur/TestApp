package com.example.marvelapp.app.ui.character_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelapp.App
import com.example.marvelapp.R
import com.example.marvelapp.app.base.error.ErrorHandler
import com.example.marvelapp.app.base.hide
import com.example.marvelapp.app.base.longSnackBar
import com.example.marvelapp.app.base.show
import com.example.marvelapp.app.base.ui.BaseFragment
import com.example.marvelapp.app.ui.characters.CharactersViewModel
import com.example.marvelapp.app.ui.characters.adapter.CharacterComicsAdapter
import com.example.marvelapp.app.ui.characters.adapter.CharactersAdapter
import kotlinx.android.synthetic.main.character_info_fragment.*

class CharacterInfoFragment : BaseFragment() {

    companion object {
        private const val CHARACTER_ID_KEY = "characterIdKey"
        private const val CHARACTER_NAME_KEY = "characterNameKey"
        private const val IMAGE_URL_KEY = "imageUrlKey"
        const val TAG = "CharacterInfoFragmentTag"
        fun newInstance(
            characterId: Int,
            characterName: String?,
            imageUrl: String?
        ): CharacterInfoFragment {
            return CharacterInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_ID_KEY, characterId)
                    putString(CHARACTER_NAME_KEY, characterName)
                    putString(IMAGE_URL_KEY, imageUrl)
                }
            }
        }
    }

    private lateinit var viewModel: CharacterInfoViewModel
    private lateinit var viewModel2: CharactersViewModel
    private lateinit var adapter: CharacterComicsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val characterId = arguments!!.getInt(CHARACTER_ID_KEY)
        val characterName = arguments!!.getString(CHARACTER_NAME_KEY)
        val imageUrl = arguments!!.getString(IMAGE_URL_KEY)
        println("idddd: " + characterId)
        loadCharacterAvatar(imageUrl)
        tvCharacterName.text = characterName

        val dataComponent = (activity!!.application as App).provideDataComponent()
        val characterDataSource = dataComponent.provideCharacterRepository()
        val viewModelFactory = CharacterInfoViewModel.Factory(characterId, characterDataSource)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CharacterInfoViewModel::class.java)

        viewModel.getLoading().observe(viewLifecycleOwner, Observer {
            if (it) {
                pbLoading.show()
                tvCharacterDescription.hide()
            } else {
                tvCharacterDescription.show()
                pbLoading.hide()
            }
        })
        adapter = CharacterComicsAdapter(requireContext())
        rv_comics.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv_comics.adapter = adapter
        viewModel.getCharacterInfo().observe(viewLifecycleOwner, Observer {
            val description = it.description
            if (description != null && description.isNotEmpty()) {
                tvCharacterDescription.text = description
            } else {
                tvCharacterDescription.text = getString(R.string.description_not_found)
            }
        })

        viewModel.getCharacterComics().observe(viewLifecycleOwner, Observer {
            adapter.setMovieListItems(it)

        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            val errorHandler = ErrorHandler(context!!)
            view?.longSnackBar(errorHandler.getErrorMessage(it))
        })

    }

    private fun loadCharacterAvatar(imageUrl: String?) {
        Glide.with(ivCharacterAvatar)
            .load(imageUrl)
            .into(ivCharacterAvatar)
    }

}
