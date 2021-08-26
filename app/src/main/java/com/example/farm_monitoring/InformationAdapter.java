package com.example.farm_monitoring;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    TextView title, category1, category2, category3;
    List<InformationData> items = new ArrayList<>();
    Context context;

    //InformationCategoryFragment에서 클릭 이벤트 처리를 위한 커스텀 리스너
    public interface OnItemClickListener{
        void onItemClick(View v, int position, String plant3) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recyclerview_content_information, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InformationData informationData = items.get(position);
        title.setText(informationData.getPlant3());
        category1.setText(informationData.getPlant1());
        category2.setText(informationData.getPlant2());
        category3.setText(informationData.getPlant3());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            category1 = itemView.findViewById(R.id.category1);
            category2 = itemView.findViewById(R.id.category2);
            category3 = itemView.findViewById(R.id.category3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); //클릭한 아이템 위치
                    InformationData data = items.get(position);
                    String plant3 = data.getPlant3();
                    if (position != RecyclerView.NO_POSITION) { //뷰홀더가 참조하는 아이템이 어댑터에서 삭제되면 getAdapterPosition() 메서드는 NO_POSITION을 리턴하기 때문
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, position, plant3);
                        }
                    }
                }
            });

        }
    }

    public void addData(String plant1, String plant2, String plant3){
        InformationData item = new InformationData();

        item.setPlant1(plant1);
        item.setPlant2(plant2);
        item.setPlant3(plant3);

        items.add(item);
        notifyDataSetChanged();
    }
}
