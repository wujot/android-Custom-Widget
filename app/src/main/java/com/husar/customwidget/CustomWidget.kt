package com.husar.customwidget

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.sample_custom_widget.view.*

class CustomWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    @JvmField
    var seekBar: SeekBar
    @JvmField
    var toolTip: TextView

    private val customState: Int = 0


    var onItemSelectedListener: ((Int) -> Unit)? = null

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.sample_custom_widget, this, true)

        // Initialize fields
        seekBar = custom_widget_slider
        toolTip = custom_widget_tooltip

        // set initial state
        setPosition(50)

        // On Click Listener
        onSliderChangeListener(seekBar)
    }

    fun setToolTipContent(text: String) {
        this.toolTip.text = text
    }

    fun setPosition(position: Int) {
        seekBar.progress = position
        setToolTip(toolTip)
        setToolTipContent("${seekBar.progress}%")
    }

    fun setToolTip(toolTip: TextView) {
        toolTip.x = seekBar.thumb.bounds.centerX().toFloat()
    }

    fun onSliderChangeListener(slider: SeekBar) {
        slider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setPosition(progress)
                onItemSelectedListener!!.invoke(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onSaveInstanceState(): Parcelable {
        return super.onSaveInstanceState()

    }

    override fun onRestoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(state)
    }
}
