import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beevration.R
import com.example.beevrationapp.database.fetchEnergyHarvesterData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnergyData : AppCompatActivity() {

    private lateinit var timestampTextView: TextView
    private lateinit var voltageTextView: TextView
    private lateinit var currentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timestampTextView = findViewById(R.id.timestampTextView)
        voltageTextView = findViewById(R.id.voltageTextView)
        currentTextView = findViewById(R.id.currentTextView)

        CoroutineScope(Dispatchers.Main).launch {
            val url = "http://your_node_mcu_ip_address/data" // Replace with your NodeMCU IP address
            val data = fetchEnergyHarvesterData(url)

            data?.let {
                timestampTextView.text = "Timestamp: ${it.timestamp}"
                voltageTextView.text = "Voltage: ${it.voltage} V"
                currentTextView.text = "Current: ${it.current} A"
            }
        }
    }
}