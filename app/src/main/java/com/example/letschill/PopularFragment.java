package com.example.letschill;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letschill.adapters.PopularAdapter;
import com.example.letschill.models.PopularData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PopularFragment extends Fragment {

    private RecyclerView popularListRv;
    private ArrayList<PopularData> popularList;
    private DatabaseReference myRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        popularList = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference("Populars");
        popularListRv = view.findViewById(R.id.popularListRv);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot items : snapshot.getChildren()) {
//                        popularList.add(items.getValue(PopularData.class));
                        PopularData data = items.getValue(PopularData.class);
                        popularList.add(data);
                        Log.e("TAG", "onDataChange: item added - " + data.getName());
                    }

                    if (popularListRv.getAdapter() == null) {
                        FragmentManager manager = getParentFragmentManager();
                        PopularAdapter adapter = new PopularAdapter(popularList, manager);
                        popularListRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        popularListRv.setAdapter(adapter);
                    } else {
                        popularListRv.getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}