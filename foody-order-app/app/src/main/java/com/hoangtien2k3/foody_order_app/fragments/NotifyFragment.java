package com.hoangtien2k3.foody_order_app.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.activity.ActivityImpl.HomeActivity;
import com.hoangtien2k3.foody_order_app.components.NotifyCard;
import com.hoangtien2k3.foody_order_app.model.Notify;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends Fragment {
    private LinearLayout notifyContainer;
    private LinearLayout btnNotifyApps, btnNotifyUser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public NotifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifyFragment newInstance(String param1, String param2) {
        NotifyFragment fragment = new NotifyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_notify, container, false);

        notifyContainer = mainView.findViewById(R.id.layout_notify);
        btnNotifyApps = mainView.findViewById(R.id.btn_notify_apps);
        TextView tvNotifyApps = mainView.findViewById(R.id.tv_notify_apps);
        btnNotifyUser = mainView.findViewById(R.id.btn_notify_user);
        TextView tvNotifyUser = mainView.findViewById(R.id.tv_notify_user);

        btnNotifyApps.setOnClickListener(view -> {
            btnNotifyApps.setBackground(ContextCompat.getDrawable(requireContext(),R.color.silver));
            tvNotifyApps.setTextColor(Color.WHITE);
            btnNotifyUser.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.bg_white));
            tvNotifyUser.setTextColor(Color.BLACK);

            LoadNotify("apps");
        });

        btnNotifyUser.setOnClickListener(view -> {
            btnNotifyApps.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.bg_white));
            tvNotifyApps.setTextColor(Color.BLACK);
            btnNotifyUser.setBackground(ContextCompat.getDrawable(requireContext(),R.color.silver));
            tvNotifyUser.setTextColor(Color.WHITE);

            LoadNotify("user");
        });

        LoadNotify("apps");

        return mainView;
    }

    private void LoadNotify(String type){
        // xóa tất cả thông báo trước đó
        notifyContainer.removeAllViews();

        ArrayList<Notify> listNotify;
        if(type.equals("apps")){
            listNotify = HomeActivity.dao.getSystemNotify();
        } else {
            listNotify = HomeActivity.dao.getUserNotify(HomeActivity.user.getId());
        }

        for (Notify notify: listNotify){
            notifyContainer.addView(new NotifyCard(getActivity(), notify));
        }
    }
}