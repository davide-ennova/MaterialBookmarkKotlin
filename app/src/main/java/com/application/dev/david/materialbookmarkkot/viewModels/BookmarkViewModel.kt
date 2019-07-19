package com.application.dev.david.materialbookmarkkot.viewModels

import android.app.Application
import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.dev.david.materialbookmarkkot.data.BookmarkListDataRepository
import com.application.dev.david.materialbookmarkkot.models.Bookmark
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import khronos.toDate

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {
    var bookmarksLiveData = MutableLiveData<List<Bookmark>>()
    private val list : ArrayList<Bookmark> = ArrayList()
    private val bookmarkListaDataRepository : BookmarkListDataRepository = BookmarkListDataRepository(getApplication())

    init {
        //mock data
        list.add(Bookmark("bll", "Instagram", "Image", "", "www.instagram.com", "2019-06-19".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Facebook", "Image", "", "www.facebook.com", "2019-05-01".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Google", "Image", "", "www.google.com", "2019-02-11".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Vodafone", "Image", "", "www.vodafone.com", "2019-04-22".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Outlook", "Image", "", "www.outlook.it", "2019-04-30".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Gmail", "Image", "", "www.gmail.com", "2019-05-18".toDate("yyyy-MM-dd")))
        list.add(Bookmark("bll", "Blablacar", "Image", "", "www.blablacar.com", "2019-05-12".toDate("yyyy-MM-dd")))
    }

    fun retrieveBookmarkList() {
        val disposable = Observable.just("")//fromArray(list)
            .flatMap { obs -> bookmarkListaDataRepository.getBookmarks()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
            .subscribe(
                {result -> (bookmarksLiveData as MutableLiveData).value = result },
                {error -> print(error.message)}
            )

    }
}