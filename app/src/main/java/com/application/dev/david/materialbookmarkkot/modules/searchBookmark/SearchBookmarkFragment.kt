package com.application.dev.david.materialbookmarkkot.modules.searchBookmark

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.application.dev.david.materialbookmarkkot.OnFragmentInteractionListener
import com.application.dev.david.materialbookmarkkot.R
import com.application.dev.david.materialbookmarkkot.ui.changeToolbarFont
import com.application.dev.david.materialbookmarkkot.viewModels.SearchBookmarkViewModel
import kotlinx.android.synthetic.main.fragment_add_bookmark.mbToolbarId
import kotlinx.android.synthetic.main.fragment_search_bookmark.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddBookmarkFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddBookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchBookmarkFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private val searchBookmarkViewModel: SearchBookmarkViewModel by lazy {
        ViewModelProviders.of(this).get(SearchBookmarkViewModel::class.java)
    }

    private val navigation: NavController? by lazy {
        view?.let { Navigation.findNavController(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add_bookmark, menu)
    }

    /**
     * init actionbar
     */
    private fun initActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(mbToolbarId)
        mbToolbarId.changeToolbarFont()
        mbToolbarId.title = "Search"
        mbToolbarId.visibility = View.VISIBLE
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun initView() {

        mbBookmarkSearchPasteClipboardButtonId.setOnClickListener {

        }
        searchBookmarkViewModel.bookmarkInfoLiveData.observe(this, Observer{ bookmarkInfo ->
            navigation?.navigate(R.id.addBookmarkFragment)
        })

        mbsearchBookmarkButtonViewId.setOnClickListener {
            val action = SearchBookmarkFragmentDirections
                .actionSearchBookmarkFragmentToAddBookmarkFragment(mbNewBookmarkUrlEditTextId.text.toString())
            navigation?.navigate(action)
        }

   }

    /**
     * get placeholder
     */
    private fun getPlaceholder(): Drawable? {
        return context?.let {ctx ->
            ContextCompat.getDrawable(ctx, R.drawable.ic_bookmark)?.let {
                DrawableCompat.setTint(it, ContextCompat.getColor(ctx, R.color.colorPrimary))
                it
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


}