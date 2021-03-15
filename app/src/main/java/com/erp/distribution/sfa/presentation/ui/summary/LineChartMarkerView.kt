package com.erp.distribution.sfa.presentation.ui.summary

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.erp.distribution.sfa.R


class LineChartMarkerView(context: Context?, private val lineChart: LineChart, layoutResource: Int, axisX: AxisDateFormatter) : MarkerView(context, layoutResource), IMarker {
    private val square1: TextView
    private val square2: TextView
    private val square3: TextView
    private val item1: TextView
    private val item2: TextView
    private val item3: TextView

    private val Title: TextView
    private val XAxis: AxisDateFormatter

    init {
        square1 = findViewById(R.id.square1)
        square2 = findViewById(R.id.square2)
        square3 = findViewById(R.id.square3)
        item1 = findViewById(R.id.item1)
        item2 = findViewById(R.id.item2)
        item3 = findViewById(R.id.item3)
        Title = findViewById(R.id.txtTitle)
        XAxis = axisX
    }

    override fun refreshContent( e: Entry, highlight: Highlight) {
        try {
            Title.text = XAxis.getFormattedValue(e.x).toString()
            square1.setBackgroundColor(lineChart.data.getDataSetByIndex(0).color)
            square2.setBackgroundColor(lineChart.data.getDataSetByIndex(1).color)
            square3.setBackgroundColor(lineChart.data.getDataSetByIndex(2).color)
            val val1 = lineChart.data.getDataSetByIndex(0).getEntryForXValue(e.x, Float.NaN, DataSet.Rounding.CLOSEST) as Entry
            val val2 = lineChart.data.getDataSetByIndex(1).getEntryForXValue(e.x, Float.NaN, DataSet.Rounding.CLOSEST) as Entry
            val val3 = lineChart.data.getDataSetByIndex(2).getEntryForXValue(e.x, Float.NaN, DataSet.Rounding.CLOSEST) as Entry
            item1.text = String.format("%,.0f", val1.y)
            item2.text = String.format("%,.0f", val2.y)
            item3.text = String.format("%,.0f", val3.y)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        super.refreshContent(e, highlight)
    }

    private var mOffset: MPPointF? = null
    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
        }
        return mOffset!!
    }
}