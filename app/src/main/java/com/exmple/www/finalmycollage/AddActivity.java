package com.exmple.www.finalmycollage;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Button addtask;
    ArrayAdapter<String> arraydays;
    ImageButton btntime;
    int daydb;
    EditText name_aplace;
    EditText name_lectuere;
    EditText name_task;
    TextView select_time;
    View selectedView;
    Snackbar snackbar;
    Spinner spiner;
    DBSqliteApp sqLiteOpenHelper;
    Time f11t;

    /* renamed from: com.wisleem.mycollage.AddActivity.1 */
    class C02011 implements OnItemSelectedListener {
        C02011() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            AddActivity.this.daydb = (int) AddActivity.this.spiner.getSelectedItemId();
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.wisleem.mycollage.AddActivity.2 */
    class C02032 implements OnClickListener {

        /* renamed from: com.wisleem.mycollage.AddActivity.2.1 */
        class C02021 implements OnTimeSetListener {
            C02021() {
            }

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                AddActivity.this.f11t = Time.valueOf(selectedHour + ":" + selectedMinute + ":00");
                AddActivity.this.getTextviewselecttime().setText(AddActivity.this.f11t.toString() + " " + (selectedHour < 12 ? "am" : "pm"));
            }
        }

        C02032() {
        }

        public void onClick(View v) {
            int hour;
            int minute;
            String chr = BuildConfig.FLAVOR;
            Calendar mcurrentTime = Calendar.getInstance();
            String times = AddActivity.this.getIntent().getStringExtra("timeTask");
            if (times == null) {
                hour = mcurrentTime.get(Calendar.HOUR);
                minute = mcurrentTime.get(Calendar.MINUTE);
            } else {
                for (int i = 0; i < 8; i++) {
                    chr = chr + String.valueOf(times.charAt(i));
                }
                AddActivity.this.f11t = Time.valueOf(chr);
                hour = AddActivity.this.f11t.getHours();
                minute = AddActivity.this.f11t.getMinutes();
                AddActivity.this.getTextviewselecttime().setText(AddActivity.this.f11t.toString() + (hour < 12 ? "am" : "pm"));
            }
            TimePickerDialog mTimePicker = new TimePickerDialog(AddActivity.this, new C02021(), hour, minute, true);
            mcurrentTime.set(Calendar.HOUR, hour);
            mcurrentTime.set(Calendar.MINUTE, minute);
            mTimePicker.show();
        }
    }

    class C02043 implements OnClickListener {
        C02043() {
        }

        public void onClick(View v) {
            selectedView = spiner.getSelectedView();
            if (daydb == 0 || getEditTextnametask().getText().toString().equals(BuildConfig.FLAVOR) || getEditTextnamelectuere().getText().toString().equals(BuildConfig.FLAVOR) || getEditTextnameaplace().getText().toString().equals(BuildConfig.FLAVOR)) {
                if (selectedView != null && (selectedView instanceof TextView)) {
                }
                AddActivity.this.getEditTextnametask().setError("\u0627\u0644\u062d\u0642\u0644 \u0641\u0627\u0631\u063a");
                AddActivity.this.getEditTextnamelectuere().setError("\u0627\u0644\u062d\u0642\u0644 \u0641\u0627\u0631\u063a");
                AddActivity.this.getEditTextnameaplace().setError("\u0627\u0644\u062d\u0642\u0644 \u0641\u0627\u0631\u063a");
            } else if (AddActivity.this.sqLiteOpenHelper.insertNewTask(AddActivity.this.getEditTextnametask().getText().toString(), AddActivity.this.getEditTextnamelectuere().getText().toString(), AddActivity.this.getEditTextnameaplace().getText().toString(), AddActivity.this.f11t.toString(), AddActivity.this.daydb)) {
                AddActivity.this.getsnackbar().show();
            }
        }
    }

    public AddActivity() {
        this.daydb = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sqLiteOpenHelper = new DBSqliteApp(this);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbaraddactivity);
        toolbar.setTitle("اضافة مهمة جديدة");
        setSupportActionBar(toolbar);
        this.btntime = (ImageButton) findViewById(R.id.button);
        this.spiner = (Spinner) findViewById(R.id.spinner);
        this.arraydays = new ArrayAdapter(this, android.R.layout.select_dialog_item);
        this.arraydays.add("اختر اليوم");
        this.arraydays.add("السبت");
        this.arraydays.add("الاحد");
        this.arraydays.add("الاثنين");
        this.arraydays.add("الثلاثاء");
        this.arraydays.add("الاربعاء");
        this.arraydays.add("الخميس");
        this.spiner.setAdapter(this.arraydays);
        this.spiner.setOnItemSelectedListener(new C02011());
        String times = getIntent().getStringExtra("timeTask");
        if (times != null) {
            String chr = BuildConfig.FLAVOR;
            for (int i = 0; i < 8; i++) {
                chr = chr + String.valueOf(times.charAt(i));
            }
            f11t = Time.valueOf(chr);
            getTextviewselecttime().setText(f11t.toString() + (f11t.getHours() < 12 ? "am" : "pm"));
        }
        this.btntime.setOnClickListener(new C02032());
        if (getIntent().getStringExtra(DBSqliteApp.NAMETASK) != null) {
            getEditTextnametask().setText(getIntent().getStringExtra(DBSqliteApp.NAMETASK));
            getEditTextnamelectuere().setText(getIntent().getStringExtra(DBSqliteApp.NAMETHALECTURER));
            getEditTextnameaplace().setText(getIntent().getStringExtra(DBSqliteApp.HALLTASK));
            this.spiner.setSelection(getIntent().getIntExtra("dayindex", 0));
        }
        getButtonAddTask().setOnClickListener(new C02043());
    }

    private Button getButtonAddTask() {
        if (this.addtask == null) {
            this.addtask = (Button) findViewById(R.id.add_task);
        }
        return this.addtask;
    }

    private TextView getTextviewselecttime() {
        if (this.select_time == null) {
            this.select_time = (TextView) findViewById(R.id.select_time_task);
        }
        return this.select_time;
    }

    private EditText getEditTextnametask() {
        if (this.name_task == null) {
            this.name_task = (EditText) findViewById(R.id.name_task);
        }
        return this.name_task;
    }

    private EditText getEditTextnamelectuere() {
        if (this.name_lectuere == null) {
            this.name_lectuere = (EditText) findViewById(R.id.name_lectuere);
        }
        return this.name_lectuere;
    }

    private EditText getEditTextnameaplace() {
        if (this.name_aplace == null) {
            this.name_aplace = (EditText) findViewById(R.id.add_a_place);
        }
        return this.name_aplace;
    }

    private Snackbar getsnackbar() {
        if (this.snackbar == null) {
            this.snackbar = Snackbar.make(findViewById(android.support.design.R.id.snackbar_text),"تم اضافة المهمة بنجاح", Snackbar.LENGTH_LONG);
        }
        finish();
        return this.snackbar;
    }

    private int getdayint(String ss) {
        int i = 0;
        switch (ss) {
            case "السبت":
                if (ss.equals("\u0627\u0644\u062e\u0645\u064a\u0633")) {
                    i = 1;
                    break;
                }
                break;
            case "الاحد":
                if (ss.equals("\u0627\u0644\u0627\u062d\u062f")) {
                    i = 2;
                    break;
                }
                break;
            case "الاثنين":
                if (ss.equals("\u0627\u0644\u0633\u0628\u062a")) {
                    i = 3;
                    break;
                }
                break;
            case "الثلاثاء":
                if (ss.equals("\u0627\u0644\u0627\u0631\u0628\u0639\u0627\u0621")) {
                    i = 4;
                    break;
                }
                break;
            case "الاربعاء":
                if (ss.equals("\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621")) {
                    i = 5;
                    break;
                }
                break;
            case "الخميس":
                if (ss.equals("\u0627\u0644\u0627\u062b\u0646\u064a\u0646")) {
                    i = 6;
                    break;
                }
                break;
        }
        return i;
    }

//        switch (i) {
//            case 0 :
//                this.daydb = 0;
//                return 0;
//            case ItemTouchHelper.UP /*1*/:
//                this.daydb = 1;
//                return 1;
//            case ItemTouchHelper.DOWN /*2*/:
//                this.daydb = 2;
//                return 2;
//            case WearableExtender.SIZE_MEDIUM /*3*/:
//                this.daydb = 3;
//                return 3;
//            case ItemTouchHelper.LEFT /*4*/:
//                this.daydb = 4;
//                return 4;
//            case WearableExtender.SIZE_FULL_SCREEN /*5*/:
//                this.daydb = 5;
//                return 5;
//            default:
//                this.daydb = 0;
//                return this.daydb;
//        }


}