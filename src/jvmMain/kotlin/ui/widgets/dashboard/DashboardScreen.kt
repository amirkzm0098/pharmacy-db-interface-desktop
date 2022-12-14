package ui.widgets.dashboard

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.navigator.NavHost
import ui.widgets.category.view.CategoryScreen
import ui.widgets.company.view.CompanyScreen
import ui.widgets.doctor.view.DoctorScreen
import ui.widgets.insurance.view.InsuranceScreen
import ui.widgets.med.view.MedScreen
import ui.widgets.patient.view.PatientScreen
import ui.widgets.pharm.view.PharmScreen
import ui.widgets.prescription.view.PrescriptionScreen
import ui.widgets.util.LocalIsDarkMode

@Preview
@Composable
fun DashboardScreen() {
    val selectedItem = remember { mutableStateOf(0) }
    val isDark = LocalIsDarkMode.current
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationRail(
            header = {
                IconButton(onClick = {
                    isDark.value = !isDark.value
                }) {
                    if (isDark.value) Icon(
                        painterResource("Icons/light_mode.svg"),
                        contentDescription = "Switch to dark mode",
                        modifier = Modifier.width(24.dp).height(24.dp)
                    )
                    else Icon(
                        painterResource("Icons/dark_mode.svg"),
                        contentDescription = "Switch to light mode",
                        modifier = Modifier.width(24.dp).height(24.dp)
                    )
                }
            }, modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())
        ) {
            Table.values().forEachIndexed { index, table ->
                Column(modifier = Modifier) {
                    NavigationRailItem(
                        label = { Text(table.tableName) },
                        selected = selectedItem.value == index,
                        icon = {
                            Icon(
                                painterResource(table.iconPath),
                                contentDescription = table.tableName,
                                modifier = Modifier.width(30.dp).height(30.dp)
                            )
                        },
                        onClick = {
                            selectedItem.value = index
                        },
                    )
                }
            }
        }
        Table.values()[selectedItem.value].screen()
    }
}

enum class Table(val tableName: String, val iconPath: String, val screen: @Composable () -> Unit) {
    PHARM(
        "Pharm",
        "Icons/admin_meds.svg",
        { NavHost { PharmScreen() } },
    ),
    MED(
        "Med",
        "Icons/medication.svg",
        { NavHost { MedScreen() } },
    ),
    CATEGORY(
        "Category",
        "Icons/category.svg",
        { NavHost { CategoryScreen() } },
    ),
    COMPANY(
        "Company",
        "Icons/lab_panel.svg",
        { NavHost { CompanyScreen() } },
    ),
    PATIENT(
        "Patient",
        "Icons/personal_injury.svg",
        { NavHost { PatientScreen() } },
    ),
    PRESCRIPTION(
        "Prescription",
        "Icons/prescriptions.svg",
        { NavHost { PrescriptionScreen() } },
    ),
    DOCTOR(
        "Doctor",
        "Icons/stethoscope.svg",
        { NavHost { DoctorScreen() } },
    ),
    INSURANCE(
        "Insurance",
        "Icons/health_and_safety.svg",
        { NavHost { InsuranceScreen() } },
    ),
}