package com.mobiquity.demo.viewpagerasyncdemo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiquity.demo.viewpagerasyncdemo.R;
import com.mobiquity.demo.viewpagerasyncdemo.objects.ActivityListener;
import com.mobiquity.demo.viewpagerasyncdemo.objects.NumberTask;
import com.mobiquity.demo.viewpagerasyncdemo.objects.NumberTaskListener;

import org.w3c.dom.Text;

/**
 * Created by jdonlan on 7/23/15.
 */
public class CategoryFragment extends Fragment implements NumberTaskListener, ActivityListener {

    private static final String TAG = "CategoryFragment";
    private static final String CATEGORY = "category";

    private View mLayout;

    public static CategoryFragment newInstance(String _category){
        CategoryFragment frag = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, _category);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mLayout = view;
        Bundle args = getArguments();
        if(args != null) {
            String category = args.getString(CATEGORY);
            TextView title = (TextView) view.findViewById(R.id.tv_category);
            title.setText(category.toUpperCase());
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        EditText userNumberField = (EditText) activity.findViewById(R.id.et_number);
        String userNumber = userNumberField.getText().toString();
        if(userNumber.length() > 0) {
            getNumberInfo(userNumber);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView textView = (TextView) mLayout.findViewById(R.id.tv_info);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateInformation(String _string) {
        TextView textView = (TextView) mLayout.findViewById(R.id.tv_info);
        textView.setText(_string);

    }

    private void getNumberInfo(String _number){
        NumberTask numberTask = new NumberTask(this);
        numberTask.execute(_number, getArguments().getString(CATEGORY).toLowerCase());
    }

    @Override
    public void updateNumber(String _userNumber) {
        getNumberInfo(_userNumber);
    }
}
