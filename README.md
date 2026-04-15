# 🏥 Hospital Management System
### JavaFX + MVC + OOP — OOSD Lab Project

---

## 📁 Project Structure

```
HospitalManagementSystem/
├── pom.xml                              ← Maven build file
└── src/main/
    ├── java/
    │   ├── module-info.java             ← Java module descriptor
    │   └── com/hospital/
    │       ├── MainApp.java             ← Application entry point
    │       ├── model/
    │       │   ├── Person.java          ← Abstract base class (Abstraction)
    │       │   ├── Patient.java         ← Extends Person (Inheritance)
    │       │   ├── Doctor.java          ← Extends Person (Inheritance)
    │       │   └── Appointment.java     ← Links Patient + Doctor
    │       ├── controller/
    │       │   ├── BaseController.java          ← Shared controller logic
    │       │   ├── DashboardController.java     ← Main window / nav
    │       │   ├── HomeController.java          ← Welcome screen
    │       │   ├── AddPatientController.java    ← Add patient form
    │       │   ├── AddDoctorController.java     ← Add doctor form
    │       │   ├── BookAppointmentController.java
    │       │   ├── PatientListController.java   ← Patient TableView
    │       │   ├── DoctorListController.java    ← Doctor TableView
    │       │   └── AppointmentListController.java
    │       └── util/
    │           └── DataStore.java       ← Singleton in-memory data store
    └── resources/com/hospital/
        ├── view/
        │   ├── Dashboard.fxml           ← Main window (header + sidebar)
        │   ├── Home.fxml                ← Welcome / info screen
        │   ├── AddPatient.fxml          ← Add patient form
        │   ├── AddDoctor.fxml           ← Add doctor form
        │   ├── BookAppointment.fxml     ← Book appointment form
        │   ├── PatientList.fxml         ← Patient TableView
        │   ├── DoctorList.fxml          ← Doctor TableView
        │   └── AppointmentList.fxml     ← Appointment TableView
        └── css/
            └── style.css               ← Global stylesheet
```

---

## 🧠 OOP Concepts Demonstrated

| Concept        | Where                                                                        |
|----------------|------------------------------------------------------------------------------|
| **Abstraction**   | `Person` is abstract — cannot be instantiated directly                    |
| **Inheritance**   | `Patient` and `Doctor` both extend `Person`                               |
| **Encapsulation** | All model fields are `private` with public getters/setters                |
| **Polymorphism**  | `getDetails()` is overridden differently in `Patient` and `Doctor`        |

---

## 🏗️ MVC Architecture

| Layer      | Classes / Files                              |
|------------|----------------------------------------------|
| **Model**  | `Person`, `Patient`, `Doctor`, `Appointment`, `DataStore` |
| **View**   | All `.fxml` files + `style.css`              |
| **Controller** | All `*Controller.java` classes           |

---

## 🚀 How to Run

### Option 1 — IntelliJ IDEA (Recommended)
1. Open IntelliJ → **File → Open** → select the `HospitalManagementSystem` folder
2. IntelliJ auto-detects `pom.xml` — click **"Load Maven Project"** if prompted
3. Wait for Maven to download JavaFX dependencies (requires internet on first run)
4. Open `src/main/java/com/hospital/MainApp.java`
5. Click the ▶ **Run** button (or press `Shift+F10`)

### Option 2 — Maven CLI
```bash
cd HospitalManagementSystem
mvn javafx:run
```

### Option 3 — Eclipse
1. **File → Import → Existing Maven Projects**
2. Select the `HospitalManagementSystem` folder
3. Right-click `MainApp.java` → **Run As → Java Application**

---

## ⚙️ Prerequisites

| Requirement | Version  |
|-------------|----------|
| JDK         | 17 or 21 |
| Maven       | 3.8+     |
| JavaFX      | Auto-downloaded via Maven (21.0.2) |

---

## ✨ Features

- **Add Patient** — Register patients with ID (auto), name, age, ailment
- **Add Doctor** — Register doctors with specialization dropdown (13 options)
- **Book Appointment** — Link patient + doctor + date with calendar picker
- **View Patients** — TableView with live search + delete
- **View Doctors** — TableView with live search + delete
- **View Appointments** — TableView with status management (Scheduled / Completed / Cancelled)
- **Dashboard stats** — Live counts in the header bar
- **Sample data** — 3 patients, 3 doctors, 1 appointment pre-loaded on startup
- **Input validation** — Alerts for missing/invalid fields on all forms

---

## 📸 Screens

| Screen | Description |
|--------|-------------|
| Dashboard | Sidebar navigation + live stat badges |
| Home | Welcome screen with OOP concept summary |
| Add Patient | Form with auto ID, validation alerts |
| Add Doctor | Form with specialization ComboBox |
| Book Appointment | Patient/Doctor dropdowns + DatePicker |
| Patient List | Searchable TableView with delete |
| Doctor List | Searchable TableView with delete |
| Appointment List | TableView with status colour-coding |
