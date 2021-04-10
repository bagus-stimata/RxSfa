package com.erp.distribution.sfa.presentation.ui.summary

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentSummaryBinding
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class SummaryFragment : Fragment(R.layout.fragment_summary) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val TAG = SummaryFragment::class.java.simpleName

    val viewModel: SummaryViewModel by viewModels<SummaryViewModel>()
    private val args: SummaryFragmentArgs by navArgs()

    lateinit var viewBinding: FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSummaryBinding.bind(view)

//        showPieChart()
//        showLineChart()
//        showPieChart2()
//        showPieChart3()

        /**
         * Agar navigasi BackStage dan tombol android.R.id.hame melewati satu
         * pintu(method) maka
         */
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                myPopBackStack()
            }
        })

        this.setHasOptionsMenu(true);
        /**
         * LOADING LIVE DATA TO VIEW
         */
        viewModel.summaryReportLive
                .observe(viewLifecycleOwner) {

                    var totalSales = 0f
                    var targetAmount = 0f
                    var totalDelivery = 0f
                    for (sysvar in it){
                        if (sysvar.sysvarId.equals("salesThisMonth")) totalSales = sysvar.nilaiDouble1.toFloat()
                        if (sysvar.sysvarId.equals("salesThisMonthTerkirimSaja")) totalDelivery = sysvar.nilaiDouble1.toFloat()
                        if (sysvar.sysvarId.equals("targetSalesAmount")) {
                            targetAmount = sysvar.nilaiDouble1.toFloat()
                        }
                    }
                    /**
                     * Menghindari error pembagian karena null
                     */
                    if (totalSales==0f) totalSales = 2f
                    if (targetAmount <=1) targetAmount = totalSales

                    yDataSales = floatArrayOf(totalSales, targetAmount-totalSales)
                    xDataSales = arrayOf("Sales Total", "")

                    var persentaseSales = (totalSales/targetAmount) * 100
                    strPersentaseSales = "${persentaseSales.toInt().toString()}%"
                    showPieChart2()


                    yDataDelivery = floatArrayOf(totalDelivery, totalSales-totalDelivery)
                    xDataDelivery = arrayOf("Sales Total", "")

                    if (totalDelivery==0f) totalDelivery = 2f
                    var persentaseDelivery = (totalDelivery/totalSales) * 100
                    strPersentaseDelivery = "${persentaseDelivery.toInt().toString()}%"

                    showPieChart3()


                }

    }

    fun showPieChart(){
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = "type"

        //initializing data

        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap["Invoice Amount"] = 100
        typeAmountMap["TargetAmount"] = 0
//        typeAmountMap["Clothes"] = 100
//        typeAmountMap["Stationary"] = 500
//        typeAmountMap["Phone"] = 50

        //initializing colors for the entries

        //initializing colors for the entries
        val colors: ArrayList<Int> = ArrayList()
//        colors.add(Color.parseColor("#304567"))
//        colors.add(Color.parseColor("#309967"))
//        colors.add(Color.parseColor("#476567"))
//        colors.add(Color.parseColor("#890567"))
//        colors.add(Color.parseColor("#a35567"))
//        colors.add(Color.parseColor("#ff5f67"))
//        colors.add(Color.parseColor("#3ca567"))

        colors.add(Color.parseColor("#3ca567"))
        colors.add(Color.parseColor("#304567"))

        //input data and fit data into pie chart entry

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }

        //collecting the entries with label name

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)
        //setting text size of the value
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //grouping the data set from entry to chart
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)

        viewBinding.pieChart.setData(pieData)
        viewBinding.pieChart.invalidate()
    }

    fun showLineChart() {
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


    private var yDataSales = floatArrayOf(50.3f, 50f)
    private var xDataSales = arrayOf("Sales Total", "")
    private var strPersentaseSales :String = "0"

    private fun showPieChart2(){
//        pieChart = findViewById(R.id.idPieChart) as PieChart
        val description :Description = Description()
        description.text = ""
        description.textSize = 10f
//        description.setPosition(200f, 200f)
        viewBinding.pieChart.description = description


        viewBinding.pieChart.isRotationEnabled = true
//        viewBinding.pieChart.setUsePercentValues(true);
        //viewBinding.pieChart.setHoleColor(Color.BLUE);
        //viewBinding.pieChart.setCenterTextColor(Color.BLACK);
        //viewBinding.pieChart.setUsePercentValues(true);
        //viewBinding.pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        viewBinding.pieChart.holeRadius = 45f
        viewBinding.pieChart.setTransparentCircleAlpha(0)
        viewBinding.pieChart.centerText = "${strPersentaseSales}"
        viewBinding.pieChart.setCenterTextSize(25f)
        viewBinding.pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD)
//        viewBinding.pieChart.setDrawEntryLabels(true);
//        viewBinding.pieChart.setEntryLabelTextSize(40f)
        //More options just check out the documentation!

        viewBinding.pieChart.setDrawEntryLabels(true);
        viewBinding.pieChart.setEntryLabelTextSize(20f);
        //More options just check out the documentation!
        addDataSet2()

        viewBinding.pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                var pos1 = e.toString().indexOf("(sum): ")
                val sales = e.toString().substring(pos1 + 7)
                for (i in yDataSales.indices) {
                    try {
                        if (yDataSales[i] == sales.toFloat()) {
                            pos1 = i
                            break
                        }
                    }catch (e: Exception){}
                }
                val employee = xDataSales[pos1 + 1]
            }

            override fun onNothingSelected() {}
        })
    }


    private fun addDataSet2() {

        val yEntrys = java.util.ArrayList<PieEntry>()
        val xEntrys = java.util.ArrayList<String>()
        for (i in yDataSales.indices) {
            yEntrys.add(PieEntry(yDataSales.get(i), i))
        }
        for (i in 1 until xDataSales.size) {
            xEntrys.add(xDataSales.get(i))
        }

        //create the data set
        val pieDataSet = PieDataSet(yEntrys, "Sales")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 12f


        //add colors to dataset
        val colors = java.util.ArrayList<Int>()
        colors.add(Color.MAGENTA)
        colors.add(Color.DKGRAY)
//        colors.add(Color.BLUE)
//        colors.add(Color.RED)
//        colors.add(Color.GREEN)
//        colors.add(Color.CYAN)
//        colors.add(Color.YELLOW)
//        colors.add(Color.MAGENTA)
        pieDataSet.colors = colors

        //add legend to chart
        val legend: Legend = viewBinding.pieChart.getLegend()
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART)
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.form = Legend.LegendForm.DEFAULT
        legend.direction = Legend.LegendDirection.LEFT_TO_RIGHT

        //create pie data object
        val pieData = PieData(pieDataSet)
        viewBinding.pieChart.setData(pieData)
        viewBinding.pieChart.invalidate()
    }

    private var yDataDelivery = floatArrayOf(50.3f, 50f)
    private var xDataDelivery = arrayOf("Delivery", "")
    private var strPersentaseDelivery :String = "0"

    private fun showPieChart3(){
//        pieChart = findViewById(R.id.idPieChart) as PieChart
        val description :Description = Description()
        description.text = ""
        description.textSize = 10f
//        description.setPosition(200f, 200f)
        viewBinding.pieChart2.description = description


        viewBinding.pieChart2.isRotationEnabled = true
//        viewBinding.pieChart2.setUsePercentValues(true);
        //viewBinding.pieChart2.setHoleColor(Color.BLUE);
        //viewBinding.pieChart2.setCenterTextColor(Color.BLACK);
        //viewBinding.pieChart2.setUsePercentValues(true);
        //viewBinding.pieChart2.setHoleColor(Color.BLUE);
        //pieChart2.setCenterTextColor(Color.BLACK);
        viewBinding.pieChart2.holeRadius = 45f
        viewBinding.pieChart2.setTransparentCircleAlpha(0)
        viewBinding.pieChart2.centerText = "${strPersentaseDelivery}"
        viewBinding.pieChart2.setCenterTextSize(25f)
        viewBinding.pieChart2.setCenterTextTypeface(Typeface.DEFAULT_BOLD)
//        viewBinding.pieChart.setDrawEntryLabels(true);
//        viewBinding.pieChart.setEntryLabelTextSize(40f)
        //More options just check out the documentation!

        viewBinding.pieChart2.setDrawEntryLabels(true);
        viewBinding.pieChart2.setEntryLabelTextSize(20f);
        //More options just check out the documentation!
        addDataSet3()

        viewBinding.pieChart2.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                var pos1 = e.toString().indexOf("(sum): ")
                val sales = e.toString().substring(pos1 + 7)
                for (i in yDataDelivery.indices) {
                    try {
                        if (yDataSales[i] == sales.toFloat()) {
                            pos1 = i
                            break
                        }
                    }catch (e: Exception){}
                }
                val employee = xDataDelivery[pos1 + 1]
            }

            override fun onNothingSelected() {}
        })
    }


    private fun addDataSet3() {

        val yEntrys = java.util.ArrayList<PieEntry>()
        val xEntrys = java.util.ArrayList<String>()
        for (i in yDataDelivery.indices) {
            yEntrys.add(PieEntry(yDataDelivery.get(i), i))
        }
        for (i in 1 until xDataDelivery.size) {
            xEntrys.add(xDataDelivery.get(i))
        }

        //create the data set
        val pieDataSet = PieDataSet(yEntrys, "Delivery")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 12f


        //add colors to dataset
        val colors = java.util.ArrayList<Int>()
        colors.add(Color.CYAN)
        colors.add(Color.DKGRAY)
//        colors.add(Color.BLUE)
//        colors.add(Color.RED)
//        colors.add(Color.GREEN)
//        colors.add(Color.CYAN)
//        colors.add(Color.YELLOW)
//        colors.add(Color.MAGENTA)
        pieDataSet.colors = colors

        //add legend to chart
        val legend: Legend = viewBinding.pieChart2.getLegend()
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART)
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.form = Legend.LegendForm.DEFAULT
        legend.direction = Legend.LegendDirection.LEFT_TO_RIGHT

        //create pie data object
        val pieData = PieData(pieDataSet)
        viewBinding.pieChart2.setData(pieData)
        viewBinding.pieChart2.invalidate()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_fragment_fmaterial, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                myPopBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun myPopBackStack() {
        findNavController().popBackStack()
    }


}
