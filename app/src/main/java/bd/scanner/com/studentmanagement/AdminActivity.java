package bd.scanner.com.studentmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements RecyclerItemClickListener{

    private Button btnAddStudent;
    private RecyclerView recyclerView;
    private SQLiteDB sqLiteDB;
    private RecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerAdmin);

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(this);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AdminActivity.this,AddStudentActivity.class));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }


    void loadData(){
        sqLiteDB = new SQLiteDB(this);

        List<Student> studentList = new ArrayList<>();

        Cursor cursor = sqLiteDB.retrieve();
        Student student;

        if (cursor.moveToFirst()) {
            do {

                student = new Student();

                student.setId(cursor.getInt(0));
                student.setStdName(cursor.getString(1));
                student.setStdId(cursor.getString(2));
                student.setStdDpt(cursor.getString(3));
                student.setStdResult(cursor.getString(4));

                Log.d("Data",String.valueOf(student.getId())+"\n"+
                student.getStdName()+"\n"+student.getStdId()+"\n"+student.getStdResult());

                studentList.add(student);
            }while (cursor.moveToNext());
        }

        adapter.clear();
        adapter.addAll(studentList);
    }

    @Override
    public void onItemClick(int position, View view) {

        Student student = adapter.getItem(position);

        Intent intent = new Intent(AdminActivity.this, UpdatedeleteActivity.class);
        intent.putExtra("dbid",student.getId());
        intent.putExtra("name",student.getStdName());
        intent.putExtra("id",student.getStdId());
        intent.putExtra("dpt",student.getStdDpt());
        intent.putExtra("result",student.getStdResult());
        startActivity(intent);
    }
}
