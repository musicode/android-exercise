package com.musicode.criminalintent.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.musicode.criminalintent.R;
import com.musicode.criminalintent.model.Crime;
import com.musicode.criminalintent.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

public class DetailFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private static final String TAG_DATE_PICKER_DIALOG = "date_picker_dialog";

    private static final int REQUEST_CODE_DATE = 1;

    private Crime mCrime;

    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(UUID crimeId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, crimeId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getInstance().get(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        mTitleField = (EditText) view.findViewById(R.id.title_input);
        mDateButton = (Button) view.findViewById(R.id.date_button);
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.solved_checkbox);

        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment fragment = DatePickerFragment.newInstance(mCrime.getDate());
                fragment.setTargetFragment(DetailFragment.this, REQUEST_CODE_DATE);
                fragment.show(fm, TAG_DATE_PICKER_DIALOG);
            }
        });

        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_DATE) {
            return;
        }
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        mCrime.setDate(date);
        updateDate();
    }

    private void updateDate() {
        mDateButton.setText(DateFormat.format("yyyy/MM/dd", mCrime.getDate()));
    }
}
