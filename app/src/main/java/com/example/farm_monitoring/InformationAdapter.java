package com.example.farm_monitoring;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    TextView title, category1, category2, category3;
//    ImageView image;
    List<InformationData> items = new ArrayList<>();
    Context context;
//    Bitmap bitmap;

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

//        if(!informationData.getImg().equals("")) {
//            //안드로이드에서 네트워크와 관련된 작업을 할 때,
//            //반드시 메인 Thread가 아닌 별도의 작업 Thread를 생성하여 작업해야 한다.
//            Thread uThread = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        //서버에 올려둔 이미지 URL
//                        URL url = new URL("http://easyfarm.dothome.co.kr/files/" + informationData.getImg());
//
//                        //Web에서 이미지 가져온 후 ImageView에 지정할 Bitmap 만들기
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                        conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
//                        conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
//
//                        InputStream is = conn.getInputStream(); //inputStream 값 가져오기
//                        bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
//
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//
//            uThread.start(); // 작업 Thread 실행
//
//            try {
//                //메인 Thread는 별도의 작업을 완료할 때까지 대기한다!
//                //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
//                //join() 메서드는 InterruptedException을 발생시킨다.
//                uThread.join();
//
//                //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
//                //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
//                image.setImageBitmap(bitmap);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
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
//            image = itemView.findViewById(R.id.image);

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
