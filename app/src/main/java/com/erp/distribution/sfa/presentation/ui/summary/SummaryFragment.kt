package com.erp.distribution.sfa.presentation.ui.summary

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentSummaryBinding
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SummaryFragment : Fragment(R.layout.fragment_summary) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val TAG = SummaryFragment::class.java.simpleName
    val viewModel: SummaryViewModel by viewModels<SummaryViewModel>()

    lateinit var viewBinding: FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSummaryBinding.bind(view)

        showChart()
    }

    fun showChart() {
        //Konfigurasi Line Chart
        viewBinding.apply {

            lineChart.description.isEnabled = false
    //        lineChart.setDrawGridBackground(false)
    //        lineChart.setDrawBorders(false)
    //        lineChart.getAxisLeft().setEnabled(false)
    //        lineChart.getAxisRight().setDrawAxisLine(false)
    //        lineChart.getAxisRight().setDrawGridLines(false)
    //        lineChart.getXAxis().setDrawAxisLine(false)
    //        lineChart.getXAxis().setDrawGridLines(false)
    //        lineChart.setTouchEnabled(true) // enable touch gestures. Allows to enable/disable all possible touch-interactions with the chart.
    //        lineChart.setDragEnabled(true) // enable scaling and dragging
    //        lineChart.setScaleEnabled(true) //Enables/disables scaling for the chart on both axes.
    //        lineChart.setScaleXEnabled(true) //Enables/disables scaling on the x-axis and y-axis
    //        lineChart.setPinchZoom(false) // if disabled, scaling can be done on x- and y-axis can be zoomed separately
    //        lineChart.axisLeft.isEnabled = false
    //        lineChart.axisLeft.spaceTop = 40f
    //        lineChart.axisLeft.spaceBottom = 40f
    //        lineChart.axisRight.isEnabled = false
    //        lineChart.xAxis.isEnabled = true
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    //        lineChart.xAxis.setDrawGridLines(true);
    //        lineChart.xAxis.granularity = 1f;

            //Setting Legend
            val legend = lineChart.legend
            legend.isEnabled = true
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER)
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
            legend.setDrawInside(false)

            val salesInvoiceRp = ArrayList<Entry>()
            salesInvoiceRp.add(Entry(0F, 149F))
            salesInvoiceRp.add(Entry(1F, 113F))
            salesInvoiceRp.add(Entry(2F, 196F))
            salesInvoiceRp.add(Entry(3F, 106F))
            salesInvoiceRp.add(Entry(4F, 181F))
            salesInvoiceRp.add(Entry(5F, 218F))
            salesInvoiceRp.add(Entry(6F, 247F))
            salesInvoiceRp.add(Entry(7F, 218F))
            salesInvoiceRp.add(Entry(8F, 337F))
            salesInvoiceRp.add(Entry(9F, 219F))

            val salesDeliveredRp = ArrayList<Entry>()
            salesDeliveredRp.add(Entry(0F, 22F))
            salesDeliveredRp.add(Entry(1F, 9F))
            salesDeliveredRp.add(Entry(2F, 22F))
            salesDeliveredRp.add(Entry(3F, 16F))
            salesDeliveredRp.add(Entry(4F, 14F))
            salesDeliveredRp.add(Entry(5F, 28F))
            salesDeliveredRp.add(Entry(6F, 12F))
            salesDeliveredRp.add(Entry(7F, 18F))
            salesDeliveredRp.add(Entry(8F, 30F))
            salesDeliveredRp.add(Entry(9F, 30F))

            val efectiveCall = ArrayList<Entry>()
            efectiveCall.add(Entry(0F, 21F))
            efectiveCall.add(Entry(1F, 13F))
            efectiveCall.add(Entry(2F, 11F))
            efectiveCall.add(Entry(3F, 10F))
            efectiveCall.add(Entry(4F, 7F))
            efectiveCall.add(Entry(5F, 11F))
            efectiveCall.add(Entry(6F, 12F))
            efectiveCall.add(Entry(7F, 19F))
            efectiveCall.add(Entry(8F, 40F))
            efectiveCall.add(Entry(9F, 26F))

            val salesInvoiceRpLineDataSet = LineDataSet(salesInvoiceRp, "Sales")
            salesInvoiceRpLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            salesInvoiceRpLineDataSet.color = Color.BLUE
            salesInvoiceRpLineDataSet.circleRadius = 5f
            salesInvoiceRpLineDataSet.setCircleColor(Color.BLUE)

            val salesDeliveredRpLineDataSet = LineDataSet(salesDeliveredRp, "Terkirim")
            salesDeliveredRpLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            salesDeliveredRpLineDataSet.color = Color.GREEN
            salesDeliveredRpLineDataSet.circleRadius = 5f
            salesDeliveredRpLineDataSet.setCircleColor(Color.GREEN)

            val efectiveCallLineDataSet = LineDataSet(efectiveCall, "EC")
            efectiveCallLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            efectiveCallLineDataSet.color = Color.RED
            efectiveCallLineDataSet.circleRadius = 5f
            efectiveCallLineDataSet.setCircleColor(Color.RED)

            var date = ArrayList<String>();
            date.add("01-Apr")
            date.add("02-Apr")
            date.add("03-Apr")
            date.add("04-Apr")
            date.add("05-Apr")
            date.add("06-Apr")
            date.add("07-Apr")
            date.add("08-Apr")
            date.add("09-Apr")
            date.add("10-Apr")
            val tanggal = AxisDateFormatter(date.toArray(arrayOfNulls<String>(date.size)))
            lineChart.xAxis?.setValueFormatter(tanggal);

            val marker : IMarker = LineChartMarkerView(context, lineChart, R.layout.markerview_three_item, tanggal);
            lineChart.marker = marker;

            lineChart.data = LineData(salesInvoiceRpLineDataSet, salesDeliveredRpLineDataSet, efectiveCallLineDataSet)
            lineChart.animateXY(100, 500)
        }

    }


}
