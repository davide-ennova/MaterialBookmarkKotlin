package com.application.dev.david.materialbookmarkkot.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.application.dev.david.materialbookmarkkot.R

class MbBookmarkPreviewView : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context, R.layout.add_bookmark_preview_view, this)
    }
}
