package bd.scanner.com.studentmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etStudentName, etStudentID, etStudentDpt, etStudentResult;
    private Button btnAddStudent;
    private String stdName, stdDpt, stdId, stdReult;
    private SQLiteDB sqLiteDB;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etStudentDpt = (EditText) findViewById(R.id.etStudentDpt);
        etStudentName = (EditText) findViewById(R.id.etStudentName);
        etStudentID = (EditText) findViewById(R.id.etStudentID);
        etStudentResult = (EditText) findViewById(R.id.etStudentResult);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);

        sqLiteDB = new SQLiteDB(this);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try{
                    stdName = etStudentName.getText().toString();
                    stdDpt = etStudentDpt.getText().toString();
                    stdReult = etStudentResult.getText().toString();
                    stdId = etStudentID.getText().toString();

                    if (!stdName.equals("") && !stdDpt.equals("") && !stdReult.equals("") && !stdId.equals("")){
                        student = new Student();

                        student.setStdName(stdName);
                        student.setStdDpt(stdDpt);
                        student.setStdResult(stdReult);
                        student.setStdId(stdId);

                        sqLiteDB.create(student);

                        Toast.makeText(getApplicationContext(), "Inserted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Please fill all the field!",Toast.LENGTH_LONG).show();
                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Please fill all the field!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
