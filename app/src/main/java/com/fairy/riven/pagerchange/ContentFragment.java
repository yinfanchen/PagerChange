package com.fairy.riven.pagerchange;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ContentFragment extends Fragment {

    private MyListener listener;//需要实例化

    private int[] bgRes=new int[]{R.drawable.guide_one,R.drawable.guide_two};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener= (MyListener) getActivity();//因为实现类是MainActivity.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,null);
        RelativeLayout bg= (RelativeLayout) view.findViewById(R.id.fragment_bg);
        ImageView img= (ImageView) view.findViewById(R.id.img);
        int index =getArguments().getInt("index");

        bg.setBackgroundResource(bgRes[index]);
        img.setVisibility(index==1?View.VISIBLE:View.INVISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"tiao",Toast.LENGTH_SHORT).show();
                listener.sendMessage("success");
            }
        });
        return view;
    }
    //通过接口回调的 在方法中传递参数
    public interface MyListener{
        void sendMessage(String str);
    }

}
