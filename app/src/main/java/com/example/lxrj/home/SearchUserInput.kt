package com.example.lxrj.home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lxrj.R
import com.example.lxrj.home.PeopleUserInputAnimationState.Invalid
import com.example.lxrj.home.PeopleUserInputAnimationState.Valid
import com.example.lxrj.base.CraneUserInput
import com.example.lxrj.ui.theme.LxrjTheme

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/3/8 星期六  12:16
*/

enum class PeopleUserInputAnimationState { Valid, Invalid }

class PeopleUserInputState {
    var people by mutableStateOf(1)
        private set
    var animationState: MutableTransitionState<PeopleUserInputAnimationState> =
        MutableTransitionState(Valid)

    fun addPerson() {
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState = if (people > MAX_PEOPLE) Invalid else Valid
        if (animationState.currentState != newState) animationState.targetState = newState
    }
}


@Composable
fun PeopleUserInput(
    titleSuffix: String = "",
    onPeopleChanged: (Int) -> Unit,
    peopleState: PeopleUserInputState = remember { PeopleUserInputState() }
) {
    Column {
        val transitionState = remember { peopleState.animationState }
        val tint = tintPeopleUserInput(transitionState)
        val people = peopleState.people
        CraneUserInput(
            text = pluralStringResource(
                id = R.plurals.number_adults_selected,
                count = people,
                people,
                titleSuffix
            ),
            vectorImageId = R.drawable.ic_person,
            tint = tint.value,
            onClick = {
                peopleState.addPerson()
                onPeopleChanged(peopleState.people)
            }
        )
    }
}



@Composable
private fun tintPeopleUserInput(transitionState: MutableTransitionState<PeopleUserInputAnimationState>): State<Color> {
    val validColor = MaterialTheme.colorScheme.onSurface
    val invalidColor = MaterialTheme.colorScheme.secondary

    val transition = updateTransition(transitionState, label = "tintTransition")
    return transition.animateColor(
        transitionSpec = { tween(durationMillis = 300) }, label = "tintTransitionSpec"
    ) {
        if (it == Valid) validColor else invalidColor
    }
}

@Preview
@Composable
private fun PeopleUserInputPreview() {
    LxrjTheme {
        PeopleUserInput(onPeopleChanged = {})
    }
}