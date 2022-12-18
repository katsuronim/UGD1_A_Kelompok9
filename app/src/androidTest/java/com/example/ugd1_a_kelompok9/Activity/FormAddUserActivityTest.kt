package com.example.ugd1_a_kelompok9.Activity


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.ugd1_a_kelompok9.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FormAddUserActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(FormAddUserActivity::class.java)

    @Test
    fun formAddUserActivityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText = onView(
            allOf(
                withId(R.id.txt_nama),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Andreas Noah Jati Sesoca"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.txt_username),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("rereandreas"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.txt_username), withText("rereandreas"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("andreasnoah"))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.txt_username), withText("andreasnoah"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.txt_password),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("rere123"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.txt_notelp),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(replaceText("123"), closeSoftKeyboard())

        val materialButton6 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.txt_notelp), withText("123"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText8.perform(replaceText("1234567890123"))

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.txt_notelp), withText("1234567890123"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(closeSoftKeyboard())

        val materialButton7 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText10 = onView(
            allOf(
                withId(R.id.txt_notelp), withText("1234567890123"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText10.perform(replaceText("1234567890"))

        val textInputEditText11 = onView(
            allOf(
                withId(R.id.txt_notelp), withText("1234567890"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText11.perform(closeSoftKeyboard())

        val materialButton8 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText12 = onView(
            allOf(
                withId(R.id.txt_tgllahir),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        textInputEditText12.perform(replaceText("30-11-2002"), closeSoftKeyboard())

        val materialButton9 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText13 = onView(
            allOf(
                withId(R.id.txt_email),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textInputEditText13.perform(replaceText("thehallpora@gmail.com"), closeSoftKeyboard())

        val materialButton10 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText14 = onView(
            allOf(
                withId(R.id.txt_email), withText("thehallpora@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textInputEditText14.perform(replaceText("rereandreas9@gmail.com"))

        val textInputEditText15 = onView(
            allOf(
                withId(R.id.txt_email), withText("rereandreas9@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textInputEditText15.perform(closeSoftKeyboard())

        val materialButton11 = onView(
            allOf(
                withId(R.id.btn_add), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton11.perform(click())
        onView(isRoot()).perform(waitFor(3000))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
