package bd.scanner.com.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatedeleteActivity extends AppCompatActivity {

    private EditText etStudentNameUd,etStudentIDUd,etStudentDptUd,etStudentResultUd;
    private Button btnUpdateStudentUd, btnDeletStudentUd;
    private String name, id, dpt, result;
    private SQLiteDB sqLiteDB;
    private Student student;
    private int dbid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete);

        student = new Student();

        Intent intent = getIntent();
        dbid = intent.getIntExtra("dbid",70);
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        dpt = intent.getStringExtra("dpt");
        result = intent.getStringExtra("result");

        etStudentNameUd = (EditText) findViewById(R.id.etStudentNameUd);
        etStudentIDUd = (EditText) findViewById(R.id.etStudentIDUd);
        etStudentDptUd = (EditText) findViewById(R.id.etStudentDptUd);
        etStudentResultUd = (EditText) findViewById(R.id.etStudentResultUd);
        btnUpdateStudentUd = (Button) findViewById(R.id.btnUpdateStudentUd);
        btnDeletStudentUd = (Button) findViewById(R.id.btnDeletStudentUd);

        etStudentNameUd.setText(name);
        etStudentIDUd.setText(id);
        etStudentDptUd.setText(dpt);
        etStudentResultUd.setText(result);

        sqLiteDB = new SQLiteDB(this);

        btnUpdateStudentUd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    student.setStdName(etStudentNameUd.getText().toString());
                    student.setStdId(etStudentIDUd.getText().toString());
                    student.setStdDpt(etStudentDptUd.getText().toString());
                    student.setStdResult(etStudentResultUd.getText().toString());
                    student.setId(dbid);
                    sqLiteDB.update(student);

                    Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        btnDeletStudentUd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    sqLiteDB.delete(dbid);
                    Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    finish();

            }
        });


    }
}
