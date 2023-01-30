package com.example.myapplication.Rooms

import androidx.lifecycle.LiveData
import com.example.myapplication.Utility.Category

/**
 * The instance of LiveData returned by Room only listens for
 * changes while the fragment observing it is alive. Observation of
 * the data stops when the fragment is destroyed and resumes when it's created.
 *
 * Take for example, the following situation
 *
 * User goes to the deleted section
 * User restores 5 notes
 * User returns to the notes section
 *
 * The observation of notes had stopped when the user went
 * to the deleted section and is resumed when the user comes back.
 * This leads to lag and flickering.
 *
 * To stop this, this class acts as a wrapper over the LiveData returned
 * by Room and continuously observes it.
 */
class Content(liveData: LiveData<List<Category>>, transform: (List<Category>) -> List<Category>) : LiveData<List<Category>>() {

    init {
        liveData.observeForever { list -> value = transform(list) }
    }
}