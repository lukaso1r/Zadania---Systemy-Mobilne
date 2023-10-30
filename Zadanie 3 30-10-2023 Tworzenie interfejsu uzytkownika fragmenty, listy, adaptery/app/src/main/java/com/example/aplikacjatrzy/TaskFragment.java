package com.example.aplikacjatrzy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {

   EditText nameField;
   Button dateButton;
   CheckBox doneCheckBox;
   View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_task, container, false);

        nameField = view.findViewById(R.id.task_name);
        dateButton = view.findViewById(R.id.task_date);
        doneCheckBox = view.findViewById(R.id.task_done);

        Task task = new Task();
        nameField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count){

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                task.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });

        dateButton.setText(task.getDate().toString());
        dateButton.setEnabled(false);
        doneCheckBox.setChecked(task.isDone());
        doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setDone(isChecked));

        return view;
    }


}
