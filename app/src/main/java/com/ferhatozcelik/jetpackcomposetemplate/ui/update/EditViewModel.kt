package com.ferhatozcelik.jetpackcomposetemplate.ui.update

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import com.ferhatozcelik.jetpackcomposetemplate.util.MissingFieldException
import com.ferhatozcelik.jetpackcomposetemplate.util.addOrUpdateRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.Period
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class EditViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) :
    ViewModel() {
    private val TAG = EditViewModel::class.java.simpleName
    private val _routineName: MutableState<String> = mutableStateOf("")
    private val _routineDescription: MutableState<String> = mutableStateOf("")
    private val _selectedCategory: MutableState<Category?> = mutableStateOf(null)
    private val _selectedStartDate: MutableState<LocalDateTime> =
        mutableStateOf(LocalDateTime.now())
    private val _hasEndDate: MutableState<Boolean> = mutableStateOf(false)
    private val _selectedEndDate: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())
    private val _selectedPriority: MutableState<Priority?> = mutableStateOf(null)
    private val _selectedPeriod: MutableState<Period?> = mutableStateOf(null)
    var routineName: State<String> = _routineName
    var routineDescription: State<String> = _routineDescription
    var selectedCategory: State<Category?> = _selectedCategory
    var selectedStartDate: State<LocalDateTime> = _selectedStartDate
    var hasEndDate: State<Boolean> = _hasEndDate
    var selectedEndDate: State<LocalDateTime> = _selectedEndDate
    var selectedPriority: State<Priority?> = _selectedPriority
    var selectedPeriod: State<Period?> = _selectedPeriod

    fun updateRoutine(routine: Routine) {
        addOrUpdateRoutine(
            Routine(
                id = routine.id,
                name = if (routineName.value.isBlank()) throw MissingFieldException("nom") else routineName.value,
                description = routineDescription.value,
                category = selectedCategory.value ?: throw MissingFieldException("catégorie"),
                startTime = selectedStartDate.value,
                endTime = if (hasEndDate.value) {
                    selectedEndDate.value
                } else {
                    null
                },
                period = selectedPeriod.value,
                priority = selectedPriority.value ?: throw MissingFieldException("importance")
            )
        )
    }

    fun setName(name: String) {
        _routineName.value = name
    }

    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    fun setCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun setStartDate(date: LocalDateTime) {
        _selectedStartDate.value = date
    }

    fun setHasEndDate(bool: Boolean) {
        _hasEndDate.value = bool
    }

    fun setEndDate(date: LocalDateTime) {
        _selectedEndDate.value = date
    }

    fun setPriority(priority: Priority) {
        _selectedPriority.value = priority
    }

    fun setPeriod(period: Period?) {
        _selectedPeriod.value = period
    }

}
