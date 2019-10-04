package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.android.synthetic.main.fragment_bolsa_dois.*

import proitdevelopers.com.bloomberg.R

class BolsaDoisFragment : Fragment() {

    val colorArray = listOf(R.color.color1, R.color.color2, R.color.color3, R.color.color4)
    val colorClassArray = listOf(Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA)
    val legendName = listOf("Kwanza","Euro","Dolár","Libra")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bolsa_dois, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lineDataSet1 = LineDataSet(dataValues1(),"Ganhos")
        val lineDataSet2 = LineDataSet(dataValues2(),"Perdas")
        val dataSets = ArrayList<LineDataSet>()
        dataSets.add(lineDataSet1)
        dataSets.add(lineDataSet2)

        lineDataSet1.lineWidth = 0.5f
        lineDataSet1.setColor(Color.RED)
        lineDataSet1.setDrawCircles(false)
        lineDataSet1.setDrawFilled(true)
        lineDataSet1.setDrawValues(false)
        lineDataSet1.setDrawCircleHole(true)
        lineDataSet1.setCircleColor(Color.GRAY)
        lineDataSet1.setCircleColorHole(Color.GREEN)
        lineDataSet1.circleRadius = 10f
        lineDataSet1.circleRadius = 5f
        lineDataSet1.valueTextSize = 10f
        lineDataSet1.setValueTextColor(Color.BLUE)
        lineDataSet1.enableDashedLine(15f,5f,12f)
        //lineDataSet1.setColors(colorArray)
        lineDataSet1.setValueFormatter(MyValueFormatter())
        lineDataSet1.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet1.fillAlpha = 255

        val data = LineData(dataSets as List<ILineDataSet>?)
        //data.setValueFormatter(MyValueFormatter())
        mpLineCgart.data = data
        mpLineCgart.invalidate()
        mpLineCgart.setNoDataText("Sem dados disponíveis")
        mpLineCgart.setNoDataTextColor(R.color.black)
        //mpLineCgart.setBackgroundColor(R.color.material_blue_grey_800)
        mpLineCgart.setDrawGridBackground(true)
        mpLineCgart.setDrawBorders(true)
        mpLineCgart.setBorderColor(R.color.black)
        mpLineCgart.setBorderWidth(0.5f)
        mpLineCgart.xAxis.setDrawGridLines(false)

        //trabalhando com descricao
        val description = Description()
        description.apply {
            text = "Bolsas"
            textColor = R.color.black
            textSize = 20f
        }
        mpLineCgart.description = description

        //trabalhar com legendas
        val legend = mpLineCgart.legend
        legend.apply {
            isEnabled = true
            textColor = Color.RED
            textSize = 15f
            form = Legend.LegendForm.LINE
            formSize = 10f
            xEntrySpace = 15f
            formToTextSpace = 10f
        }
        val legendEntries = ArrayList<LegendEntry>()
        var i = 0
        for (valores in legendName ){
            val legenEn = LegendEntry()
            legenEn.apply {
                formColor = colorClassArray[i]
                label = legendName[i]
            }
            legendEntries.add(legenEn)
            i++
        }
        legend.setCustom(legendEntries)

        var xAXis = XAxis()
        var yAxisLeft = YAxis()
        var yAxisRight = YAxis()
        xAXis = mpLineCgart.xAxis
        yAxisLeft = mpLineCgart.axisLeft
        yAxisRight = mpLineCgart.axisRight

        xAXis.setValueFormatter(MyAxisValueFormatter())
        yAxisLeft.setValueFormatter(MyAxisValueFormatter())
    }

    fun dataValues1(): ArrayList<Entry> {
        val dataValues = ArrayList<Entry>()
        dataValues.add(Entry(0F,10f))
        dataValues.add(Entry(1f,15f))
        dataValues.add(Entry(2f,50f))
        dataValues.add(Entry(3f,24f))
        dataValues.add(Entry(4f,28f))
        dataValues.add(Entry(5f,28f))
        return dataValues
    }

    fun dataValues2():ArrayList<Entry>{
        val dataValues = ArrayList<Entry>()
        dataValues.add(Entry(5f,12f))
        dataValues.add(Entry(6f,16f))
        dataValues.add(Entry(7f,23f))
        dataValues.add(Entry(8f,31f))
        dataValues.add(Entry(9f,32f))

        return dataValues
    }

    inner class MyValueFormatter:IValueFormatter{
        override fun getFormattedValue(
            value: Float,
            entry: Entry?,
            dataSetIndex: Int,
            viewPortHandler: ViewPortHandler?
        ): String {
            return value.toString().plus(" kz")
        }
    }

    inner class MyAxisValueFormatter:IAxisValueFormatter{
        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            //axis!!.setLabelCount(3,true)
            return "Dia " + value.toInt()
        }

    }

}
