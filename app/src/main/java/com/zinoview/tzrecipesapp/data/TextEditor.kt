package com.zinoview.tzrecipesapp.data

interface TextEditor {

    fun substring(srcText: String) : String

    class Base : TextEditor {

        override fun substring(srcText: String) : String {
            return if (srcText.length > END_INDEX) {
                srcText.substring(START_INDEX, END_INDEX) + "..."
            } else {
                srcText
            }
        }

        private companion object {
            private const val START_INDEX = 0
            private const val END_INDEX = 80
        }
    }
}